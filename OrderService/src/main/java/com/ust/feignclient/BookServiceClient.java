package com.ust.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service")
public interface BookServiceClient {
    @GetMapping("books/{id}/stocks")
    int getStockFromBookId(@PathVariable long id);
}
