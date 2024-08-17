package com.ust.service;

import com.ust.domain.Book;
import java.util.*;

public interface BookService {
    Book createBook (Book book);
    List<Book> getAllBooks();
    Book getBookById(long id);
    Book updateBook(long id,Book book);
    void deleteBook(long id);
}
