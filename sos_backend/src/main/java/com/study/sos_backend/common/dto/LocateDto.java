package com.study.sos_backend.common.dto;

import com.study.sos_backend.common.entity.Locate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.study.sos_backend.common.entity.Locate}
 */
@Data
@AllArgsConstructor
public class LocateDto {
    Double latitude;
    Double longitude;

    public LocateDto(Locate locate) {
        this.latitude = locate.getLatitude();
        this.longitude = locate.getLongitude();
    }
}