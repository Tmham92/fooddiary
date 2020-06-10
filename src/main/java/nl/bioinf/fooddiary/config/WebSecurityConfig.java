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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author Hans Zijlstra
 * This class handels the security of the application.
 * Here authorisation and privileges for users are set,
 * This class further controlls login functionallity and database and page access.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * Passwordencoder for hashing string passwords.
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Methods that checks if a user is authenticated and which role this user has by querying the database
     */

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.jdbcAuthentication().dataSource(this.jdbcTemplate.getDataSource())
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery(
                        "select user_code, password, enabled from user where user_code = ? and enabled = true")
                .authoritiesByUsernameQuery(
                        "select user_code, role from user where user_code = ?");
    }

    /**
     * Method that configures the application authorisation
     * Users are authorised to see different pages and have several privileges based on the role of the user
     * For specific privileges the hasRole method is used to indicate specific privileges
     * Furthermore login request are handled when this method is called
     * @param http (HttpSecurity)
     */
    // TODO: /data url can now be accessed, at a later stage this should be removed and only visible for Web-Developer. - Tom
    // TODO: /adduser, /verify-recipe-by-admin can be accessed by role USER, should change to only be accessible by ADMIN. -Hugo
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers( "/", "/home", "/*/home", "/images/**", "/css/**", "/js/**", "/contact", "/*/contact", "/**/newproductform", "/newproductform", "/addednewproduct", "/**/addednewproduct"
                        ,"/getnewproducts", "/**/getnewproducts").permitAll()
                .antMatchers("**/diary-entry", "/diary-entry", "/diary-entry/**", "/product-description", "/verify-recipe-by-admin", "/adduser", "/*/adduser").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/home")
                .failureUrl("/login-error.html")
                .permitAll()
                .defaultSuccessUrl("/default")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)        // set invalidation state when logout
                .deleteCookies("JSESSIONID");
    }

}
