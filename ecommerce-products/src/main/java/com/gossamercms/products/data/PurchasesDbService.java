package com.gossamercms.products.data;

import com.gossamercms.mvc.data.DataSourceAdapter;
import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.products.enums.PurchasePaymentStatus;
import com.gossamercms.products.models.Purchase;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class PurchasesDbService extends BaseDbService<Purchase, PurchaseDto> {

    public PurchasesDbService(DataSourceManager ds) {
        super(Purchase.class, PurchaseDto.class, ds);
    }

    @Override
    protected Purchase mapToEntity(PurchaseDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchaseDto mapToDto(Purchase entity) {
        return entity.toDto();
    }

    @Override
    protected PurchaseDto removeExcludedFields(PurchaseDto dto) {
        return dto;
    }

    @Override
    protected void throw404(String id) {

    }

    public long getNextPurchaseSequenceValue() {
        DataSourceAdapter ds = dsManager.get(meta.datasourceKey());
        String sql = """
        SELECT nextval('ecommerce_purchase_number_seq')
        """;

        return ds.executeSql(sql, Long.class);
    }

    public PurchaseDto purchase(UUID purchaseId, boolean lock) {
        DataSourceAdapter ds = dsManager.get(meta.datasourceKey());
        String sql = """
            SELECT *
            FROM ecommerce_purchases
            WHERE id = :id
            """ + (lock ? " FOR UPDATE" : "");

        return ds.findOneBySql(
                sql,
                Map.of("id", purchaseId),
                (rs, rowNum) -> PurchaseDto.builder()
                        .id(rs.getObject("id", UUID.class))
                        .purchaseNumber(rs.getString("purchaseNumber"))
                        .customerContextId(rs.getObject("customerContextId", UUID.class))
                        .status(rs.getString("status"))
                        .totalAmount(rs.getBigDecimal("totalAmount"))
                        .currency(rs.getString("currency"))
                        .subtotal(rs.getBigDecimal("subtotal"))
                        .discountTotal(rs.getBigDecimal("discountTotal"))
                        .shippingTotal(rs.getBigDecimal("shippingTotal"))
                        .taxTotal(rs.getBigDecimal("taxTotal"))
                        .build()
        );
    }


    public void completePurchase(UUID userContextId, PurchaseDto purchase, Map<String, Object> params) {
        this.update(userContextId, purchase, params);
    }

    public void completeCancellation(UUID userContextId, UUID purchaseId) {
        this.update(userContextId,
                PurchaseDto.builder()
                        .id(purchaseId)
                        .status(PurchasePaymentStatus.CANCELLED.toString())
                        .build(),
                Map.of("id", purchaseId));
    }
}
