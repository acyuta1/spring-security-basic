package com.acyuta.springsecurity2.config;

import com.acyuta.springsecurity2.customUserDetails.CustomAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/contact", "/notices").permitAll()
                .antMatchers("/customers/**").permitAll()
//                .antMatchers("/customers/**").hasRole("ADMIN")
//                .anyRequest().hasAuthority("admin")
//                .anyRequest().hasAnyAuthority("admin","user")
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new CustomAuthenticationFilter(), BasicAuthenticationFilter.class)
                .formLogin()
                .and()
                .httpBasic();

        // or
//        http
//                .authorizeRequests()
//                .antMatchers("/myAccount").authenticated()
//                .antMatchers("/myBalance").authenticated()
//                .antMatchers("/myCards").authenticated()
//                .antMatchers("/myLoans").authenticated()
//                .antMatchers("/contact").permitAll()
//                .antMatchers("/notices").permitAll()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("1234").authorities("admin")
//                .and()
//                .withUser("user").password("1234").authorities("user")
//                .and()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    /*
    What DaoAuthenticationProvider looks for is UserDetailsService's abstract method implementation (loabByUserName).
    So, either provide it as an argument in auth (with the implementation of UserDetailsManager, a concrete class)
    or as a bean.
    If you have your own UserDetailsService implementation, just annotating it with @Service will work.
     */

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        var userDetailsService = new InMemoryUserDetailsManager();
//        UserDetails user1 = User.builder().username("admin").password("1234").authorities("admin").build();
//        var user2 = User.builder().username("user").password("1234").authorities("user").build();
//        userDetailsService.createUser(user1);
//        userDetailsService.createUser(user2);
//        auth.userDetailsService(userDetailsService);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
