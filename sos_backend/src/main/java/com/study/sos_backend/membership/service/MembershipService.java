package com.study.sos_backend.membership.service;

import com.study.sos_backend.business.dto.BusinessInfoResponseDto;
import com.study.sos_backend.business.dto.BusinessUpgradeRequestDto;
import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.business.repository.BusinessRepository;
import com.study.sos_backend.membership.dto.BusinessMemberShipResponseDto;
import com.study.sos_backend.membership.entity.Membership;
import com.study.sos_backend.membership.enums.MembershipType;
import com.study.sos_backend.membership.repository.MemberShipRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MembershipService {

    private final BusinessRepository businessRepository;
    private final MemberShipRepository memberShipRepository;

    public BusinessMemberShipResponseDto getMemberShip(Long businessId) throws IllegalAccessException {
        Membership membership = businessRepository.findById(businessId).orElseThrow(EntityNotFoundException::new).getMembership();
        if (membership.getType() == MembershipType.FREE) {
            throw new IllegalAccessException();
        }
        return new BusinessMemberShipResponseDto(businessId, membership);
    }

    public BusinessInfoResponseDto upgradeBusiness(BusinessUpgradeRequestDto requestDto) {
        Business business = businessRepository.findById(requestDto.getId()).orElseThrow(EntityNotFoundException::new);
        business.upgrade(requestDto.getMonths());

        return new BusinessInfoResponseDto(businessRepository.save(business));
    }

    // 매일 자정마다 실행
    @Scheduled(cron = "0 0 0 * * *")
    public void checkEndDateAndType() {
        log.info("Checking end date and type Membership");
        LocalDateTime now = LocalDateTime.now();
        List<Membership> expiredMemberships = memberShipRepository.findAllByEndDateTimeBeforeAndType(now, MembershipType.PRO);
        if (!expiredMemberships.isEmpty()) {
            for (Membership membership : expiredMemberships) {
                membership.changeFreeType();
            }
        }else{
            log.info("수정할 멤버십이 없습니다.");
        }

    }
}
