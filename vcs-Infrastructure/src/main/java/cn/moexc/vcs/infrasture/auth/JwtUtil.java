package cn.moexc.vcs.infrasture.auth;

import cn.moexc.vcs.domain.auth.AuthDomain;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String PRIVATE_KEY = "moexc.vcan#.1521";
    private static final Map<String, Object> JWT_HEADER = new HashMap<>(2);
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(PRIVATE_KEY);
    private static final JWTVerifier VERIFIER = JWT.require(ALGORITHM).build();
    static {
        JWT_HEADER.put("Type", "Jwt");
        JWT_HEADER.put("alg", "HS256");
    }

    public static String create(AuthDomain authDomain){
        String token = JWT.create()
                .withHeader(JWT_HEADER)
                .withClaim("uid", authDomain.getUserId())
                .withClaim("name", authDomain.getNickName())
                .sign(ALGORITHM);
        authDomain.setToken(token);
        return token;
    }

    public static AuthDomain verify(String token){
        try{
            DecodedJWT jwt = VERIFIER.verify(token);
            return new AuthDomain(
                    token,
                    jwt.getClaim("uid").asString(),
                    jwt.getClaim("name").asString()
            );
        }catch (Exception e){
            return null;
        }
    }

}
