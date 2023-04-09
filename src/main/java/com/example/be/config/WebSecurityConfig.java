package com.example.be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
//    @Autowired
//    private UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @Autowired
//    private JwtFilter jwtFilter;
//
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsServiceImpl userDetailsService() {
//        return userDetailsServiceImpl;
//    }
//
//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//
//                .requestMatchers("/login/", "/", "/register").permitAll().and()
//                .authorizeRequests().requestMatchers("/admin", "/admin/*", "/admin/**")
//                .access("hasRole('ADMIN')").and().
//                authorizeRequests().requestMatchers("/user", "/user/*", "/user/**")
//                .access("hasAnyRole('CUSTOMER', 'ADMIN')").and()
//                .exceptionHandling().accessDeniedPage("/403")
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and().cors();
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
//    }

}
