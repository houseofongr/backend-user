package com.hoo.user.adapter.in.web;

import com.hoo.user.api.in.RegisterBusinessUserUseCase;
import com.hoo.user.api.in.dto.RegisterBusinessUserCommand;
import com.hoo.user.api.in.dto.RegisterBusinessUserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterBusinessUserController {

    private final RegisterBusinessUserUseCase useCase;

    @PostMapping("/users/business")
    ResponseEntity<RegisterBusinessUserResult> register(
            @RequestBody RegisterBusinessUserCommand command
            ) {
        return new ResponseEntity<>(useCase.create(command), HttpStatus.CREATED);
    }
}
