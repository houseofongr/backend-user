package com.hoo.user.adapter.in.web;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.user.api.in.ApproveBusinessUserUseCase;
import com.hoo.user.api.in.dto.ApproveBusinessUserResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ApproveBusinessUserControllerTest extends DocumentationTest {

    @Autowired
    private ApproveBusinessUserUseCase approveBusinessUserUseCase;

    @Test
    @DisplayName("비즈니스 사용자 승인")
    void approveBusinessUser() throws Exception {

        UUID userID = UuidCreator.getTimeOrderedEpoch();

        when(approveBusinessUserUseCase.approve(userID, true))
                .thenReturn(new ApproveBusinessUserResult(userID, "leaf", true));

        mockMvc.perform(post("/users/{userID}/approve/{approve}", userID, true))
                .andExpect(status().is(200))
                .andDo(document("approve-business-user",
                        pathParameters(
                                parameterWithName("userID").description("승인할 비즈니스 회원의 ID입니다."),
                                parameterWithName("approve").description("해당 회원을 승인할지, 거부할지 여부입니다.")
                        ),
                        responseFields(
                                fieldWithPath("userID").description("회원가입된 사용자의 ID입니다."),
                                fieldWithPath("nickname").description("회원가입된 사용자의 닉네임입니다."),
                                fieldWithPath("isApproved").description("승인 및 거절 여부입니다.")
                        )
                ));
    }
}