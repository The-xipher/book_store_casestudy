package com.ust.dto;

import com.ust.domain.Customer;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CustomerDto(

        long id,
        @NotEmpty(message = "name is required") @Length(min =1,max = 255,message = "Name should be between 1 and 255")
        String  name,
        @NotEmpty(message = "email is required") @Email(message = "invalid email")
        String email,
        @NotEmpty(message = "phone number required") @Pattern(regexp = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$")
        String phoneNumber) {

        public Customer toCustomer(CustomerDto dto){
                return new Customer( dto.name, dto.email, dto.phoneNumber);
        }

        public  CustomerDto toDto(Customer customer){
                return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhoneNumber());
        }
}
