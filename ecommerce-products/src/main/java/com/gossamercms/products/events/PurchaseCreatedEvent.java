package com.gossamercms.products.events;

import com.gossamercms.outbox.eventbus.DomainEvent;
import com.gossamercms.products.dtos.PurchaseDto;
import com.gossamercms.products.dtos.PurchaseItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCreatedEvent implements DomainEvent {
    private PurchaseDto purchase;
    private List<PurchaseItemDto> items;
}