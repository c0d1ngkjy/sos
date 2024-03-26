package com.study.sos_backend.common.dto;

import com.study.sos_backend.common.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {

    private String roadAddress;

    private String detailAddress;

    private String postalCode;


    public AddressDto(Address address) {
        this.roadAddress = address.getRoadAddress();
        this.detailAddress = address.getDetailAddress();
        this.postalCode = address.getPostalCode();
    }
}
