package com.TTecnologia.RURL.controller;

import com.TTecnologia.RURL.exception.UrlInvalidException;
import com.TTecnologia.RURL.service.UrlReduceService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlReduceControllerTest {

    @Mock
    UrlReduceService urlReduceService;

    @InjectMocks
    UrlReduceController urlReduceController;

    @Nested
    class ReduceUrl {

        @Test
        void shouldReturnResponseBodyCorrect() {
            String url = "http://example.com";
            String expectedShortenedUrl = "http://localhost:8080/api/abcd1234";

            when(urlReduceService.reduceUrl(url)).thenReturn(expectedShortenedUrl);

            var response = urlReduceController.reduceUrl(url);

            assertNotNull(response.getBody());
            assertEquals(expectedShortenedUrl, response.getBody());
        }

        @Test
        void shouldReturnHttpOK() {
            String url = "http://example.com";
            when(urlReduceService.reduceUrl(url))
                    .thenReturn("http://localhost:8080/api/abcd1234");

            var response = urlReduceController.reduceUrl(url);

            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void shouldCallServiceWithCorrectParameter() {
            String url = "http://example.com";

            when(urlReduceService.reduceUrl(url))
                    .thenReturn("http://localhost:8080/api/abcd1234");

            urlReduceController.reduceUrl(url);

            verify(urlReduceService, times(1))
                    .reduceUrl(url);
        }

        @Test
        void shouldThrowExceptionWhenUrlIsInvalid() {
            String invalidUrl = "invalid_url";

            assertThrows(UrlInvalidException.class,
                    () -> urlReduceController.reduceUrl(invalidUrl));
            verify(urlReduceService, never()).reduceUrl(any());
        }
    }

    @Nested
    class GetStatus {

        @Test
        void shouldReturnStatusMap() {
            String shortUrl = "abcd1234";
            Map<String, Object> expectedStatus = new HashMap<>();
            expectedStatus.put("originalUrl", "http://example.com");
            expectedStatus.put("clicks", 5L);

            when(urlReduceService.getStatus(shortUrl)).thenReturn(expectedStatus);

            ResponseEntity<Map<String, Object>> response =
                    urlReduceController.getStatus(shortUrl);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(expectedStatus, response.getBody());
        }

        @Test
        void shouldCallServiceWithCorrectShortUrl() {
            String shortUrl = "abcd1234";
            Map<String, Object> dummy = new HashMap<>();
            when(urlReduceService.getStatus(shortUrl)).thenReturn(dummy);

            urlReduceController.getStatus(shortUrl);

            verify(urlReduceService, times(1))
                    .getStatus(shortUrl);
        }
    }
}
