package com.gossamercms.products.services;


import com.gossamercms.mvc.annotations.ModuleService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ModuleService
@RequiredArgsConstructor
public class OrderFulfillmentService {
//
//    private final OutboxService outboxService;
//    private final EventRegistrationsDbService eventRegistrationsDbService;
//    private final EventTicketsDbService eventTicketsDbService;
//
//    public List<EventTicketDto> fulfill(EventOrderDto order, List<EventOrderResponse.Item> items) {
//        List<EventTicketDto> tickets = new ArrayList<>();
//        for(EventOrderResponse.Item item : items) {
//            EventRegistrationDto registrationDto = createRegistration(order, item);
//            tickets.add(createTicket(order, item, registrationDto));
//        }
//
//        outboxService.publish(
//                "event.tickets.created",
//                order.getId(),
//                EventTicketsCreatedEvent.builder()
//                        .order(order)
//                        .tickets(List.copyOf(tickets))
//                        .build()
//        );
//
//        return tickets;
//    }
//
//    private EventRegistrationDto createRegistration(
//            EventOrderDto order,
//            EventOrderResponse.Item item) {
//        com.gossamercms.events.services.EventOrderFulfillmentService.NameParts nameParts = splitFullName(order.getPurchaserName());
//
//        return eventRegistrationsDbService.create(order.getPurchaserUserContextId(),
//                EventRegistrationDto.builder()
//                        .eventId(order.getEventId())
//                        .userContextId(order.getPurchaserUserContextId())
//                        .attendeeEmail(order.getPurchaserEmail())
//                        .orderId(order.getId())
//                        .registeredAt(order.getCreatedAt())
//                        .status(EventRegistrationStatuses.REGISTERED.toString())
//                        .attendeeFirstName(nameParts.firstName())
//                        .attendeeLastName(nameParts.lastName())
//                        .build());
//    }
//
//    private EventTicketDto createTicket(
//            EventOrderDto order,
//            EventOrderResponse.Item item,
//            EventRegistrationDto registration) {
//
//        return eventTicketsDbService.create(order.getPurchaserUserContextId(),
//                EventTicketDto.builder()
//                        .issuedAt(order.getCreatedAt())
//                        .status(EventTicketStatuses.ISSUED.toString())
//                        .ticketNumber(order.getId() + "-" + item.id())
//                        .ticketTypeId(item.ticketTypeId())
//                        .registrationId(registration.getId())
//                        .build());
//    }
//
//    public static com.gossamercms.events.services.EventOrderFulfillmentService.NameParts splitFullName(String fullName) {
//        if (fullName == null || fullName.isBlank()) {
//            throw new IllegalArgumentException("Full name is required");
//        }
//
//        String[] parts = fullName.strip().split("\\s+", 2);
//        return new com.gossamercms.events.services.EventOrderFulfillmentService.NameParts(parts[0], parts.length == 2 ? parts[1] : "");
//    }

    public record NameParts(String firstName, String lastName) {}
}
