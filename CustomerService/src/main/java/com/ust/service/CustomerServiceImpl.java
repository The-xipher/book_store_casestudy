package com.ust.service;

import com.ust.domain.Customer;
import com.ust.exception.CustomerNotFoundException;
import com.ust.exception.DuplicateCustomerException;
import com.ust.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if(repository.findAll().stream().anyMatch(existingcustomer->existingcustomer.getEmail().equals(customer.getEmail()))){
            throw new DuplicateCustomerException("Duplicate customer found"+customer);
        }
        return repository.save(customer) ;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return repository.findAll();
    }

    @Override
    public void deleteCustomer(long id) {
        Customer c=getCustomerById(id);
        repository.deleteById(id);


    }

    @Override
    public Customer getCustomerById(long id) {
       return repository.findById(id).orElseThrow(()->new CustomerNotFoundException("CustomerId Not found"+id));
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) {
        Customer c=getCustomerById(id);
        c.setEmail(customer.getEmail());
        c.setName(customer.getName());
        c.setPhoneNumber(customer.getPhoneNumber());
        return repository.save(c);
    }
}
