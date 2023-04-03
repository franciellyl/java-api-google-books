package booksApplication.service.impl;

import booksApplication.config.URLConfig;
import booksApplication.model.request.BooksRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImpl {

    @Autowired
    URLConfig urlConfig;

    public void processor(BooksRequest booksRequest){
        String url = urlConfig.getUrl() + "/volumes?" + booksRequest.getQ();
        System.out.printf(url);

    }

}
