package com.ust.service;

import com.ust.domain.Order;
import com.ust.dto.Customer;

import java.util.List;

public interface OrderService {
    public Order createOrder(Order order);
    Order getOrderById(long id);
    List<Order> getAllOrders();
    Order updateOrder(long id,Order order);
    void deleteOrder(long id);
    int getStockBookId(long id);
    Customer getCustomerById(long id);

}
