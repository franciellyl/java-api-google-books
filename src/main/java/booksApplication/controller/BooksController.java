package booksApplication.controller;

import booksApplication.model.request.BooksRequest;
import booksApplication.service.impl.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    @Autowired
    BooksServiceImpl booksService;

    @GetMapping(value = "/volume")
    public void getVolume(BooksRequest booksRequest){
        booksService.processor(booksRequest);
    }

    @GetMapping(value = "/healthcheck")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("Application Up.", HttpStatus.valueOf(200));
    }
}
