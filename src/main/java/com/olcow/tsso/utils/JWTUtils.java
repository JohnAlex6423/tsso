package com.olcow.tsso.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.olcow.tsso.dto.KeyValueDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.olcow.tsso.constant.Constant.*;

/**
 *JWT工具类
 */
@Component
public class JWTUtils {

    @Value("${PublicKey}")
    private String publicKey;

    /**
     * 验证token
     * @param token token
     * @return 1-有效 2-无效 3-过期
     */
    public KeyValueDTO<Integer,Integer> verifier(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(publicKey)).build();
        try {
            verifier.verify(token);
        } catch (TokenExpiredException e){
            return new KeyValueDTO<>(VERIFIER_EXPIRED,null);
        } catch (Exception e) {
            return new KeyValueDTO<>(VERIFIER_ERROR,null);
        }
        Integer userId;
        try {
            userId = JWT.decode(token).getClaim("userId").asInt();
        } catch (Exception e) {
            return new KeyValueDTO<>(VERIFIER_ERROR,null);
        }
        return new KeyValueDTO<>(VERIFIER_SUCCESS,userId);
    }
}
