package com.cafe24.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.web.TestFileuploadConfig;
import com.cafe24.config.web.TestMVCConfig;
import com.cafe24.config.web.TestMessageConfig;
import com.cafe24.config.web.TestSecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.mysite.controller", "com.cafe24.mysite.exception"})
@Import({TestMVCConfig.class, TestSecurityConfig.class, TestFileuploadConfig.class, TestMessageConfig.class})
public class TestWebConfig {
}
