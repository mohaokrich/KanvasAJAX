package des.kanban.security;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import des.kanban.servicios.CustomUserDetailsService;





@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	private CustomUserDetailsService customUserDetailsService;
	@Override
		protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                    .antMatchers(
	                            "/index",
	                            "/js/**",
	                            "/fragments/**",
	                            "/css/**",
	                            "/img/**",
	                            "/bootstrap/**",
	                            "/login",
	                            "/alta",
	                            "/usuario/alta",
	                            "/producto/producto{id}",
	                            "/imagen/{id}",
	                            "/comida/buscar",
	                            "/webjars/**").permitAll()
	                    .antMatchers("/proyecto/crear").hasAuthority("manager")
	                    .antMatchers("/proyecto/buscar/{nombreProyecto}").hasAuthority("manager")
	                    .antMatchers("/proyecto/borrar/{idProyecto}").hasAuthority("manager")
	                    .antMatchers("/proyecto/perfil/{id}").hasAnyAuthority("manager", "trabajador")
	                    .antMatchers("/tarea/crear/{idProyecto}").hasAnyAuthority("manager", "trabajador")
	                    .antMatchers("/tarea/{idTarea}").hasAnyAuthority("manager", "trabajador")
	                    .antMatchers("/tarea/perfilTarea").hasAnyAuthority("manager", "trabajador")
	                    .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                    .loginPage("/index")
	                    .loginProcessingUrl("/login")
	                    .usernameParameter("username")
	                    .passwordParameter("password")
	                    .successHandler(myAuthenticationSuccessHandler())
	                    .permitAll()
	                .and()
	                .logout()
	                    .invalidateHttpSession(true)
	                    .deleteCookies("JSESSIONID")
	                    .clearAuthentication(true)
	                    .logoutUrl("/logout")
	                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                    .logoutSuccessUrl("/login?logout")
	                    .permitAll();
	                http.csrf().ignoringAntMatchers("/imagenes/**");
	    }
	    
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
	        //auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());     
	    }
	    
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			return bCryptPasswordEncoder;
		}
		
	    @Bean
	    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	        return new AuthenticationSuccessHandlerImpl();
	    }
}
