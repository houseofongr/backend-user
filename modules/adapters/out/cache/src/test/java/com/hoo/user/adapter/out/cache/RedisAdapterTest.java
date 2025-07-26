package com.hoo.user.adapter.out.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Set;

import static com.hoo.common.enums.CacheKeys.*;
import static org.assertj.core.api.Assertions.assertThat;

@RedisTest
class RedisAdapterTest {

    @Autowired
    RedisAdapter sut;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @BeforeEach
    void clear() {
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }

    @Test
    @DisplayName("이메일 인증여부 확인 테스트")
    void testLoadAuthnStatus() {
        // given
        String email = "test@example.com";

        // when 1 : not saved
        assertThat(sut.isAuthenticated(email)).isFalse();

        // when 2 : saved
        redisTemplate.opsForValue().set(EMAIL_AUTHN_STATUS_PREFIX.getKey() + email, EMAIL_AUTHENTICATED.getKey(), Duration.ofSeconds(3600));

        // then
        assertThat(sut.isAuthenticated(email)).isTrue();
    }
}