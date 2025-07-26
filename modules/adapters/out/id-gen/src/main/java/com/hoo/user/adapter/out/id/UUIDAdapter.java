package com.hoo.user.adapter.out.id;

import com.github.f4b6a3.uuid.UuidCreator;
import com.hoo.common.IssueIDPort;

import java.util.UUID;

public class UUIDAdapter implements IssueIDPort {

    @Override
    public UUID issueNewID() {
        return UuidCreator.getTimeOrderedEpoch();
    }
}
