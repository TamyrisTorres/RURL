package com.TTecnologia.RURL.controller;

import com.TTecnologia.RURL.exception.UrlInvalidException;
import com.TTecnologia.RURL.service.UrlReduceService;
import com.TTecnologia.RURL.util.UrlValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/rurl")
public class UrlReduceController {

    @Autowired
    private UrlReduceService urlReduceService;

    @PostMapping("/reduce")
    public ResponseEntity<String> reduceUrl(@RequestBody String url){

        if (!UrlValid.isValidURL(url)){
            throw new UrlInvalidException("Url inválida");
        }

        return ResponseEntity.ok(urlReduceService.reduceUrl(url));
    }

    @GetMapping("/statusAcess/{urlShort}")
    public ResponseEntity<Map<String, Object>> getStatus(@PathVariable String urlShort){
       return ResponseEntity.ok(urlReduceService.getStatus(urlShort));
    }
}
