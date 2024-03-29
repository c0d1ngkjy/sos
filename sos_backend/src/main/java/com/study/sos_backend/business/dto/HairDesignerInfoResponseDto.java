package com.study.sos_backend.business.dto;

import com.study.sos_backend.business.entity.hair.HairDesigner;
import lombok.Data;

@Data
public class HairDesignerInfoResponseDto {

    Long id;

    String companyName;

    String name;


    public HairDesignerInfoResponseDto(HairDesigner hairDesigner) {
        this.id = hairDesigner.getId();
        this.companyName = hairDesigner.getBusiness().getCompanyName();
        this.name = hairDesigner.getName();
    }
}
