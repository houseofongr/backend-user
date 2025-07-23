package com.hoo.user.adapter.out.persistence;

import com.hoo.user.adapter.out.persistence.command.HandleUserEventAdapter;
import com.hoo.user.adapter.out.persistence.query.LoadUserAdapter;
import com.hoo.user.adapter.out.persistence.query.QueryUserAdapter;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan
public class PersistenceAdapterConfig {

    @Bean
    HandleUserEventAdapter handleUserEventAdapter(
            UserJpaRepository userJpaRepository,
            UserMapper userMapper
    ) {
        return new HandleUserEventAdapter(userJpaRepository, userMapper);
    }

    @Bean
    public QueryUserAdapter queryUserAdapter(
            UserJpaRepository userJpaRepository,
            UserMapper userMapper
    ) {
        return new QueryUserAdapter(userJpaRepository, userMapper);
    }

    @Bean
    public LoadUserAdapter loadUserAdapter(
            UserJpaRepository userJpaRepository,
            UserMapper userMapper
    ) {
        return new LoadUserAdapter(userJpaRepository, userMapper);
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }
}
