package com.study.sos_backend.business.entity.hair;

import com.study.sos_backend.business.dto.HairDesignerInfoCreateRequestDto;
import com.study.sos_backend.business.entity.Business;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HairDesigner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DESINGER_NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Business business;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HairPrice> priceList;

    @Builder
    public HairDesigner(String name, Business business, List<HairPrice> priceList) {
        this.name = name;
        this.business = business;
        this.priceList = priceList;
    }


    public static HairDesigner toEntity(HairDesignerInfoCreateRequestDto requestDto, Business business){
        return HairDesigner.builder()
                .name(requestDto.getName())
                .business(business)
                .build();
    }
}
