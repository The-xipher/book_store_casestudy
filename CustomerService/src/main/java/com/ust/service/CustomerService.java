package com.ust.service;

import com.ust.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomer();
    void deleteCustomer(long id);
    Customer getCustomerById(long id);
    Customer updateCustomer(long id,Customer customer);
}
