package cn.moexc.vcs.infrasture.config;

import cn.moexc.vcs.domain.auth.AuthDomain;
import cn.moexc.vcs.domain.auth.CreateTokenCommand;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtConfig {
    private static final String PRIVATE_KEY = "moexc.vcan#.1521";
    private static final Map<String, Object> JWT_HEADER = new HashMap<>(2);
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(PRIVATE_KEY);
    private static final JWTVerifier VERIFIER = JWT.require(ALGORITHM).build();
    static {
        JWT_HEADER.put("Type", "Jwt");
        JWT_HEADER.put("alg", "HS256");
    }

    private final Long tokenTimeout;
    private final Long reftokenTimeout;
    private final Long remtokenTimeout;

    public JwtConfig(@Value("${auth.token-timeout}") Long tokenTimeout,
                     @Value("${auth.reftoken-timeout}") Long reftokenTimeout,
                     @Value("${auth.remtoken-timeout}") Long remtokenTimeout) {
        this.tokenTimeout = tokenTimeout;
        this.reftokenTimeout = reftokenTimeout;
        this.remtokenTimeout = remtokenTimeout;
    }

    public String create(CreateTokenCommand cmd){
        return JWT.create()
                .withHeader(JWT_HEADER)
                .withClaim("uid", cmd.getUserId())
                .withClaim("name", cmd.getNickName())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenTimeout))
                .sign(ALGORITHM);
    }

    public String createRefToken(CreateTokenCommand cmd){
        return JWT.create()
                .withHeader(JWT_HEADER)
                .withClaim("uid", cmd.getUserId())
                .withClaim("name", cmd.getNickName())
                .withClaim("reme", cmd.getReme())
                .withExpiresAt(new Date(System.currentTimeMillis() + (cmd.getReme() ? remtokenTimeout : reftokenTimeout)))
                .sign(ALGORITHM);
    }

    public AuthDomain verify(String token){
        try{
            DecodedJWT jwt = VERIFIER.verify(token);
            return new AuthDomain(
                    jwt.getClaim("uid").asString(),
                    jwt.getClaim("name").asString(),
                    jwt.getClaim("reme").asBoolean()
            );
        }catch (Exception e){
            return null;
        }
    }
}
