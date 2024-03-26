package com.study.sos_backend.membership.dto;

import com.study.sos_backend.membership.entity.Membership;
import com.study.sos_backend.membership.enums.MembershipType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BusinessMemberShipResponseDto {

    private Long businessId;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private MembershipType membershipType;


    public BusinessMemberShipResponseDto(Long businessId, Membership membership) {
        this.businessId = businessId;
        this.startDateTime = membership.getStartDateTime();
        this.endDateTime = membership.getEndDateTime();
        this.membershipType = membership.getType();
    }
}
