package ru.otus.timofeev.task3.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class CacheConfig {

    public static final String CACHE_NAME = "UserCache";

    @Bean
    public CacheManager caffeineCacheManager(@Value("${user-cache.maxSize}") long maxSize,
                                             @Value("${user-cache.expireInMinutes}") long expireInMinutes) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setAllowNullValues(false);
        cacheManager.setCacheNames(List.of(CACHE_NAME));
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterAccess(expireInMinutes, TimeUnit.MINUTES)
                .maximumSize(maxSize)
                .recordStats());
        return cacheManager;
    }
}
