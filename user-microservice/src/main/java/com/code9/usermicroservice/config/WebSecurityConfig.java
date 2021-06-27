package com.code9.usermicroservice.config;

import com.code9.usermicroservice.security.RequestFilter;
import com.code9.usermicroservice.security.RestAuthenticationEntryPoint;
import com.code9.usermicroservice.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
@Order(1000)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private RequestFilter requestFilter;
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, RequestFilter requestFilter, RestAuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.requestFilter = requestFilter;
        this.restAuthenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    // Manager that does authentication for us
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Disable Cross Site Request Forgery security
                .csrf().disable()
                // Entry point for Unauthorized requests
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                // Enable anyone to access methods of Quoted mapping (without Authorisation)
                .and().authorizeRequests()
                .antMatchers("/login").permitAll()
                // Every other request needs Authorisation
                .anyRequest().authenticated()
                // Enable CORS layer (WebMvcConfig class)
                .and().cors()
                // Disables sessions for spring security (We use jwt to manage session)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Makes RequestFilter class to execute before each controller method
                .and().addFilterBefore(requestFilter, BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        // Ignores security configurations for Quoted mappings
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js", "/");
        web.ignoring().antMatchers(HttpMethod.POST,
                "/login", "/");
    }
}
