package booksApplication.controller;

import booksApplication.config.URLConfig;
import booksApplication.model.request.BooksRequest;
import booksApplication.model.response.BooksResponse;
import booksApplication.service.impl.BooksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.*;

@RestController
@RequestMapping("/books/")
@SuppressWarnings("all")
public class BooksController {
    @Autowired
    BooksServiceImpl booksService;

    @Autowired
    URLConfig urlConfig;

    @GetMapping(value = "/volumes")
    @Description("Search any volume using the book param.")
    public ResponseEntity<String> getVolume(@RequestParam String book){
        String body = booksService.processor(book);
        return new ResponseEntity<>(body, HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = "/healthcheck")
    @Description("Application health check")
    public ResponseEntity<Health> healthCheck() throws IOException {
       String url = new URL(urlConfig.getUrl()).getHost();
        try(Socket socket =
                    SSLSocketFactory.getDefault().createSocket(url, 443)) {
        } catch (MalformedURLException | NoRouteToHostException | UnknownHostException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Health.down().withDetail("Error: ", e.getMessage()).build());
        }
        return ResponseEntity.ok(Health.up().build());
    }
}
