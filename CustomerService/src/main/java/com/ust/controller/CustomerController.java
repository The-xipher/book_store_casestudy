package com.ust.controller;

import com.ust.domain.Customer;
import com.ust.dto.CustomerDto;
import com.ust.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService service;
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto dto){
        Customer customer=dto.toCustomer(dto);
        var c=dto.toDto(service.createCustomer(customer));
      return  ResponseEntity.status(HttpStatus.CREATED).body(c);

    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok().body(service.getAllCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id){
        return ResponseEntity.ok().body(service.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @PathVariable long id,@RequestBody CustomerDto dto){
        Customer cus=dto.toCustomer(dto);
        var c=dto.toDto(service.updateCustomer(id,cus));
        return ResponseEntity.ok().body(c);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable long id){
        service.deleteCustomer(id);
       return ResponseEntity.noContent().build();
    }

}
