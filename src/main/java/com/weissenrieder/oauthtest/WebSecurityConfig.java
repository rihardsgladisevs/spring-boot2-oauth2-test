package com.weissenrieder.oauthtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Rihards Gladisevs on 09.08.2018.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		http
                .authorizeRequests()
                    .anyRequest().fullyAuthenticated()
                    .and()
                .csrf()
                    .disable()
                .logout()
                    .logoutSuccessUrl("/login").permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                .httpBasic()
                    .and()
                .oauth2Login()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/")
                    .failureUrl("/loginFailure");
		// @formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
}
