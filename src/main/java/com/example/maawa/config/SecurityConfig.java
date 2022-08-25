package com.example.maawa.config;


import com.example.maawa.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/User/add","/api/v1/product/byStoreId","/api/v1/picture/byProduct","/api/v1/service/byClinic").permitAll()
                // ADMIN
                .antMatchers("/api/v1/User/get","/api/v1/User/delete/**","/api/v1/User/makeApprovment/**",
                "/api/v1/category/get","/api/v1/product/get","/api/v1/picture/get","/api/v1/order/get",
                        "/api/v1/item/get","/api/v1/service/get","/api/v1/timeSlot/get","/api/v1/appointment/get").hasAuthority("admin")

                // STORE
                .antMatchers("/api/v1/category/add","/api/v1/category/delete/**","/api/v1/category/update/**","/api/v1/category/myCategories",
                    "/api/v1/product/add","/api/v1/product/delete/**","/api/v1/product/update/**","/api/v1/product/myProducts",
                    "/api/v1/picture/add","/api/v1/picture/delete/**","/api/v1/picture/update/**",
                        "/api/v1/order/status/**","/api/v1/order/byStoreId").hasAuthority("store")

                // CLINIC
                .antMatchers("/api/v1/service/add","/api/v1/service/delete/**","/api/v1/service/update/**","/api/v1/service/myServices",
                        "/api/v1/timeSlot/add","/api/v1/timeSlot/delete/**","/api/v1/timeSlot/update/**",
                        "/api/v1/appointment/changeStatus/**").hasAuthority("clinic")

                //CUSTOMER
                .antMatchers("/api/v1/order/add","/api/v1/order/update/**","/api/v1/order/myOrders",
                        "/api/v1/item/add","/api/v1/item/delete/**","/api/v1/item/update/**","/api/v1/item/MyItems",
                        "/api/v1/timeSlot/byService","/api/v1/appointment/add","/api/v1/appointment/delete/**","/api/v1/appointment/update/**",
                        "/api/v1/appointment/myAppointment").hasAuthority("customer")
                .antMatchers().hasAnyAuthority()

                //CUSTOMER OR STORE
                .antMatchers("/api/v1/item/getOrderItems","/api/v1/order/delete/**").hasAnyAuthority("store","customer")
               // .antMatchers("/api/v1/item/getOrderItems","/api/v1/order/{id}").access("hasAuthority('store')")


                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .httpBasic();
    }
}