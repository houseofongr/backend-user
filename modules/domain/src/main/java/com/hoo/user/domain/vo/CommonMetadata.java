package com.hoo.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class CommonMetadata {

    private final ZonedDateTime createdTime;
    private final ZonedDateTime updatedTime;

    public CommonMetadata update() {
        return new CommonMetadata(createdTime, ZonedDateTime.now());
    }
}
