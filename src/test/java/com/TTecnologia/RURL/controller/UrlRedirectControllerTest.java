package com.TTecnologia.RURL.controller;

import com.TTecnologia.RURL.service.UrlRedirectService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlRedirectControllerTest {

    @InjectMocks
    UrlRedirectController urlRedirectController;

    @Mock
    UrlRedirectService urlRedirectService;

    @Nested
    class UrlRedirect{

        @Test
        void shouldRedirectToOriginalUrl_WhenUrlExists() {
            String shortUrl = "abc123";
            String originalUrl = "https://example.com";
            when(urlRedirectService.redirectUrlToOrigin(shortUrl))
                    .thenReturn(originalUrl);

            ResponseEntity<Void> response =
                    urlRedirectController.redirectUrl(shortUrl);

            assertEquals(HttpStatus.FOUND, response.getStatusCode());
            assertEquals(URI.create(originalUrl),
                    response.getHeaders().getLocation());
            verify(urlRedirectService).redirectUrlToOrigin(shortUrl);
        }

        @Test
        void shouldReturnNotFound_WhenUrlDoesNotExist() {
            String shortUrl = "abc123";
            when(urlRedirectService.redirectUrlToOrigin(shortUrl))
                    .thenReturn(null);

            ResponseEntity<Void> response =
                    urlRedirectController.redirectUrl(shortUrl);

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertNull(response.getHeaders().getLocation());
            verify(urlRedirectService).redirectUrlToOrigin(shortUrl);
        }
    }
}