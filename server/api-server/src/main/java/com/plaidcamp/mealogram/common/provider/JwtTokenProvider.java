package com.plaidcamp.mealogram.common.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

import com.plaidcamp.mealogram.user.service.CustomUserDetailService;

/**
 * Token
 *  - Access Token : 사용자 정보를
 *  - Refresh Token : 사용자 로그인 여부확인
 */

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String secretKey = "webfirewood";
    private PrivateKey privateKey;

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;

    private final CustomUserDetailService customUserDetailService;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() throws IOException {
        // Create Token Private Key
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());

//        Path p = Paths.get("src/main/resources/secret/tokenKey.pem");
//        List<String> reads = Files.readAllLines(p);
//        String read = "";
//        for (String str : reads){
//            read += str+"\n";
//        }

    }

    // JWT 토큰 생성
    public String createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload Subject (ID를 넣을건가?)
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        // TODO : payload에 들어가야 할 데이터들 추가하면 됨

        // jwt header setting
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("typ", "JWT");

        Date now = new Date();

        // jwt token build
        return Jwts.builder()
                .setHeader(header) // jwt header 넣어줌
                .setClaims(claims) // 정보(payload) 저장
                .setIssuedAt(now)  // 토큰 발행 시간 정보(iat)
                // TODO : access token과 refresh token 만료시간 분기 필요
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 토큰 만료시간 정보(exp)
                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과
                                                               // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}