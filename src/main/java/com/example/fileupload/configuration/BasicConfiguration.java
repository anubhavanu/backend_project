//package com.example.fileupload.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class BasicConfiguration {
//
////    @Bean
////    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
////        UserDetails user = User.withUsername("user")
////                .password(passwordEncoder.encode("password"))
////                .roles("USER")
////                .build();
////
////        UserDetails admin = User.withUsername("admin")
////                .password(passwordEncoder.encode("admin"))
////                .roles("USER", "ADMIN")
////                .build();
////
////        return new InMemoryUserDetailsManager(user, admin);
////    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());
//
//        return http.build();
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
////        return encoder;
////    }
//}