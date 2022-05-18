package com.happy.delivery.infra.config.common;

import com.happy.delivery.application.common.AuthorizationService;
import com.happy.delivery.infra.interceptor.LonginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfig.
 * Interceptor 를 Bean 으로 등록할 때 사용함.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Autowired
  private AuthorizationService authorizationService;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    /*
          <설정>

          * InterceptorRegistry 의 addInterceptor 를 사용하며 Interceptor 를 등록한 후,
          * chaining 방식을 통해 세부적으로 가로챌 경로와 가로채지 않을 경로를 설정할 수 있다.

          registry.addInterceptor(new LonginCheckInterceptor())  : interceptor 추가.
                  .addPathPatterns("/*")                         : 모든 Path 에 interceptor 적용.
                  .addPathPatterns("/sample")                    : /sample 만 interceptor 적용.
                  .excludePathPatterns("/sample");                : /sample 은 제외.
    */
    registry
        .addInterceptor(new LonginCheckInterceptor(authorizationService))
        .addPathPatterns("/user/*", "/user/*/*", "/user/*/*/*")
        .excludePathPatterns("/user/signup", "/user/signin");
  }
}
