package com.TTecnologia.RURL.service;

import com.TTecnologia.RURL.dao.UrlReduceDao;
import com.TTecnologia.RURL.entity.UrlReduce;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlReduceServiceTest {

    @Mock
    UrlReduceDao urlReduceDao;

    @InjectMocks
    UrlReduceService urlReduceService;

    @Nested
    class reduceUrlService {

        @Test
        void shouldReturnUrlShortened() {
            String originalUrl = "http://example.com";

            when(urlReduceDao.findByUrlOrigin(originalUrl))
                    .thenReturn(Optional.empty());

            when(urlReduceDao.save(any()))
                    .then(AdditionalAnswers.returnsFirstArg());

            var response = urlReduceService.reduceUrl(originalUrl);

            assertNotNull(response);
            assertNotEquals(originalUrl, response);
        }

        @Test
        void shouldReturnExistingShortUrl_WhenUrlAlreadyExists() {
            String originalUrl = "http://example.com";
            String existingShort = "abc123";

            UrlReduce existing = new UrlReduce(originalUrl, existingShort);

            when(urlReduceDao.findByUrlOrigin(originalUrl))
                    .thenReturn(Optional.of(existing));

            var response = urlReduceService.reduceUrl(originalUrl);

            assertEquals("http://localhost:8080/api/" +
                    existingShort, response);
        }

        @Test
        void shouldReturnStatusData_WhenShortUrlExists() {
            String shortUrl = "xyz789";
            UrlReduce urlReduce = new UrlReduce("http://example.com", shortUrl);
            urlReduce.setAccessCount(10L);
            urlReduce.setLocalDateTime(LocalDateTime.now().minusDays(2));

            when(urlReduceDao.findByUrlShort(shortUrl))
                    .thenReturn(Optional.of(urlReduce));

            var status = urlReduceService.getStatus(shortUrl);

            assertNotNull(status);
            assertEquals("http://example.com",
                    status.get("urlOriginal: "));
        }

        @Test
        void shouldThrowException_WhenShortUrlNotFound() {
            String shortUrl = "notfound";

            when(urlReduceDao.findByUrlShort(shortUrl))
                    .thenReturn(Optional.empty());

            RuntimeException ex = assertThrows(
                    RuntimeException.class, () -> {
                urlReduceService.getStatus(shortUrl);
            });

            assertEquals("Url não encontrada", ex.getMessage());
        }

        @Test
        void shouldGenerateShortUrl_WithLengthOf8() {
            String originalUrl = "http://test.com";

            when(urlReduceDao.findByUrlOrigin(originalUrl))
                    .thenReturn(Optional.empty());
            when(urlReduceDao.save(any()))
                    .then(AdditionalAnswers.returnsFirstArg());

            String shortUrl = urlReduceService.reduceUrl(originalUrl);
            String shortPart = shortUrl
                    .replace("http://localhost:8080/api/", "");

            assertEquals(8, shortPart.length());
        }
    }

}