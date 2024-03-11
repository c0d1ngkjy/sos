package com.study.sos_backend.business.entity;

import com.study.sos_backend.common.entity.Locate;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessInfo {

    /**
     * 미용실 정보에 대한 엔티티
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     *  이 사이에 미용실 정보 작성해두셍
     */


    // 위치 정보에 관한 엔티티
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "COORDINATE_ID")
    private Locate locate;

}
