package com.hoo.user.adapter.out.persistence.entity.vo;

import com.hoo.user.domain.event.BusinessUserApproveEvent;
import com.hoo.user.domain.vo.CommonMetadata;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CommonMetadataJpaValue {

    @Column
    private ZonedDateTime createdTime;

    @Column
    private ZonedDateTime updatedTime;

    public static CommonMetadataJpaValue from(CommonMetadata commonMetadata) {

        return new CommonMetadataJpaValue(
                commonMetadata.getCreatedTime(),
                commonMetadata.getUpdatedTime()
        );
    }

    public void applyEvent(BusinessUserApproveEvent event) {
        this.updatedTime = event.approveAt();
    }
}
