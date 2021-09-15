package com.plaidcamp.mealogram.common.provider;

import com.plaidcamp.mealogram.common.constants.AuthConstant;
import com.plaidcamp.mealogram.common.constants.Token;
import com.plaidcamp.mealogram.domain.user.UserMaster;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.plaidcamp.mealogram.user.service.CustomUserDetailService;

/**
 * Token
 *  - Access Token : 사용자 정보를
 *  - Refresh Token : 사용자 로그인 여부확인
 */

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${profile}")
    private String profile;

    private String AccessKey = "webfirewood";
    private String RefreshKey = "WhoCanKnowMe";

    private final SignatureAlgorithm sa = SignatureAlgorithm.RS512;
    private final CustomUserDetailService customUserDetailService;
    private final FuncUtilProvider funcUtil;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() throws IOException {
        // Create Token Private Key
        AccessKey = Base64.getEncoder().encodeToString(AccessKey.getBytes());
        RefreshKey = Base64.getEncoder().encodeToString(RefreshKey.getBytes());
        if(!"PROD".equals(profile)) {
            System.out.println("Secret key" + AccessKey);
            System.out.println("Secret key" + RefreshKey);
        } else {
            Path p = Paths.get("src/main/resources/secret/tokenKey.pem");
            List<String> reads = Files.readAllLines(p);
            String read = "";
            for (String str : reads) {
                read += str + "\n";
            }
        }

    }

    public String decodeToken(String token) {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        SecretKeySpec sign = new SecretKeySpec();
        return null;
    }

    public String createRefreshToken(UserMaster user) {
        return createToken(user, AuthConstant.REFRESH_TOKEN);
    }

    public String createAccessToken(UserMaster user) {
        return createToken(user, AuthConstant.ACCESS_TOKEN);
    }

    // JWT 토큰 생성
    private String createToken(UserMaster user, String token) {
        boolean t = AuthConstant.ACCESS_TOKEN.equals(token);
        String key = (t)? this.AccessKey : this.RefreshKey;
        Date expireDate = new Date();
        if(t) { expireDate = funcUtil.AddHour(expireDate, AuthConstant.ACCESS_TOKEN_DURATION); }
        else  { expireDate = funcUtil.AddDays(expireDate, AuthConstant.REFRESH_TOKEN_DURATION); }

        // jwt header setting
        Map<String, Object> header = new HashMap<String, Object>();
        header.put("typ", "JWT");
        header.put("alg", sa.getValue());

        // claims 생성
        Claims claims = Jwts.claims().setSubject(user.getId().toString()); // JWT payload Subject
        // TODO : payload에 들어가야 할 데이터들 추가하면 됨
        claims.put("email", user.getEmail());
        claims.put("roles", user.getRoles()); // 정보는 key / value 쌍으로 저장된다.

        // jwt token build
        return Jwts.builder()
                .setHeader(header) // jwt header 넣어줌
                .setClaims(claims) // 정보(payload) 저장
                .setIssuedAt(new Date())  // 토큰 발행 시간 정보(iat)
                .setExpiration(expireDate) // 토큰 만료시간 정보(exp)
                .signWith(sa, key) // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();
    }
//
//    // JWT 토큰에서 인증 정보 조회
//    public Authentication getAuthentication(Token token, String jwt) {
//        UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserPk(jwt));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
//
//    // 토큰에서 회원 정보 추출
//    public String getUserPk(String token) {
//        return Jwts.parser().setSigningKey(AccessKey).parseClaimsJws(token).getBody().getSubject();
//    }
//
    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    // ACCESS TOKEN만 해당, Refresh는 header에 없으니
    public String[] resolveToken(HttpServletRequest request) {
        String[] tokens = new String[2];
        tokens[0] = request.getHeader(AuthConstant.AUTH_HEADER_A);
        tokens[1] = request.getHeader(AuthConstant.AUTH_HEADER_R);

        return tokens;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(Token token, String jwtToken) {
        try {
            Jws<Claims> claims;
            if(token == Token.ACCESS) {
                claims = Jwts.parser().setSigningKey(AccessKey).parseClaimsJws(jwtToken);
            } else {
                claims = Jwts.parser().setSigningKey(RefreshKey).parseClaimsJws(jwtToken);
            }

            return !claims.getBody().getExpiration().before(new Date());

        } catch (Exception e) {
            return false;
        }
    }


}