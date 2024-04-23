package cn.moexc.vcs.web.config.auth;

import cn.moexc.vcs.domain.auth.AuthDomain;
import cn.moexc.vcs.domain.auth.AuthDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthResolver implements HandlerMethodArgumentResolver {
    private static final String HEADER_NAME_TOKEN = "Token";
    private static final AuthException NO_LOGIN_EXCEPTION = new AuthException(401, "未登录");
    private static final AuthException LOGIN_TIMEOUT_EXCEPTION = new AuthException(402, "登录有效期已过");

    private final AuthDomainRepository authDomainRepository;

    @Autowired
    public AuthResolver(AuthDomainRepository authDomainRepository) {
        this.authDomainRepository = authDomainRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class) && parameter.getParameterType().isAssignableFrom(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String token = webRequest.getHeader(HEADER_NAME_TOKEN);
        if (token == null || token.length() == 0){
            throw NO_LOGIN_EXCEPTION;
        }
        AuthDomain authDomain = authDomainRepository.byId(token);
        if (authDomain == null){
            throw LOGIN_TIMEOUT_EXCEPTION;
        }

        authDomainRepository.expire(token);

        return new User().initVal(authDomain);
    }

}
