package com.ust.service;

import com.ust.domain.Order;
import com.ust.domain.Status;
import com.ust.dto.Customer;
import com.ust.exception.OrderNotFoundException;
import com.ust.feignclient.BookServiceClient;
import com.ust.feignclient.CustomerServiceClient;
import com.ust.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    private final CustomerServiceClient customerServiceClient;
    private final OrderRepository repository;
    private final BookServiceClient bookServiceClient;
    public OrderServiceImpl(CustomerServiceClient customerServiceClient, OrderRepository repository, BookServiceClient bookServiceClient) {
        this.customerServiceClient = customerServiceClient;
        this.repository = repository;
        this.bookServiceClient = bookServiceClient;
    }

    @Override
    public Order createOrder(Order order) {

        return repository.save(order) ;
    }

    @Override
    public Order getOrderById(long id) {
        return repository.findById(id).orElseThrow(()->new OrderNotFoundException("Order not found"+id));
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order updateOrder(long id, Order order) {
        Order or1=getOrderById(id);
        or1.setBookId(order.getBookId());
        or1.setCustomerId(order.getCustomerId());
        or1.setQuantity(order.getQuantity());
        or1.setStatus(order.getStatus());
        return repository.save(or1);
    }

    @Override
    public void deleteOrder(long id) {
        getOrderById(id);
        repository.deleteById(id);
    }

    @Override
    public int getStockBookId(long id) {
        return bookServiceClient.getStockFromBookId(id);
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerServiceClient.getCustomerById(id);
    }

    @Override
    public void updateStock(long id, long BookId, Status status) {
        var o=getOrderById(id);
        int stock=getStockBookId(BookId);
        if(status==Status.CONFIRMED||status==Status.PENDING){
            if(status!=o.getStatus()){
                stock=stock-o.getQuantity();
            }
        }
        if (status==Status.CANCELLED){
            if (status!=o.getStatus()){
                stock=stock+o.getQuantity();
            }
        }
        bookServiceClient.updateStock(id,stock);
    }
}
