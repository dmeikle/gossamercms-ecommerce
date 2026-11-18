package com.gossamercms.products.data;

import com.gossamercms.products.dtos.CartDto;
import com.gossamercms.products.models.Cart;
import com.gossamercms.mvc.annotations.ModuleDbService;
import com.gossamercms.mvc.data.BaseDbService;
import com.gossamercms.mvc.data.DataSourceManager;

import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class CartsDbService extends BaseDbService<Cart, CartDto> {

    public CartsDbService(DataSourceManager ds) {
        super(Cart.class, CartDto.class, ds);
    }

    @Override
    protected Cart mapToEntity(CartDto dto) {
        return dto.toEntity();
    }

    @Override
    protected CartDto mapToDto(Cart entity) {
        return entity.toDto();
    }

    @Override
    protected CartDto removeExcludedFields(CartDto dto) {
        return dto;
    }

    public CartDto findGuestCart(String sessionId) {
        return dsManager.get(meta.datasourceKey()).getNamedParameterJdbcTemplate().query("""
                select "id" from ecommerce_carts
                where "sessionId" = :sessionId and "userContextId" is null
                order by "createdAt" desc nulls last, "id"
                limit 1
                """, Map.of("sessionId", sessionId),
                rs -> rs.next() ? CartDto.builder().id(rs.getObject("id", UUID.class)).build() : null);
    }

    @Override
    protected void throw404(String id) {

    }

}
