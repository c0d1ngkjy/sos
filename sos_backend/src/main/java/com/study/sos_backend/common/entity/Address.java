package com.study.sos_backend.common.entity;

import com.study.sos_backend.common.dto.AddressDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    private String roadAddress;

    private String detailAddress;

    private String postalCode;


    public Address(String roadAddress, String detailAddress, String postalCode) {
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.postalCode = postalCode;
    }

    public static Address toEntity(AddressDto addressDto) {
        return new Address(addressDto.getRoadAddress(), addressDto.getDetailAddress(), addressDto.getPostalCode());
    }
}
