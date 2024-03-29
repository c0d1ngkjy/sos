package com.study.sos_backend.business.service;

import com.study.sos_backend.business.dto.HairDesignerInfoCreateRequestDto;
import com.study.sos_backend.business.dto.HairDesignerInfoResponseDto;
import com.study.sos_backend.business.entity.Business;
import com.study.sos_backend.business.entity.hair.HairDesigner;
import com.study.sos_backend.business.repository.BusinessRepository;
import com.study.sos_backend.business.repository.HairDesignerRepository;
import com.study.sos_backend.business.repository.HairPriceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HairShopService {
    private final HairDesignerRepository designerRepository;
    private final HairPriceRepository priceRepository;
    private final BusinessRepository businessRepository;


    // 디자이너 추가
    public HairDesignerInfoResponseDto addDesigner(String email, HairDesignerInfoCreateRequestDto requestDto) {
        Business business = businessRepository.findById(requestDto.getBusinessId()).orElseThrow(EntityNotFoundException::new);
        // 비즈니스 오우너 확인
        businessRepository.findByOwnerEmailAndId(requestDto.getBusinessId(), email).orElseThrow(EntityNotFoundException::new);

        return new HairDesignerInfoResponseDto(designerRepository
                .save(HairDesigner
                        .toEntity(requestDto, business)
                ));
    }

    // TODO 디자이너 수정

    // 디자이너 삭제
    public void deleteDesigner(Long id) {
        HairDesigner designer = designerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        designerRepository.delete(designer);
    }


}
