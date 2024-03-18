package com.study.sos_backend.common.entity;

import com.study.sos_backend.common.dto.LocateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Locate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    private Double latitude;

    private Double longitude;

    public Locate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Locate toEntity(LocateDto locateDto){
        return new Locate(locateDto.getLatitude(), locateDto.getLongitude());
    }
}
