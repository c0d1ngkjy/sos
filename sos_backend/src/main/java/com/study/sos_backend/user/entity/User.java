package com.study.sos_backend.user.entity;

import com.study.sos_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    // BaseTimeEntity 는 객체가 생성되거나 수정될 때 객체 생성 시간 수정 시간을 컬럼에 추가시키고 알아서 자동 생성 및 수정함.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    // ID 값은 그냥 의미없고 userId가 주로 사용해서 유저 분리할거임.
    @Column(name ="USER_ID", nullable = false, unique = true)
    private String userId;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleType;


}
