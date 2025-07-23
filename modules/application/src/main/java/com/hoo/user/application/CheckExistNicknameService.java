package com.hoo.user.application;

import com.hoo.user.api.in.CheckExistNicknameUseCase;
import com.hoo.user.api.in.dto.CheckExistNicknameResult;
import com.hoo.user.api.out.QueryUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CheckExistNicknameService implements CheckExistNicknameUseCase {

    private final QueryUserPort queryUserPort;

    @Override
    public CheckExistNicknameResult isExist(String nickname) {
        return new CheckExistNicknameResult(queryUserPort.existByName(nickname));
    }
}
