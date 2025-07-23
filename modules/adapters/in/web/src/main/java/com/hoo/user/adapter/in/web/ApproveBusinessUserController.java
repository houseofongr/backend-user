package com.hoo.user.adapter.in.web;

import com.hoo.user.api.in.ApproveBusinessUserUseCase;
import com.hoo.user.api.in.dto.ApproveBusinessUserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ApproveBusinessUserController {

    final ApproveBusinessUserUseCase useCase;

    @PostMapping("/users/{userID}/approve/{approve}")
    ResponseEntity<ApproveBusinessUserResult> approve(
            @PathVariable UUID userID,
            @PathVariable Boolean approve
    ) {

        return ResponseEntity.ok(useCase.approve(userID, approve));
    }
}
