package com.TTecnologia.RURL.dao;

import com.TTecnologia.RURL.entity.UrlReduce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlReduceDao extends JpaRepository<UrlReduce, Integer>{
    Optional<UrlReduce> findByUrlOrigin(String urlOrigin);

    Optional<UrlReduce> findByUrlShort(String urlShort);
}
