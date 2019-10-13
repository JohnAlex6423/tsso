package com.olcow.tsso.until;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.stereotype.Component;

/**
 *JWT工具类
 */
@Component
public class JWTUntil {

    /**
     * 验证token
     * @param token token
     * @return 1-有效 2-无效 3-过期
     */
    public int verifier(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("keyl")).build();
        try {
            verifier.verify(token);
        } catch (SignatureVerificationException e){
            return 2;
        } catch (TokenExpiredException e){
            return 3;
        }
        return 1;
    }
}
