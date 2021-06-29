package com.code9.usermicroservice.config;

import com.code9.usermicroservice.jwt.RequestFilter;
import com.code9.usermicroservice.jwt.RestAuthEntryPoint;
import com.code9.usermicroservice.jwt.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final RequestFilter requestFilter;
    private final RestAuthEntryPoint authEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers("/configuration/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(requestFilter, BasicAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
                        "/**/*.css", "/**/*.js", "/api/**");
        web
                .ignoring()
                .antMatchers(HttpMethod.POST,
                        "/api/login", "/api");

        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
