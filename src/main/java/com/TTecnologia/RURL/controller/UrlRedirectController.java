package com.TTecnologia.RURL.controller;

import com.TTecnologia.RURL.service.UrlRedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UrlRedirectController {

    @Autowired
    UrlRedirectService urlRedirectService;

    @GetMapping("/{urlShort}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String urlShort){
        String url = urlRedirectService.redirectUrlToOrigin(urlShort);

        if(url != null){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(url)).build();
        }

        return ResponseEntity.notFound().build();
    }
}
