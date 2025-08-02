package com.TTecnologia.RURL.service;

import com.TTecnologia.RURL.dao.UrlReduceDao;
import com.TTecnologia.RURL.entity.UrlReduce;
import com.TTecnologia.RURL.exception.UrlInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.*;

@Service
public class UrlReduceService {

    private static final String DOMAIN = "http://localhost:8080/api/";

    @Autowired
    private UrlReduceDao urlReduceDao;

    public String reduceUrl(String urlOrigin){
        Optional<UrlReduce> urlShortOptional =
                urlReduceDao.findByUrlOrigin(urlOrigin);

        if (urlShortOptional.isPresent()){
            return DOMAIN + urlShortOptional.get().getUrlShort();
        }

        String urlShortened = buildUrlShort();
        urlReduceDao.save(new UrlReduce(urlOrigin, urlShortened));

        return DOMAIN + urlShortened;
    }

    private String buildUrlShort(){
        UUID uuid = UUID.randomUUID();
        byte[] bytes = uuid.toString().getBytes();
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(bytes).substring(0,8);
    }

    public Map<String, Object> getStatus(String urlShort) {
        Optional<UrlReduce> urlReduceOptional = urlReduceDao.findByUrlShort(urlShort);

        if (urlReduceOptional.isEmpty()){
            throw new RuntimeException("Url não encontrada");
        }

        UrlReduce urlReduce = new UrlReduce();

        return urlReduce.getData(urlReduceOptional.get());
    }
}
