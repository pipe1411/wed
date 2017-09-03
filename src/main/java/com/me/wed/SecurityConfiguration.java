package com.me.wed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

/**
 * Created by pipe on 8/28/17.
 */

@Configuration
/*@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)*/
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {
/*    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/


    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
                .anyRequest().authenticated().and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
/*        http
                .authorizeRequests()
                .antMatchers("/js*//**//**","/plugins*//**//**","/ui-router","/jquery*//**//**","/bootstrap*//**//**","/angular-translate*//**//**","/angular*//**//**","/img*//**//**","/fonts*//**//**","/views*//**//**","/pdf*//**//**","/email_templates*//**//**","/css*//**//**", "/registration","/landing","/templates/landing","templates/index","/"*//**//*,"/css/bootstrap.min.css","/font-awesome/css/font-awesome.css","/css/animate.css","/css/style.css","/js/controllers.js","/js/directives.js","/js/translations.js","/js/config.js","/js/app.js","/js/plugins/angular-idle/angular-idle.js","/js/bootstrap/ui-bootstrap-tpls-1.1.2.min.js","/js/ui-router/angular-ui-router.min.js","/js/angular-translate/angular-translate.min.js","/js/plugins/oclazyload/dist/ocLazyLoad.min.js","/js/angular/angular-sanitize.js","/js/angular/angular.min.js","/js/inspinia.js","/js/plugins/pace/pace.min.js","/js/plugins/slimscroll/jquery.slimscroll.min.js","/js/plugins/metisMenu/jquery.metisMenu.js","/js/bootstrap/bootstrap.min.js","/js/plugins/jquery-ui/jquery-ui.min.js","/js/jquery/jquery-3.1.1.min.js","/css/bootstrap.min.css","/font-awesome/css/font-awesome.css","/css/animate.css","/css/style.css"*//**//*).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();*//*
    }*/

/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());    }*/

}
