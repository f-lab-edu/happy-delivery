package com.happy.delivery.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * UserLoginCheck annotation.
 * 로그인 체크 AOP 위해 만든 어노테이션.
 * userController 메서드에 사용.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserLoginCheck {

}
