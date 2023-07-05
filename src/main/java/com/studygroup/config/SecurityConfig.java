package com.studygroup.config;

<<<<<<< HEAD
import com.studygroup.auth.token.JwtToken;
import com.studygroup.auth.token.JwtTokenValidationStrategy;
import com.studygroup.auth.token.NormalLoginJwtTokenHandler;
import com.studygroup.auth.token.TokenProcessor;
import com.studygroup.auth.token.TokenValidator;
import com.studygroup.securirty.filter.ChatRoomMembershipFilter;
import com.studygroup.securirty.filter.GroupAuthorizationFilter;
import com.studygroup.securirty.filter.JwtAuthenticationFilter;
import com.studygroup.securirty.filter.JwtRequestFilter;
import com.studygroup.securirty.handler.GroupAccessDeniedHandler;
import com.studygroup.securirty.handler.GroupAuthenticationFailureHandler;
import com.studygroup.securirty.handler.LoginAccessDeniedHandler;
import com.studygroup.service.chatroom.FindMemberIsInChatRoom;
import com.studygroup.service.group.FindGroupService;
import com.studygroup.service.group.member.FindGroupMemberService;
import com.studygroup.service.user.RetrieveMemberByAuthPrinciple;
=======
import com.studygroup.securirty.filter.ChatRoomMembershipFilter;
import com.studygroup.securirty.handler.GroupAccessDeniedHandler;
import com.studygroup.securirty.handler.GroupAuthenticationFailureHandler;
import com.studygroup.securirty.handler.LoginAccessDeniedHandler;
import com.studygroup.securirty.filter.GroupAuthorizationFilter;
import com.studygroup.securirty.filter.JwtAuthenticationFilter;
import com.studygroup.securirty.filter.JwtRequestFilter;
import com.studygroup.service.chatroom.FindMemberIsInChatRoom;
import com.studygroup.service.group.FindGroupService;
import com.studygroup.service.groupmember.FindGroupMemberService;
import com.studygroup.service.user.RetrieveMemberByAuthPrinciple;
import com.studygroup.service.user.UserAuthenticationService;
import com.studygroup.util.JwtUtil;
import lombok.RequiredArgsConstructor;
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

<<<<<<< HEAD
  private final FindMemberIsInChatRoom findMemberIsInChatRoom;
  private final RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple;
  private final FindGroupMemberService findGroupMemberService;
  private final FindGroupService findGroupService;


  public SecurityConfig(
      @Qualifier("FindGroupMemberByIdService") FindGroupMemberService findGroupMemberService,
      FindGroupService findGroupService,
      FindMemberIsInChatRoom findMemberIsInChatRoom,
      RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple) {
    this.findMemberIsInChatRoom = findMemberIsInChatRoom;
    this.findGroupService = findGroupService;
    this.findGroupMemberService = findGroupMemberService;
    this.retrieveMemberByAuthPrinciple = retrieveMemberByAuthPrinciple;
  }

  @Bean
  TokenProcessor<JwtToken<SimpleGrantedAuthority>> getUserNormalLoginJwtTokenProcessor() {
    return new TokenProcessor<>(
        new TokenValidator<JwtToken<SimpleGrantedAuthority>>(new JwtTokenValidationStrategy()),
        new NormalLoginJwtTokenHandler());
  }


  @Bean
  GroupAuthenticationFailureHandler groupAuthenticationFailureHandler() {
    return new GroupAuthenticationFailureHandler();
  }

  @Bean
  BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  LoginAccessDeniedHandler loginAccessDeniedHandler() {
    return new LoginAccessDeniedHandler();
  }

  @Bean
  GroupAccessDeniedHandler groupAccessDeniedHandler() {
    return new GroupAccessDeniedHandler();
  }

  @Bean
  AuthenticationManager defaultAuthenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
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

    http.addFilterAt(new JwtAuthenticationFilter(defaultAuthenticationManager(),
            getUserNormalLoginJwtTokenProcessor()),
        UsernamePasswordAuthenticationFilter.class);
    http.addFilterAfter(new JwtRequestFilter(), JwtAuthenticationFilter.class);
    http.addFilterAfter(
        new GroupAuthorizationFilter(groupAuthenticationFailureHandler(), findGroupMemberService,
            findGroupService), JwtRequestFilter.class);
    http.addFilterAfter(
        new ChatRoomMembershipFilter(retrieveMemberByAuthPrinciple, findMemberIsInChatRoom),
        GroupAuthorizationFilter.class);

    return http.build();
  }


  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.debug(true)
        .ignoring()
        .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
  }
=======
    private FindMemberIsInChatRoom findMemberIsInChatRoom;
    private RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple;
    private  UserAuthenticationService userService;
    private  FindGroupMemberService findGroupMemberService;
    private  FindGroupService findGroupService;

    public SecurityConfig(UserAuthenticationService userService,
                          @Qualifier("FindGroupMemberByIdService") FindGroupMemberService findGroupMemberService,
                          FindGroupService findGroupService,
                          FindMemberIsInChatRoom findMemberIsInChatRoom,
                          RetrieveMemberByAuthPrinciple retrieveMemberByAuthPrinciple) {
        this.userService = userService;
        this.findMemberIsInChatRoom = findMemberIsInChatRoom;
        this.findGroupService = findGroupService;
        this.findGroupMemberService = findGroupMemberService;
        this.retrieveMemberByAuthPrinciple = retrieveMemberByAuthPrinciple;
    }

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
        http.addFilterAfter(new GroupAuthorizationFilter(groupAuthenticationFailureHandler(), findGroupMemberService, findGroupService),JwtRequestFilter.class);
        http.addFilterAfter(new ChatRoomMembershipFilter(retrieveMemberByAuthPrinciple,findMemberIsInChatRoom), GroupAuthorizationFilter.class);

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(true)
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
>>>>>>> 7c3dbf05b4ae7da5f5b0cb57a6434bd46693aa91
}
