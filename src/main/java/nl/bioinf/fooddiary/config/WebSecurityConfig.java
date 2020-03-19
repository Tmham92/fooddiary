package nl.bioinf.fooddiary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;


<<<<<<< HEAD
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(this.jdbcTemplate.getDataSource())
                .usersByUsernameQuery(
                        "select email, password, enabled from users where email = ? and enabled = true")
                .authoritiesByUsernameQuery(
                        "select email, authority from users where email = ?");
    }
=======
>>>>>>> 7eaf715b9f6af0a70cc142240392fecf662949f5

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


<<<<<<< HEAD
=======
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.jdbcAuthentication().dataSource(this.jdbcTemplate.getDataSource())
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery(
                        "select user_code, password, enabled from user where user_code = ? and enabled = true")
                .authoritiesByUsernameQuery(
                        "select user_code, role from user where user_code = ?");
    }

>>>>>>> 7eaf715b9f6af0a70cc142240392fecf662949f5
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
<<<<<<< HEAD
                .antMatchers( "/", "/home", "/*/home", "/images/**", "/css/**", "/contact", "/newProductForm").permitAll()
=======
                .antMatchers( "/", "/home", "/*/home", "/images/**", "/css/**", "/contact", "/*/contact").permitAll()
>>>>>>> 7eaf715b9f6af0a70cc142240392fecf662949f5
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/home")
                    .failureUrl("/login-error.html")
                    .permitAll()
                    .defaultSuccessUrl("/contact")
                    .and()

                .logout()
                    .permitAll();
    }

}
