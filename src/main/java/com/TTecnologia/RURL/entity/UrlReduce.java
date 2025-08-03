package com.TTecnologia.RURL.entity;


import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "UrlReduce")
public class UrlReduce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "urlOrigin")
    private String urlOrigin;

    @Column(name = "urlShort", unique = true)
    private String urlShort;

    private LocalDateTime localDateTime;

    private Long accessCount = 0L;

    public UrlReduce() {
    }

    public UrlReduce(String urlOrigin, String urlShort) {
        this.urlOrigin = urlOrigin;
        this.urlShort = urlShort;
    }

    public Integer getId() {
        return id;
    }

    public String getUrlOrigin() {
        return urlOrigin;
    }

    public void setUrlOrigin(String urlOrigin) {
        this.urlOrigin = urlOrigin;
    }

    public String getUrlShort() {
        return urlShort;
    }

    public void setUrlShort(String urlShort) {
        this.urlShort = urlShort;
    }

    public Long getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Long accessCount) {
        this.accessCount = accessCount;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @PrePersist
    public void prePersist(){
        this.localDateTime = LocalDateTime.now();
    }

    public double getAccessesPerDay(){
        long days = Duration.between(localDateTime, LocalDateTime.now()).toDays();
        if (days == 0) days = 1;
        return (double) accessCount / days;
    }

    public Map<String, Object> getData(UrlReduce urlReduce){
        Map<String, Object> dataUrl = new HashMap<>();
        dataUrl.put("urlOriginal: ", urlReduce.getUrlOrigin());
        dataUrl.put("urlShort: ", urlReduce.getUrlShort());
        dataUrl.put("Total de acessos: ", urlReduce.getAccessCount());
        dataUrl.put("Media de acessos por dia: ", urlReduce.getAccessesPerDay());

        return dataUrl;
    }
}
