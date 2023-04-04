package booksApplication.service.impl;

import booksApplication.config.URLConfig;
import booksApplication.model.request.BooksRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BooksServiceImpl {

    @Autowired
    URLConfig urlConfig;

    public String processor(String book){
        String body = null;
        try {
            //connection
            String url = urlConfig.getUrl() + "/volumes?q=" + book;
            URI path = URI.create(url);

            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(path).GET().build();

            //response api
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            body = response.body();
        }catch(Exception e){
            System.out.println("Message Error:" + e);
        }
        return body;
    }

}
