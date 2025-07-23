package com.hoo.user.adapter.in.web;

import com.hoo.user.api.in.CheckExistNicknameUseCase;
import com.hoo.user.api.in.dto.CheckExistNicknameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CheckExistNicknameControllerTest extends DocumentationTest {

    @Autowired
    private CheckExistNicknameUseCase checkExistNicknameUseCase;

    @Test
    @DisplayName("닉네임 중복여부 조회")
    void checkExistNickname() throws Exception {

        when(checkExistNicknameUseCase.isExist("leaf")).thenReturn(new CheckExistNicknameResult(true));

        mockMvc.perform(get("/users/nickname/{nickname}", "leaf"))
                .andExpect(status().is(200))
                .andDo(document("check-exist-nickname",
                        pathParameters(
                                parameterWithName("nickname").description("존재여부를 확인할 닉네임입니다.")
                        ),
                        responseFields(
                                fieldWithPath("exist").description("해당 닉네임이 존재하는지 여부입니다.")
                        )
                ));
    }

}