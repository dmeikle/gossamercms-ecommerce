package com.gossamercms.products.models;

import com.gossamercms.products.dtos.CategoryDto;
import com.gossamercms.mvc.models.BaseModel;
import com.gossamercms.mvc.models.ModelMeta;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements BaseModel{
    private UUID id;
    private String name;
    private String slug;
    private String description;
    private UUID parentId;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
    private UUID createdBy;
    private UUID updatedBy;

    public CategoryDto toDto() {
        return CategoryDto.builder()
            .id(id)
            .name(name)
            .slug(slug)
            .description(description)
            .parentId(parentId)
            .status(status)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .createdBy(createdBy)
            .updatedBy(updatedBy)
            .build();
    }


public static final ModelMeta META = ModelMeta.builderWithId("ecommerce_categories")
    .column("id", UUID.class)
    .column("name", String.class, 100)
    .column("slug", String.class, 100)
    .column("description", String.class)
    .column("parentId", UUID.class)
    .column("status", String.class, 20)
    .column("createdAt", Instant.class)
    .column("updatedAt", Instant.class)
    .column("createdBy", UUID.class)
    .column("updatedBy", UUID.class)
    .defaultSort("id desc")
    .build();



    @Override
    public ModelMeta meta() {
        return META;
    }

    @Override
    public ModelMeta metaOf() {
        return BaseModel.super.metaOf();
    }

}
