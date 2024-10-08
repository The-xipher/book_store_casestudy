package com.ust.controller;
import com.ust.domain.Order;
import com.ust.domain.Status;
import com.ust.dto.CombinedDto;
import com.ust.dto.Customer;
import com.ust.dto.OrderDto;
import com.ust.exception.StockNotAvailableException;
import com.ust.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService service;

    @PostMapping
    public ResponseEntity<CombinedDto> createOrder(@RequestBody OrderDto dto){
        Order order=dto.toOrder(dto);
        var a=service.getCustomerById(order.getCustomerId());
        if(order.getQuantity()>service.getStockBookId(order.getBookId())){
            throw new StockNotAvailableException("Stock not available for that quantity"+order.getQuantity());
        }
        var o=dto.toDto(service.createOrder(order));
        if(order.getStatus()== Status.CONFIRMED|| order.getStatus()==Status.PENDING){
            service.updateStock(order.getId(),order.getBookId(),order.getStatus());
        }

        CombinedDto combinedDto=new CombinedDto(o,a);
        return ResponseEntity.status(HttpStatus.CREATED).body(combinedDto);
    }

    @GetMapping
    public ResponseEntity<List<Order>>  getAllOrders(){
        return ResponseEntity.ok().body(service.getAllOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable long id,@RequestBody OrderDto dto){
        Order order=dto.toOrder(dto);
        if(order.getQuantity()>service.getStockBookId(order.getBookId())){
            throw new StockNotAvailableException("Stock not available for that quantity"+order.getQuantity());
        }
        var o=dto.toDto(service.updateOrder(id,order));
        service.updateStock(order.getBookId(),service.getStockBookId(order.getBookId()),order.getStatus());
        return ResponseEntity.ok().body(o);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus>  deleteOrder(@PathVariable long id){
        service.deleteOrder(id);
      return   ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CombinedDto> getOrderById(@PathVariable long id){

        var a=OrderDto.toDto(service.getOrderById(id));
        var b=service.getCustomerById(a.customerId());
        CombinedDto dto=new CombinedDto(a,b);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}/stocks")
    public ResponseEntity<Integer> getStocksByBookId(@PathVariable long id){
        return ResponseEntity.ok().body(service.getStockBookId(id));
    }
    @GetMapping("/{id}/customer")
    public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable long id){
        return ResponseEntity.ok().body(service.getCustomerById(id));
    }
}
