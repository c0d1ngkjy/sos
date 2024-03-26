package com.study.sos_backend.business.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessSortType {
    PRICE("price"),
    DISTANCE("distance");

    private final String type;
}
