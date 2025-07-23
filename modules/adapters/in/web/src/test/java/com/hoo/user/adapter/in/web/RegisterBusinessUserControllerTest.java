package com.hoo.user.adapter.in.web;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.user.api.in.RegisterBusinessUserUseCase;
import com.hoo.user.api.in.dto.RegisterBusinessUserResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegisterBusinessUserControllerTest extends DocumentationTest {

    @Autowired
    private RegisterBusinessUserUseCase registerBusinessUserUseCase;

    @Test
    @DisplayName("비즈니스 회원가입")
    void registerBusinessUser() throws Exception {

        //language=JSON
        String body = """
                     { 
                        "email": "test@example.com", 
                        "password": "temp2143@",
                        "nickname": "temp_user123",
                        "termsOfUseConsent": true,
                        "personalInfoConsent": true
                     }
                """;

        when(registerBusinessUserUseCase.create(argThat(command -> 
                command.email().equals("test@example.com") &&
                command.password().equals("temp2143@") &&
                command.nickname().equals("temp_user123") &&
                command.termsOfUseConsent().equals(true) &&
                command.personalInfoConsent().equals(true))))
                .thenReturn(new RegisterBusinessUserResult(
                        UuidCreator.getTimeOrderedEpoch(),
                        "test@example.com",
                        "temp_user123",
                        true, true)
                );

        mockMvc.perform(post("/users/business")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(201))
                .andDo(document("register-business-user",
                        requestFields(
                                fieldWithPath("email").description("비즈니스 사용자의 이메일입니다. +" + "\n" +
                                                                   "* 인증 완료된 이메일만 가능합니다."),
                                fieldWithPath("password").description("사용할 비밀번호입니다."),
                                fieldWithPath("nickname").description("사용할 닉네임입니다."),
                                fieldWithPath("termsOfUseConsent").description("이용약관 동의 여부입니다."),
                                fieldWithPath("personalInfoConsent").description("개인정보 제공 동의여부입니다.")
                        ),
                        responseFields(
                                fieldWithPath("userID").description("임시 회원가입된 사용자의 ID입니다.(관리자 승인 후 활성화)"),
                                fieldWithPath("email").description("회원가입한 사용자의 이메일입니다."),
                                fieldWithPath("nickname").description("회원가입한 사용자의 닉네임입니다."),
                                fieldWithPath("termsOfUseConsent").description("회원가입한 사용자의 이용약관 동의 여부입니다."),
                                fieldWithPath("personalInfoConsent").description("회원가입한 사용자의 개인정보 제공 동의여부입니다.")
                        )
                ));
    }
}