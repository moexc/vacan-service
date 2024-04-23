package cn.moexc.vcs.web.config;

import cn.moexc.vcs.web.config.auth.AuthResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    private final AuthResolver authResolver;

    @Autowired
    public MvcConfig(AuthResolver authResolver) {
        this.authResolver = authResolver;
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authResolver);
    }
}
