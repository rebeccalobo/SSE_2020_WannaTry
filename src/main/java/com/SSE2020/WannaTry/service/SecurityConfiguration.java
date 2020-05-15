package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackages = {"com.SSE2020.WannaTry.databaserepo"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().and()
                .authorizeRequests()
                    .antMatchers("/editDescription","/submit_grade").hasAnyRole("ADMIN","STAFF")
                    .antMatchers("/Dashboard","/StudentModule","/grades","/enrol","/un_enrol","/Payments","/update_balance").hasAnyRole("ADMIN","STAFF","STUDENT")
                    .antMatchers("/","/Home","**/Register","**/resources/**","**/save","/Login","/login_user","/LoginFailed","/blocked").permitAll()
                    .and()
                .formLogin()
                    .loginPage("/Login")
                    .usernameParameter("id").passwordParameter("pwd")
                    .defaultSuccessUrl("/LoginSuccess",true).failureUrl("/LoginFailed")
                    .failureForwardUrl("/LoginFailed")
                    .permitAll()
                    .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                    .permitAll()
                .and()
                .requiresChannel().antMatchers("/*").requiresSecure();

        http.sessionManagement().maximumSessions(1).expiredUrl("/logout");

    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
    //EXCEPTION HANDLERS
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(UserNotFoundException ex, Model model){
        return "redirect:/LoginFailed";
    }

}
