package com.hoo.user.api.in;

import com.hoo.user.api.in.dto.CheckExistNicknameResult;

public interface CheckExistNicknameUseCase {
    CheckExistNicknameResult isExist(String nickname);
}
