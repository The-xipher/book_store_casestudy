package com.ust.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
  private  String author;
  private long price;
  private  long stock;

    public Book(String title, String author, long price, long stock) {
        this.title=title;
        this.author=author;
        this.price=price;
        this.stock=stock;
    }
}
