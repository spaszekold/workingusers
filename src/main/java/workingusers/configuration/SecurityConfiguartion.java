package workingusers.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;
import workingusers.service.CurrentUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Tomek on 2015-04-30.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguartion extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private CurrentUserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties security;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().antMatchers("/logout", "/api2/**", "/img/**", "/user/**", "/angularviews/**", "/fonts/**", "/home", "/", "/css/**", "/bower_components/**", "/js/**", "/api/**", "/post/**").permitAll().anyRequest().fullyAuthenticated()
         .and()
         .formLogin().loginPage("/login").defaultSuccessUrl("/post/page/1").failureUrl("/login?error").usernameParameter("email").permitAll()
         .and()
         .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutUrl("/logout").logoutSuccessUrl("/post/page/1").permitAll()
         .and()
         .csrf().csrfTokenRepository(csrfTokenRepository())
         .and()
         .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

         }

         @Override public void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
         }




        //WE NEED TOKEN REPOSITORY, AS ANGULAR CSRF TOKEN DIFFERS FROM SPRING'S DEFAULT CSRF TOKEN
        //HEADER GETS RENAMED TO PROPER, ANGULAR ONE
         private CsrfTokenRepository csrfTokenRepository() {
         HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
         repository.setHeaderName("X-XSRF-TOKEN");
         return repository;
         }




         public class CsrfHeaderFilter extends OncePerRequestFilter {
         @Override protected void doFilterInternal(HttpServletRequest request,
         HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {
         CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
         .getName());
         if (csrf != null) {
         Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
         String token = csrf.getToken();
         if (cookie==null || token!=null && !token.equals(cookie.getValue())) {
         cookie = new Cookie("XSRF-TOKEN", token);
         cookie.setPath("/");
         response.addCookie(cookie);
         }
         }
         filterChain.doFilter(request, response);
         }
    }


}
