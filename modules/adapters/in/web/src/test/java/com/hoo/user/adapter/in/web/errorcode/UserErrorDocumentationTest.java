package com.hoo.user.adapter.in.web.errorcode;

import com.hoo.user.adapter.in.web.DocumentationTest;
import com.hoo.user.application.exception.AdapterErrorCode;
import com.hoo.user.application.exception.ApplicationErrorCode;
import com.hoo.user.application.exception.DomainErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.hoo.user.adapter.in.web.errorcode.ErrorCodeResponseFieldsSnippet.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserErrorDocumentationTest extends DocumentationTest {

    @Test
    @DisplayName("도메인 계층 에러 코드")
    void domainErrorCode() throws Exception {
        mockMvc.perform(get("/users/domain-error-codes"))
                .andExpect(status().is(200))
                .andDo(document("domain-error-code",
                        responseFields(
                                fieldWithPath("*.code").description("에러코드 이름입니다."),
                                fieldWithPath("*.message").description("에러코드에 대한 설명입니다."),
                                fieldWithPath("*.httpStatusCode").description("HTTP 상태 코드입니다."),
                                fieldWithPath("*.httpStatusReason").description("상태 코드의 발생 원인입니다.")
                        ), errorCodeResponseFields("error-code-response",
                                errorCodeFieldDescriptors(DomainErrorCode.values())
                        )
                ));
    }

    @Test
    @DisplayName("서비스 계층 에러 코드")
    void applicationErrorCode() throws Exception {
        mockMvc.perform(get("/users/application-error-codes"))
                .andExpect(status().is(200))
                .andDo(document("application-error-code",
                        responseFields(
                                fieldWithPath("*.code").description("에러코드 이름입니다."),
                                fieldWithPath("*.message").description("에러코드에 대한 설명입니다."),
                                fieldWithPath("*.httpStatusCode").description("HTTP 상태 코드입니다."),
                                fieldWithPath("*.httpStatusReason").description("상태 코드의 발생 원인입니다.")
                        ), errorCodeResponseFields("error-code-response",
                                errorCodeFieldDescriptors(ApplicationErrorCode.values())
                        )
                ));
    }

    @Test
    @DisplayName("어댑터 계층 에러 코드")
    void adapterErrorCode() throws Exception {
        mockMvc.perform(get("/users/adapter-error-codes"))
                .andExpect(status().is(200))
                .andDo(document("adapter-error-code",
                        responseFields(
                                fieldWithPath("*.code").description("에러코드 이름입니다."),
                                fieldWithPath("*.message").description("에러코드에 대한 설명입니다."),
                                fieldWithPath("*.httpStatusCode").description("HTTP 상태 코드입니다."),
                                fieldWithPath("*.httpStatusReason").description("상태 코드의 발생 원인입니다.")
                        ), errorCodeResponseFields("error-code-response",
                                errorCodeFieldDescriptors(AdapterErrorCode.values())
                        )
                ));
    }
}
