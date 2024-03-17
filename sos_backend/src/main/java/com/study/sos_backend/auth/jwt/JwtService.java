package com.study.sos_backend.auth.jwt;

import com.study.sos_backend.user.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
<<<<<<< HEAD
=======
import jakarta.servlet.http.Cookie;
>>>>>>> main
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
@Getter
@Slf4j
public class JwtService {

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String EMAIL_CLAIM = "email";
    private static final String BEARER = "Bearer ";
    private final Key key;
    private final UserRepository userRepository;
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.token.expiry}")
    private Long accessTokenExpirationPeriod;
    @Value("${jwt.refresh-token.expiry}")
    private Long refreshTokenExpirationPeriod;
    @Value("${jwt.token.header}")
    private String accessHeader;
    @Value("${jwt.refresh-token.header}")
    private String refreshHeader;

    public JwtService(UserRepository userRepository, String secretKey) {
        this.userRepository = userRepository;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createAccessToken(String email) {
        Date now = new Date();
        return Jwts.builder().setSubject(ACCESS_TOKEN_SUBJECT).setExpiration(new Date(now.getTime() + accessTokenExpirationPeriod)).claim(EMAIL_CLAIM, email)
                .signWith(key, SignatureAlgorithm.HS256).compact();

    }

    public String createRefreshToken() {
        Date now = new Date();
        return Jwts.builder().setSubject(REFRESH_TOKEN_SUBJECT).setExpiration(new Date(now.getTime() + refreshTokenExpirationPeriod)).signWith(key, SignatureAlgorithm.HS256).compact();
    }


    public void sendAccessToken(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(accessHeader, accessToken);
        log.info("재발급된 Access Token : {}", accessToken);
    }


    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken);
        setRefreshTokenHeader(response, refreshToken);
<<<<<<< HEAD
=======

        // 엑세스 토큰 쿠키 생성
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setMaxAge(accessTokenExpirationPeriod.intValue());
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        // 리프레시 토큰 쿠키 생성
        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
        refreshTokenCookie.setMaxAge(refreshTokenExpirationPeriod.intValue());
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setHttpOnly(true); // JavaScript 에서 접근 불가능하도록 설정
        response.addCookie(refreshTokenCookie);

>>>>>>> main
        log.info("Access Token, Refresh Token 헤더 설정 완료");
    }


    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }


    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(refreshToken -> refreshToken.startsWith(BEARER))
                .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }


    public Optional<String> extractEmail(String accessToken) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken)
                    .getBody();
            log.info(claims.get(EMAIL_CLAIM, String.class));

            return Optional.ofNullable(claims.get(EMAIL_CLAIM, String.class));
        } catch (Exception e) {
            log.error("액세스 토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }

    /**
     * AccessToken 헤더 설정
     */
    public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(accessHeader, accessToken);
    }

    /**
     * RefreshToken 헤더 설정
     */
    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshHeader, refreshToken);
    }

    /**
     * RefreshToken DB 저장(업데이트)
     */
    public void updateRefreshToken(String email, String refreshToken) {
        userRepository.findByEmail(email)
                .ifPresentOrElse(
                        user -> user.updateRefreshToken(refreshToken),
                        IllegalStateException::new
                );
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return true;
        } catch (SecurityException e) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        } catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
        }
        return false;
    }

}
