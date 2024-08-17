package com.ust.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
   @Id
           @GeneratedValue(strategy = GenerationType.IDENTITY)
   long id;
   String  name;
   String email;
   String phoneNumber;

   public Customer(String name) {
      this.name = name;
   }

   public Customer(String name, String email, String phoneNumber) {
      this.name = name;
      this.email = email;
      this.phoneNumber = phoneNumber;
   }
}
