package com.studygroup.config;

import com.studygroup.securirty.handler.GroupAccessDeniedHandler;
import com.studygroup.securirty.handler.GroupAuthenticationFailureHandler;
import com.studygroup.securirty.handler.LoginAccessDeniedHandler;
import com.studygroup.securirty.filter.GroupAuthorizationFilter;
import com.studygroup.securirty.filter.JwtAuthenticationFilter;
import com.studygroup.securirty.filter.JwtRequestFilter;
import com.studygroup.service.group.RetrieveGroupByNameService;
import com.studygroup.service.groupmember.RetrieveGroupMemberByMemberIdAndGroup;
import com.studygroup.service.user.CustomAuthenticationService;
import com.studygroup.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Lazy
    private final CustomAuthenticationService userService;
    private final RetrieveGroupMemberByMemberIdAndGroup retrieveGroupMemberByMemberIdAndGroupName;
    private final RetrieveGroupByNameService retrieveGroupByNameService;

    @Bean
    GroupAuthenticationFailureHandler groupAuthenticationFailureHandler(){
        return new GroupAuthenticationFailureHandler();
    }
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    LoginAccessDeniedHandler loginAccessDeniedHandler(){
        return new LoginAccessDeniedHandler();
    }

    @Bean
    GroupAccessDeniedHandler groupAccessDeniedHandler() {
        return new GroupAccessDeniedHandler();
    }

    @Bean
    JwtUtil jwtUtil() {
        return new JwtUtil();
    }


    @Bean
    AuthenticationManager defaultAuthenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider::authenticate;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
                csrf().
                disable().
                httpBasic().
                and().
                logout().
                disable().
                formLogin().
                disable().authorizeHttpRequests().
                antMatchers("/api/sign-up").permitAll().
                antMatchers("/api/logout").permitAll().
                antMatchers("/api/login").hasAnyRole("ADMIN", "USER").
                antMatchers("/api/user/**/*").hasAnyRole("USER", "ADMIN").
                antMatchers("/api/admin/**/*").hasRole("ADMIN").
                anyRequest().permitAll();

        http.exceptionHandling().accessDeniedHandler(loginAccessDeniedHandler());

        http.
                sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authenticationManager(defaultAuthenticationManager());

        http.addFilterAt(new JwtAuthenticationFilter(defaultAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new JwtRequestFilter(jwtUtil()), JwtAuthenticationFilter.class);
        http.addFilterAfter(new GroupAuthorizationFilter(groupAuthenticationFailureHandler(), retrieveGroupMemberByMemberIdAndGroupName, retrieveGroupByNameService),JwtRequestFilter.class);
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(true)
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
}
