package apap.tugasakhir.rumahsehat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Configuration
    @Order(1)
    public class RestSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private JWTAuthEntryPoint jwtAuthEntryPoint;

        @Autowired
        public JWTRequestFilter jwtRequestFilter;

        @Override
        protected void configure (HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .antMatcher("/api/v1/**")
                    .authorizeRequests()
                    .antMatchers("/api/v1/auth/signin").permitAll()
                    .antMatchers("/api/v1/auth/register").permitAll()
                    .anyRequest().hasAuthority("pasien")
                    .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
    @Configuration
    @Order(2)
    public static class SpringWebConfig extends WebSecurityConfigurerAdapter{

        @Override
        protected void configure (HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .antMatcher("/**")
                    .authorizeRequests()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/").hasAnyAuthority("admin", "dokter", "apoteker")
                    .antMatchers("/login-sso", "/validate-ticket").permitAll()
                    .antMatchers("/appointment").hasAnyAuthority("dokter", "admin")
                    .antMatchers("/appointment/*").hasAnyAuthority("dokter", "admin")
                    .antMatchers("/resep/add").hasAuthority("dokter")
                    .antMatchers("/resep").hasAnyAuthority("admin", "apoteker")
                    .antMatchers("/resep/view").permitAll()
                    .antMatchers("/obat").hasAnyAuthority("admin", "apoteker")
                    .antMatchers("/obat/update").hasAuthority("apoteker")
                    .antMatchers("/user").hasAuthority("admin")
                    .antMatchers("/dokter").hasAuthority("admin")
                    .antMatchers("/apoteker").hasAuthority("admin")
                    .antMatchers("/pasien").hasAuthority("admin")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll()
                    .and().sessionManagement().sessionFixation().newSession().maximumSessions(1);
        }

    }
}
