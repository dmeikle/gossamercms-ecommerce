package com.gossamercms.products.data;

import com.gossamercms.mvc.data.*;
import com.gossamercms.mvc.helpers.JsonbHelper;
import com.gossamercms.products.dtos.CartItemDto;
import com.gossamercms.products.dtos.ProductDto;
import com.gossamercms.products.dtos.responses.CartItemResponse;
import com.gossamercms.products.models.CartItem;
import com.gossamercms.mvc.annotations.ModuleDbService;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class CartItemsDbService extends BaseDbService<CartItem, CartItemDto> {

    public CartItemsDbService(DataSourceManager ds) {
        super(CartItem.class, CartItemDto.class, ds);
    }

    @Override
    protected CartItem mapToEntity(CartItemDto dto) {
        return dto.toEntity();
    }

    @Override
    protected CartItemDto mapToDto(CartItem entity) {
        return entity.toDto();
    }

    @Override
    protected CartItemDto removeExcludedFields(CartItemDto dto) {
        return dto;
    }

    public ListResultset<CartItemResponse> getCartItemsByCartId(UUID cartId, int page, int pageSize) {
        DataSourceAdapter ds = dsManager.get(meta.datasourceKey());
        String from = """
                from ecommerce_cart_items eci
                inner join ecommerce_products ep on ep."id" = eci."productId"
                /*WHERE_CLAUSE*/
                """;
        String sql = """
                select ep.*, eci."id" as "cartItemId", eci."cartId", eci."productId",
                       eci."quantity", eci."createdAt" as "cartItemCreatedAt"
                """ + from + """
                order by eci."id"
                """;

        return ds.findAllBySql(
                sql,
                "select count(*) " + from,
                Map.of("cartId", cartId, "status", "ACTIVE"),
                List.of(),
                Map.of(),
                Map.of(),
                page,
                pageSize,
                (rs, rowNum) -> {
                    BigDecimal price = rs.getBigDecimal("price");
                    Integer quantity = rs.getObject("quantity", Integer.class);
                    ProductDto product = ProductDto.builder()
                            .id(rs.getObject("id", UUID.class))
                            .name(rs.getString("name"))
                            .slug(rs.getString("slug"))
                            .sku(rs.getString("sku"))
                            .description(rs.getString("description"))
                            .price(price)
                            .status(rs.getString("status"))
                            .metadata(JsonbHelper.fromJsonb(rs.getObject("metadata"), Map.class))
                            .thumbnailUrl(rs.getString("thumbnailUrl"))
                            .createdAt(readUtcTimestamp(rs, "createdAt"))
                            .currency(rs.getString("currency"))
                            .build();

                    return CartItemResponse.builder()
                            .id(rs.getObject("cartItemId", UUID.class))
                            .cartId(rs.getObject("cartId", UUID.class))
                            .productId(rs.getObject("productId", UUID.class))
                            .quantity(quantity)
                            .createdAt(readUtcTimestamp(rs, "cartItemCreatedAt"))
                            .unitPrice(price)
                            .subtotal(price == null || quantity == null
                                    ? null : price.multiply(BigDecimal.valueOf(quantity)))
                            .product(product)
                            .build();
                }
        );
    }

    private static Instant readUtcTimestamp(ResultSet rs, String column) throws SQLException {
        LocalDateTime value = rs.getObject(column, LocalDateTime.class);
        return value == null ? null : value.toInstant(ZoneOffset.UTC);
    }

}
