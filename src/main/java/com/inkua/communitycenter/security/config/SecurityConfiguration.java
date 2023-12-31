package com.inkua.communitycenter.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors().and().csrf().disable()

            .authorizeHttpRequests()
            .requestMatchers("/api/v1/auth/authenticate").permitAll()
            .requestMatchers("/api/v1/auth/register").authenticated()
            
            .requestMatchers(HttpMethod.GET,"/api/v1/posts/**").permitAll()
            .requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll()
            .requestMatchers(HttpMethod.GET,"/api/v1/information/type/**").permitAll()

            // No funcionan, no sé por qué
            .requestMatchers(HttpMethod.POST,"/api/v1/volunteers").permitAll()
            .requestMatchers(HttpMethod.GET,"/api/v1/volunteers").authenticated()
            .requestMatchers(HttpMethod.DELETE,"/api/v1/volunteers/**").authenticated()


            .requestMatchers("api/v1/categories/**").denyAll()
            .requestMatchers("api/v1/posts/**").denyAll()

            .anyRequest().authenticated()

        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
    ;

    return http.build();
  }
}
