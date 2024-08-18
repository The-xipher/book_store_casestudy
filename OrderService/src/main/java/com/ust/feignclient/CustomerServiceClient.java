package com.ust.feignclient;


import com.ust.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {
    @GetMapping("customers/{id}")
    public Customer getCustomerById(@PathVariable long id);
}
