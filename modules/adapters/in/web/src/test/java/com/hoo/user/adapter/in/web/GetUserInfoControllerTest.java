package com.hoo.user.adapter.in.web;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.common.internal.api.user.GetUserInfoAPI;
import com.hoo.common.internal.api.user.dto.UserInfo;
import com.hoo.user.domain.vo.UserStatus;
import com.hoo.user.domain.vo.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetUserInfoControllerTest extends DocumentationTest {

    @Autowired
    private GetUserInfoAPI getUserInfoAPI;

    @Test
    @DisplayName("사용자 정보 조회")
    void getUserInfo() throws Exception {

        UUID userID = UuidCreator.getTimeOrderedEpoch();

        when(getUserInfoAPI.getUserInfo(userID)).thenReturn(new UserInfo(
                userID,
                true, true,
                "test@example.com",
                "leaf",
                UserType.BUSINESS.name(),
                UserStatus.ACTIVATE.name(),
                ZonedDateTime.now().toEpochSecond()
        ));

        mockMvc.perform(get("/users/{userID}/info", userID))
                .andExpect(status().isOk())
                .andDo(document("get-user-info",
                        pathParameters(
                                parameterWithName("userID").description("조회할 사용자의 ID입니다.")
                        ),
                        responseFields(
                                fieldWithPath("id").description("조회된 사용자의 ID입니다."),
                                fieldWithPath("termsOfUseConsent").description("이용약관 동의여부입니다."),
                                fieldWithPath("personalInfoConsent").description("개인정보 활용 동의여부입니다."),
                                fieldWithPath("email").description("이메일 주소입니다."),
                                fieldWithPath("nickname").description("닉네임입니다."),
                                fieldWithPath("userType").description("사용자 타입입니다."),
                                fieldWithPath("userStatus").description("사용자 상태입니다."),
                                fieldWithPath("registeredAt").description("등록일자입니다.")
                        )
                ));
    }
}