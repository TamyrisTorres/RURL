package com.TTecnologia.RURL.service;

import com.TTecnologia.RURL.dao.UrlReduceDao;
import com.TTecnologia.RURL.entity.UrlReduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlRedirectService {

    @Autowired
    private UrlReduceDao urlReduceDao;

    public String redirectUrlToOrigin(String urlShort){
        Optional<UrlReduce> urlReduceOptional = urlReduceDao.findByUrlShort(urlShort);

        if (urlReduceOptional.isEmpty()){
            throw new RuntimeException("Url não encontrada");
        }

        UrlReduce urlReduce = urlReduceOptional.get();
        urlReduce.setAccessCount(urlReduce.getAccessCount() + 1);
        urlReduceDao.save(urlReduce);

        return urlReduce.getUrlOrigin();
    }
}
