package com.hoo.user.adapter.in.web;

import com.hoo.user.api.in.CheckExistNicknameUseCase;
import com.hoo.user.api.in.dto.CheckExistNicknameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CheckExistNicknameController {

    private final CheckExistNicknameUseCase useCase;

    @GetMapping("/users/nickname/{nickname}")
    ResponseEntity<CheckExistNicknameResult> check(
            @PathVariable String nickname
    ) {
        return ResponseEntity.ok(useCase.isExist(nickname));
    }
}

