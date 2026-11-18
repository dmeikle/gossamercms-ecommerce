package com.gossamercms.products.data;

import com.gossamercms.mvc.data.*;
import com.gossamercms.mvc.helpers.JsonbHelper;
import com.gossamercms.products.dtos.ProductDto;
import com.gossamercms.products.models.Product;
import com.gossamercms.mvc.annotations.ModuleDbService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@ModuleDbService
public class ProductsDbService extends BaseDbService<Product, ProductDto> {

    public ProductsDbService(DataSourceManager ds) {
        super(Product.class, ProductDto.class, ds);
    }

    @Override
    protected Product mapToEntity(ProductDto dto) {
        return dto.toEntity();
    }

    @Override
    protected ProductDto mapToDto(Product entity) {
        return entity.toDto();
    }

    @Override
    protected ProductDto removeExcludedFields(ProductDto dto) {
        return dto;
    }

    public ListResultset<ProductDto> listProductsByCategory(QueryOptions options) {
        DataSourceAdapter ds = dsManager.get(meta.datasourceKey());
        String sql = """
                select ep.* from ecommerce_products ep
                    inner join ecommerce_product_categories epc on epc."productId" = ep."id"
                    inner join ecommerce_categories ec on ec."id" = epc."categoryId"
                /*WHERE_CLAUSE*/
            """;

        String countSql = """
            select count(ep.id) from ecommerce_products ep
                    inner join ecommerce_product_categories epc on epc."productId" = ep."id"
                    inner join ecommerce_categories ec on ec."id" = epc."categoryId"
                /*WHERE_CLAUSE*/
    """;

        return ds.findAllBySql(
                        sql,
                        countSql,
                        options.filters(),
                        options.queryFilters(),
                        Collections.emptyMap(),
                        options.orderBy(),
                        options.page(),
                        options.size(),
                        (rs, rowNum) -> ProductDto.builder()
                                .id(rs.getObject("id", UUID.class))
                                .name(rs.getString("name"))
                                .slug(rs.getString("slug"))
                                .sku(rs.getString("sku"))
                                .description(rs.getString("description"))
                                .price(rs.getBigDecimal("price"))
                                .status(rs.getString("status"))
                                .metadata(JsonbHelper.fromJsonb(rs.getObject("metadata"), Map.class))
                                .thumbnailUrl(rs.getString("thumbnailUrl"))
                                .createdAt( rs.getTimestamp("createdAt") != null
                                        ? rs.getTimestamp("createdAt").toInstant()
                                        : null)
                                .currency(rs.getString("currency"))
                                .build()
                );
    }

}
