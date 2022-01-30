package ru.job4j.forum.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.job4j.forum.models.User;
import ru.job4j.forum.services.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passEncoder;
    private DataSource ds;
    private UserService users;

    public WebSecurityConfig(
            PasswordEncoder encoder,
            DataSource dataSource,
            UserService service1
    ) {
        passEncoder = encoder;
        ds = dataSource;
        users = service1;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String usersSQL =
            "SELECT login AS username, pass AS password, enabled "
            + "FROM tz_users WHERE login = ?";
        String rolesSQL =
            "SELECT u.login AS username, a.authority "
            + "FROM tz_roles AS a "
            + "INNER JOIN tz_users AS u ON a.id=u.id_role AND u.login=?";
        auth
        .jdbcAuthentication()
        .passwordEncoder(passEncoder)
        .dataSource(ds)
        .usersByUsernameQuery(usersSQL)
        .authoritiesByUsernameQuery(rolesSQL);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(
                "/", "/home", "/login", "/register", "/scripts/**", "/styles/**", "/fonts/**"
        ).permitAll()
        .antMatchers("/**").hasAnyRole("ADMIN", "USER")
        .and()
        .formLogin().loginPage("/login")
        .defaultSuccessUrl("/login?success=true")
        .failureUrl("/login?error=true").permitAll()
        .and()
        .logout().logoutSuccessUrl("/login?logout=true")
        .invalidateHttpSession(true).permitAll()
        .and()
        .csrf().disable();
    }

    @Bean
    @Scope("prototype")
    public Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}