package cn.moexc.vcs.infrasture.auth;

import cn.moexc.vcs.domain.auth.AuthDomain;
import cn.moexc.vcs.domain.auth.AuthDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class AuthDomainRepositoryImpl implements AuthDomainRepository {

    private final RedisTemplate<String, Long> redisTemplate;
    private final Long authTimeout;
    private static final Long REMEMBER_ME = 7 * 24 * 60 * 60L;

    @Autowired
    public AuthDomainRepositoryImpl(RedisTemplate redisTemplate,
                                    @Value("${auth.timeout}") Long authTimeout) {
        this.redisTemplate = redisTemplate;
        this.authTimeout = authTimeout;
    }

    @Override
    public AuthDomain byId(String token) {
        if (Boolean.FALSE.equals(redisTemplate.hasKey(token))){
            return null;
        }
        return JwtUtil.verify(token);
    }

    @Override
    public void save(AuthDomain domain) {
        redisTemplate.opsForValue().set(JwtUtil.create(domain), System.currentTimeMillis(), domain.getRememberMe() ? REMEMBER_ME : authTimeout, TimeUnit.SECONDS);
    }

    @Override
    public void expire(String token) {
        redisTemplate.expire(token, authTimeout, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String token) {
        redisTemplate.delete(token);
    }
}
