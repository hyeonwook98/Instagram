package com.cos.instagram.config;

import com.cos.instagram.config.oauth.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final OAuth2DetailsService oAuth2DetailsService;
    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin") //GET
                .loginProcessingUrl("/auth/signin") //POST -> 스프링 시큐리티가 로그인 프로세스 진행
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login() //oauth2 로그인도 할거다.
                .userInfoEndpoint() //인증코드나 token 권한 대행은 알아서 해주고 최종응답을 회원정보만 달라.
                .userService(oAuth2DetailsService);
        return http.build();
    }
}
