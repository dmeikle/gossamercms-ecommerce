package com.gossamercms.products.data;

import com.gossamercms.mvc.data.DataSourceAdapter;
import com.gossamercms.mvc.jwt.CurrentJwtUser;
import com.gossamercms.products.dtos.PurchasePaymentDto;
import com.gossamercms.products.enums.PurchasePaymentStatus;
import com.gossamercms.products.models.PurchasePayment;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;
import com.gossamercms.mvc.data.ListResultset;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class PurchasePaymentsDbService extends BaseDbService<PurchasePayment, PurchasePaymentDto> {

    public PurchasePaymentsDbService(DataSourceManager ds) {
        super(PurchasePayment.class, PurchasePaymentDto.class, ds);
    }

    @Override
    protected PurchasePayment mapToEntity(PurchasePaymentDto dto) {
        return dto.toEntity();
    }

    @Override
    protected PurchasePaymentDto mapToDto(PurchasePayment entity) {
        return entity.toDto();
    }

    @Override
    protected PurchasePaymentDto removeExcludedFields(PurchasePaymentDto dto) {
        return dto;
    }


    public PurchasePaymentDto payment(UUID purchaseId) {

        String sql = """
                SELECT *
                FROM ecommerce_purchase_payments
                WHERE "purchaseId" = :purchaseId
                FOR UPDATE
                """;

        DataSourceAdapter ds = dsManager.get(meta.datasourceKey());

        return ds.findOneBySql(
                sql,
                Map.of("purchaseId", purchaseId),
                (rs, rowNum) -> PurchasePaymentDto.builder()
                        .id(rs.getObject("id", UUID.class))
                        .purchaseId(rs.getObject("purchaseId", UUID.class))
                        .provider(rs.getString("provider"))
                        .amount(rs.getBigDecimal("amount"))
                        .currency(rs.getString("currency"))
                        .status(rs.getString("status"))
                        .idempotencyKey(rs.getString("idempotencyKey"))
                        .providerPaymentId(rs.getString("providerPaymentId"))
                        .providerCheckoutId(rs.getString("providerCheckoutId"))
                        .providerCustomerId(rs.getString("providerCustomerId"))
                        //.processedAt(rs.getTimestamp("processedAt"))
                        .createdAt( rs.getTimestamp("createdAt") != null
                                ? rs.getTimestamp("createdAt").toInstant()
                                : null)
                        .updatedAt( rs.getTimestamp("updatedAt") != null
                                ? rs.getTimestamp("updatedAt").toInstant()
                                : null)
                        .build()
        );
    }

    /**
     * Create a new checkout operation for the given purchase payment.
     *
     * @param userContextId         The current authenticated user.
     * @param purchasePayment The purchase payment to create a checkout for.
     * @param params          Additional parameters for the checkout operation.
     *                        "id",
     *                 "purchaseId",
     *                 "provider",
     *                 "paymentMethod",
     *                 "providerCustomerId",
     *                 "amount",
     *                 "currency",
     *                 "status",
     *                 "idempotencyKey",
     *                 "metadata"
     */
    public void createCheckout(UUID userContextId, PurchasePaymentDto purchasePayment, Map<String, Object> params) {
       this.update(userContextId,
               purchasePayment, params);

    }
//
//    public void markCheckoutUnknown(UUID id) {
//        jdbc().update("""
//                UPDATE ecommerce_purchase_payments SET "status"='checkout_unknown',"updatedAt"=CURRENT_TIMESTAMP
//                WHERE "id"=:id AND "status"='checkout_creating'
//                """, Map.of("id", id));
//    }
//
//    public void completeCheckout(UUID id, String checkoutId) {
//        jdbc().update("""
//                UPDATE ecommerce_purchase_payments SET "providerCheckoutId"=:checkout,
//                  "status"=CASE WHEN "status"='checkout_creating' THEN 'pending' ELSE "status" END,
//                  "updatedAt"=CURRENT_TIMESTAMP WHERE "id"=:id
//                """, Map.of("id", id, "checkout", checkoutId));
//    }

    public void completeCancellation(UUID userContextId, UUID purchaseId) {
        update(userContextId,
                PurchasePaymentDto.builder()
                        .purchaseId(purchaseId)
                        .status(PurchasePaymentStatus.CANCELLED.toString())
                        .build(),
               Map.of("purchaseId", purchaseId));
    }

    public void completePayment(UUID paymentId, String providerPaymentId, String providerCustomerId) {
        int updated = dsManager.get(meta.datasourceKey()).getNamedParameterJdbcTemplate().update("""
                UPDATE ecommerce_purchase_payments
                SET "status" = :status, "providerPaymentId" = :providerPaymentId,
                    "providerCustomerId" = COALESCE(:providerCustomerId, "providerCustomerId"),
                    "updatedAt" = CURRENT_TIMESTAMP
                WHERE "id" = :id
                """, new MapSqlParameterSource()
                .addValue("id", paymentId)
                .addValue("status", PurchasePaymentStatus.PAID.toString())
                .addValue("providerPaymentId", providerPaymentId)
                .addValue("providerCustomerId", providerCustomerId, Types.VARCHAR));
        if (updated != 1) {
            throw new IllegalStateException("Payment could not be completed: " + paymentId);
        }
    }
//
//    public void setStatus(UUID id, String status) {
//        jdbc().update("""
//                UPDATE purchase_payments SET "status"=:status,"updatedAt"=CURRENT_TIMESTAMP WHERE "id"=:id
//                """, Map.of("id", id, "status", status));
//    }

}
