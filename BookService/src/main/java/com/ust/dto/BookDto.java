package com.ust.dto;

import com.ust.domain.Book;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public record BookDto(
        long id,
        @NotEmpty(message = "Title cannot be empty")@Length(min = 1 ,max = 255,message = "length should be between 1 and 255")
        String title,
        @NotEmpty(message = "Title cannot be empty")@Length(min = 1 ,max = 255,message = "length should be between 1 and 255")
        String author,
        @NotNull(message = "price should be not null") @Positive( message = "price should be positive and greater than 0") @Digits(integer = 10,fraction = 2,message = "limit is 2 decimal values")
        long price,
        @NotNull(message = "Stock input required")
                @PositiveOrZero(message = "value should be 0 or more")
          long stock
) {
    public Book toBook(BookDto dto){
        return new Book(dto.title, dto.author, dto.price, dto.stock);

    }

    public BookDto toDto(Book book){
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getStock());
    }
}
