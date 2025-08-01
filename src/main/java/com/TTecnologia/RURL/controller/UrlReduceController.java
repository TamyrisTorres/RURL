package com.TTecnologia.RURL.controller;

import com.TTecnologia.RURL.service.UrlReduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rulr")
public class UrlReduceController {

    @Autowired
    private UrlReduceService urlReduceService;

    @PostMapping
    public ResponseEntity<String> reduceUrl(@RequestBody String url){
        return ResponseEntity.ok(urlReduceService.reduceUrl(url));
    }
}
