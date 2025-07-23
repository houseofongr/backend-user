package com.hoo.user.adapter.out.cache;

import com.hoo.user.api.out.LoadEmailAuthnPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Objects;

import static com.hoo.common.enums.CacheKeys.*;

@RequiredArgsConstructor
public class RedisCacheAdapter implements LoadEmailAuthnPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean isAuthenticated(String email) {
        return Objects.equals(valueOperations().get(EMAIL_AUTHN_STATUS_PREFIX.getKey() + email), EMAIL_AUTHENTICATED.getKey());
    }

    private ValueOperations<String, String> valueOperations() {
        return redisTemplate.opsForValue();
    }
}
