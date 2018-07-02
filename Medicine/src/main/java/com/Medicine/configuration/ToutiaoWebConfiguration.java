package com.Medicine.configuration;

import com.Medicine.interceptor.LoginRequiredInterceptor;
import com.Medicine.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created by licker.
 */
@Configuration
public class ToutiaoWebConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequiredInterceptor).
                addPathPatterns("/User*").
                addPathPatterns("/Drug*").
                addPathPatterns("/Category*").
                addPathPatterns("/Buy*").
                addPathPatterns("/user/info/*");
        super.addInterceptors(registry);
    }
}
