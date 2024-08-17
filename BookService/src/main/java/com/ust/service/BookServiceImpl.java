package com.ust.service;

import com.ust.domain.Book;
import com.ust.exception.BookNotFoundException;
import com.ust.exception.DuplicateBookException;
import com.ust.exception.StockUnavailableException;
import com.ust.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService{
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book createBook(Book book) {
        if (repository.findAll().stream().anyMatch(existingbook->existingbook.getTitle().equals(book.getTitle()))){
            throw new DuplicateBookException("Duplicate book "+book.getTitle());
        }
        return repository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBookById(long id) {
        return repository.findById(id).orElseThrow(()->new BookNotFoundException("BookId not found"+id));
    }

    @Override
    public Book updateBook(long id, Book book) {
        Book book1=getBookById(id);
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setPrice(book.getPrice());
            book1.setStock(book.getStock());
           return repository.save(book1);
    }

    @Override
    public void deleteBook(long id) {
        Book book1=getBookById(id);
            repository.deleteById(id);
    }

    @Override
    public int getStockById(long id) {
        Book book=getBookById(id);
        var st=book.getStock();
        if(st==0){
            throw new StockUnavailableException("Stock not available");
        }
        return (int) st;
    }
}
