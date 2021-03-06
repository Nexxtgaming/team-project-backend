package teamproject.cs5.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import teamproject.cs5.security.jwt.AuthEntryPointJwt;
import teamproject.cs5.security.jwt.AuthTokenFilter;
import teamproject.cs5.security.services.UserDetailsServiceImpl;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/docs/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/confirmationToken/*").permitAll().and()
                .authorizeRequests().antMatchers("/api/accommodationOffer/*").permitAll().and()
                .authorizeRequests().antMatchers("/api/accommodationOffer").permitAll().and()
                .authorizeRequests().antMatchers("/api/translationOffer/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/transportationOffer/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/article/*").permitAll().and()
                .authorizeRequests().antMatchers("/api/article").permitAll().and()
                .authorizeRequests().antMatchers("/api/volunteersOffer/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/jobOffer/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/article/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/languageCourses/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/legalAdvices/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/lookingForOffers/**").permitAll()
                .antMatchers("/api/test/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/users/*/publicInfo").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
