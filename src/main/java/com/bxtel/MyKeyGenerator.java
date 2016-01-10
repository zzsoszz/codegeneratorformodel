package com.bxtel;
import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
public class MyKeyGenerator implements KeyGenerator {
    public Object generate(Object target, Method method, Object... params) {
    	return null;
    }
}