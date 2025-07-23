package com.hoo.user.adapter.in.web;

import com.hoo.common.internal.api.user.GetUserInfoAPI;
import com.hoo.user.api.in.ApproveBusinessUserUseCase;
import com.hoo.user.api.in.CheckExistNicknameUseCase;
import com.hoo.user.api.in.RegisterBusinessUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
@ComponentScan(basePackages = "com.hoo.user.adapter.in.web")
public class WebMvcTestMockContext {

    @Bean
    public ApproveBusinessUserUseCase approveBusinessUserUseCase() {
        return mock(ApproveBusinessUserUseCase.class);
    }

    @Bean
    public CheckExistNicknameUseCase checkExistNicknameUseCase() {
        return mock(CheckExistNicknameUseCase.class);
    }

    @Bean
    public RegisterBusinessUserUseCase registerBusinessUserUseCase() {
        return mock(RegisterBusinessUserUseCase.class);
    }

    @Bean
    public GetUserInfoAPI getUserInfoAPI() {
        return mock(GetUserInfoAPI.class);
    }

}
