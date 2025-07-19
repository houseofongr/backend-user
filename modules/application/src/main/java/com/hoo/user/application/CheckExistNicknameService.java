package com.hoo.user.application;

import com.hoo.user.api.in.web.usecase.CheckExistNicknameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CheckExistNicknameService implements CheckExistNicknameUseCase {

    @Override
    public boolean isExist(String nickname) {
        return false;
    }
}
