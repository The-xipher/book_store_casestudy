package com.ust.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

    public Order(long customerId, long bookId, int quantity, Status status) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.status = status;
    }

    private long customerId;
   private long bookId;
   private  int quantity;
    private Status status;

}

