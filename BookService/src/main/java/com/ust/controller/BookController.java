package com.ust.controller;


import com.ust.domain.Book;
import com.ust.dto.BookDto;
import com.ust.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService service;



    @PostMapping
    public ResponseEntity< BookDto> createBook(@Valid @RequestBody BookDto dto){
        Book book=dto.toBook(dto);
       var b= dto.toDto(service.createBook(book));
        return ResponseEntity.status(HttpStatus.CREATED).body(b);
    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
       return ResponseEntity.ok().body(service.getAllBooks());
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDto>updateBook(@Valid @RequestParam long id,@RequestBody BookDto dto){

        Book book=dto.toBook(dto);
        var b=dto.toDto(service.updateBook(id,book));
        return ResponseEntity.ok().body(b);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){
        return ResponseEntity.ok().body(service.getBookById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable long id){
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Integer> getStockById(@PathVariable long id){
        return ResponseEntity.ok().body(service.getStockById(id));
    }

}

