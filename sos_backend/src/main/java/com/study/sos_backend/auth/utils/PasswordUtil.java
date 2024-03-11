package com.study.sos_backend.auth.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordUtil {

    private static final int PASSWORD_LENGTH = 15;

    public static String generateRandomPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 랜덤 비밀번호를 생성하는 로직 추가
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return passwordEncoder.encode(password);
    }
}
