package com.TTecnologia.RURL.service;

import com.TTecnologia.RURL.dao.UrlReduceDao;
import com.TTecnologia.RURL.entity.UrlReduce;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlRedirectServiceTest {

    @Mock
    UrlReduceDao urlReduceDao;

    @InjectMocks
    UrlRedirectService urlRedirectService;

    @Nested
    class UrlRedirect{

        @Test
        void shouldRedirectToOriginalUrl_WhenShortUrlExists() {
            String shortUrl = "abc123";
            UrlReduce urlReduce = new UrlReduce();
            urlReduce.setUrlShort(shortUrl);
            urlReduce.setUrlOrigin("https://siteoriginal.com");
            urlReduce.setAccessCount(5L);
            urlReduce.setLocalDateTime(LocalDateTime.now().minusDays(2));

            when(urlReduceDao.findByUrlShort(shortUrl))
                    .thenReturn(Optional.of(urlReduce));

            String result = urlRedirectService.redirectUrlToOrigin(shortUrl);

            assertEquals("https://siteoriginal.com", result);
            assertEquals(6, urlReduce.getAccessCount());
            verify(urlReduceDao).save(urlReduce);
        }

        @Test
        void shouldThrowException_WhenShortUrlDoesNotExist() {
            String shortUrl = "naoExiste";

            when(urlReduceDao.findByUrlShort(shortUrl))
                    .thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(
                    RuntimeException.class, () -> {
                urlRedirectService.redirectUrlToOrigin(shortUrl);
            });

            assertEquals("Url não encontrada",
                    exception.getMessage());
        }
    }
}