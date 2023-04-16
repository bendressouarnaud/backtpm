package com.example.postgresql.demopostgre.mesobjets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    //@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
    @Configuration
    @EnableWebSecurity
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsServiceImp();
        };

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService()).passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return true;
                }
            });
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //super.configure(http);
            http.csrf().disable();
            http.authorizeRequests().antMatchers("/**/acc",
                    "/secured/** ** /** ** ",
                    "/secured/success",
                    "/secured/socket",
                    "/secured/success",
                    "/**/creer**","/**/nouv**","/**/modif**",
                    "/**/panneau","/**/rapports"
            ,"/**/genrap","/**/taille","/**/affichpanneau**",
                    "/**/quartier","/**/requetepan",
                    "/**/afficherhistopanneau**","/**/secteur",
                    "/**/types","/**/gestionuser","/**/ville").authenticated()
                    .and().
                    formLogin().loginPage("/login").defaultSuccessUrl("/acc").
                    failureUrl("/login/1").permitAll();
            http.authorizeRequests().anyRequest().permitAll();
            http.logout().logoutRequestMatcher(
                    new AntPathRequestMatcher("/deconnex")).
                    logoutSuccessUrl("/acc");
            //http.csrf().and().headers().frameOptions().sameOrigin().and().authorizeRequests();
            //http.csrf();
            http.sessionManagement().maximumSessions(1);
        }
    }

}