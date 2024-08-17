package com.ust.dto;

import com.ust.domain.Order;
import com.ust.domain.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderDto(

        long id,
        @NotNull(message = "CustomerID required")
        long customerId,
        @NotNull(message = "BookID required")
        long bookId,
         @NotNull(message = "Quantity required") @Positive(message = "Positive quantity required")
         int quantity,
        @NotNull(message = "Status Required")  @Pattern(regexp = " PENDING|CONFIRMED|CANCELLED",message =" PENDING, CONFIRMED, CANCELLED   any one of this required" )
        Status status
) {
    public OrderDto toDto(Order order){
        return new OrderDto(order.getId(),order.getCustomerId(), order.getBookId(),order.getQuantity(),order.getStatus());
    }

    public Order toOrder(OrderDto dto){
        return new Order(dto.customerId, dto.bookId,dto.quantity,dto.status);
    }

}
