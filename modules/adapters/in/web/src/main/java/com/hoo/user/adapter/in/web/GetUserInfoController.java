package com.hoo.user.adapter.in.web;

import com.hoo.common.internal.api.user.GetUserInfoAPI;
import com.hoo.common.internal.api.user.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GetUserInfoController {

    private final GetUserInfoAPI getUserInfoAPI;

    @GetMapping("/users/{userID}/info")
    ResponseEntity<UserInfo> get(@PathVariable UUID userID) {
        return ResponseEntity.ok(getUserInfoAPI.getUserInfo(userID));
    }
}
