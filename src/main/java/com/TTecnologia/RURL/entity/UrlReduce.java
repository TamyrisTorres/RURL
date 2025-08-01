package com.TTecnologia.RURL.entity;


import jakarta.persistence.*;

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
}
