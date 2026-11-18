package com.gossamercms.products.dtos;

import com.gossamercms.products.models.Category;
import com.gossamercms.mvc.data.DtoWithId;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements DtoWithId{
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

    public Category toEntity() {
        return Category.builder()
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
}
