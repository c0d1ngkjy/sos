package com.study.sos_backend.membership.service;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.dto.BusinessUpgradeRequestDto;
import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.business.repository.BusinessRepository;
import com.study.sos_backend.membership.dto.BusinessMemberShipResponseDto;
import com.study.sos_backend.membership.entity.Membership;
import com.study.sos_backend.membership.repository.MemberShipRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final BusinessRepository businessRepository;
    private final MemberShipRepository memberShipRepository;

    public BusinessMemberShipResponseDto getMemberShip(Long businessId) {
        Membership membership = businessRepository.findById(businessId).orElseThrow(EntityNotFoundException::new).getMembership();

        return new BusinessMemberShipResponseDto(businessId, membership);
    }

    public BusinessInfoResponseDto upgradeBusiness(BusinessUpgradeRequestDto requestDto) {
        Business business = businessRepository.findById(requestDto.getId()).orElseThrow(EntityNotFoundException::new);
        business.upgrade(requestDto.getMonths());

        return new BusinessInfoResponseDto(businessRepository.save(business));
    }
}
