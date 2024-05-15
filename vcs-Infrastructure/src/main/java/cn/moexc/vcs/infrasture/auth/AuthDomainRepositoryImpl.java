package cn.moexc.vcs.infrasture.auth;

import cn.moexc.vcs.domain.auth.AuthDomain;
import cn.moexc.vcs.domain.auth.AuthDomainRepository;
import cn.moexc.vcs.domain.auth.CreateTokenCommand;
import cn.moexc.vcs.domain.auth.TokenDTO;
import cn.moexc.vcs.infrasture.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDomainRepositoryImpl implements AuthDomainRepository {

    private final JwtConfig jwtConfig;

    @Autowired
    public AuthDomainRepositoryImpl(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public AuthDomain byId(String token) {
        return jwtConfig.verify(token);
    }

    @Override
    public void save(AuthDomain domain) {}

    @Override
    public TokenDTO genToken(CreateTokenCommand cmd) {
        return new TokenDTO(jwtConfig.create(cmd), jwtConfig.createRefToken(cmd));
    }
}
