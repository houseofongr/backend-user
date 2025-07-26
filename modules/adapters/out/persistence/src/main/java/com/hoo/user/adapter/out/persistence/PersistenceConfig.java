package com.hoo.user.adapter.out.persistence;

import com.hoo.user.adapter.out.persistence.config.HibernateCustomNamingStrategy;
import com.hoo.user.adapter.out.persistence.repository.UserJpaRepository;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan
public class PersistenceConfig {

    @Bean
    JpaCommandAdapter jpaCommandAdapter(
            UserJpaRepository userJpaRepository,
            PersistenceMapper persistenceMapper
    ) {
        return new JpaCommandAdapter(userJpaRepository, persistenceMapper);
    }

    @Bean
    public JpaQueryAdapter jpaQueryAdapter(
            UserJpaRepository userJpaRepository,
            PersistenceMapper persistenceMapper
    ) {
        return new JpaQueryAdapter(userJpaRepository, persistenceMapper);
    }

    @Bean
    public PersistenceMapper userMapper() {
        return new PersistenceMapper();
    }

    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy() {
        return new HibernateCustomNamingStrategy();
    }
}
