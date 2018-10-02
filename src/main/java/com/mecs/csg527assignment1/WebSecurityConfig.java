package com.mecs.csg527assignment1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
	
    @SuppressWarnings("deprecation")
	@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder().username("h20181030100@hyderabad.bits-pilani.ac.in").password("password").roles("USER").build();
        UserDetails user2 = User.withDefaultPasswordEncoder().username("h20181030090@hyderabad.bits-pilani.ac.in").password("password").roles("USER").build();
        UserDetails user3 = User.withDefaultPasswordEncoder().username("h20181030107@hyderabad.bits-pilani.ac.in").password("password").roles("USER").build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}