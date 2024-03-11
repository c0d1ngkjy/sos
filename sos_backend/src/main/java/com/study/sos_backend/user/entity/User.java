package com.study.sos_backend.user.entity;

import com.study.sos_backend.auth.utils.PasswordUtil;
import com.study.sos_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    // BaseTimeEntity 는 객체가 생성되거나 수정될 때 객체 생성 시간 수정 시간을 컬럼에 추가시키고 알아서 자동 생성 및 수정함.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    // ID 값은 그냥 의미없고 email 로 유저 식별.
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    private String password;

    @Column(name = "SOCIAL_ID")
    private String socialId;

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private String refreshToken;


    @Builder
    public User(String email, String password, String socialId, ProviderType providerType, RoleType roleType) {
        this.email = email;

        this.password = passwordEncode(password);
        this.socialId = socialId;
        this.providerType = providerType;
        this.roleType = roleType;
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    private String passwordEncode(String rawPassword) {
        if (rawPassword == null){
            return PasswordUtil.generateRandomPassword();
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }
}
