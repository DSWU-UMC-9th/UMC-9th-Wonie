package com.example.umc9th.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // 인증 없이 열어둘 URI들
    private static final String[] PUBLIC_URIS = {
            "/sign-up",                 // 회원가입
            "/swagger-ui/**",           // Swagger
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/h2-console/**",
            "/error"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // REST API 실습 편하게 하려고 CSRF 일단 비활성화
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_URIS).permitAll()      // 위에서 정의한 공개 URI
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN만 접근
                        .anyRequest().authenticated()                  // 나머지는 로그인 필요
                )

                // 세션 기반 폼 로그인
                .formLogin(form -> form
                        // 별도 커스텀 로그인 페이지 없으면 생략 가능 (기본 /login 사용)
                        // .loginPage("/login")
                        .defaultSuccessUrl("/swagger-ui/index.html", true) // 로그인 성공 시 Swagger로 이동
                        .permitAll()
                )

                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        // 세션 방식은 기본이기 때문에 따로 sessionManagement 안 건드려도 됨
        return http.build();
    }

    // 비밀번호 암호화를 위한 PasswordEncoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
