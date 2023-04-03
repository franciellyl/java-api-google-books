package booksApplication.controller;

import booksApplication.config.URLConfig;
import booksApplication.model.request.BooksRequest;
import booksApplication.model.response.BooksResponse;
import booksApplication.service.impl.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    @Autowired
    BooksServiceImpl booksService;

    @Autowired
    BooksResponse booksResponse;

    @GetMapping(value = "/volume")
    public ResponseEntity<BooksResponse> getVolume(BooksRequest booksRequest){
        booksService.processor(booksRequest);
        return new ResponseEntity<>(booksResponse, HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = "/healthcheck")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("Application Up.", HttpStatus.valueOf(200));
    }
}
