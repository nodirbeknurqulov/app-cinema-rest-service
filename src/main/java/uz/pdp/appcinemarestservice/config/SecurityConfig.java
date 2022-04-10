package uz.pdp.appcinemarestservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.appcinemarestservice.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

// Nurkulov Nodirbek 4/6/2022  8:41 AM
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthService authService;

//    HttpServletRequest httpServletRequest;

//    Principal re

    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/api/movie")
//                    .hasRole("ADMIN")
                .antMatchers("/api/movie", "/")
                .permitAll()
//                    .hasRole("ADMIN")
//                    .authenticated()
//                .antMatchers("/api/**")
//                    .permitAll()
                .anyRequest()
                .authenticated()
                .and()

                .oauth2Login()
                  .defaultSuccessUrl("/success-url-oauth", true)
                  .failureUrl("/error")
                  .loginPage("/login").permitAll()
                .and()
                .formLogin()
                   .defaultSuccessUrl("/success-url", true)
                   .failureUrl("/error")
                   .loginPage("/login").permitAll()
                .and()
                .httpBasic();
    }

    @Bean
    public AuthenticationProvider getAuthProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authService);
        provider.setPasswordEncoder(getEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthProvider());
    }
}
