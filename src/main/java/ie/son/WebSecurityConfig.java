package ie.son;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig {
	
	//passwordEncoder 
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	      return bCryptPasswordEncoder;
	  }
	  
	// https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#multiple-httpsecurity
	@Configuration
	@Order(1)	// First to be checked....
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
			.antMatcher("/api/**")
			.authorizeRequests()
				.anyRequest().hasRole("API")
			.and()
				.httpBasic();
		}

		@Autowired
		DataSource dataSource;
				
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
			 .usersByUsernameQuery("select user.Email, user.Password, user.userEnabled from user where user.Email=?")
	  .authoritiesByUsernameQuery("select role.UserEmail, role.roleDescription from role where role.UserEmail=?");	  
		}
	}

	@Configuration
	public class FormLoginSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter  {
		@Autowired
		DataSource dataSource;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/css/**", "/", "/showjobs/**","/showjob/**","/register/**","/h2-console/**", "/login/**","/error/**").permitAll()
				.antMatchers("/newjob").hasAnyRole("ADMIN", "USER") 
				.antMatchers("/newbid/").hasAnyRole("ADMIN", "USER") 
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").permitAll()
					.usernameParameter("email")
		    .and()
		    	.httpBasic()
			.and()
				.logout()
					.logoutSuccessUrl("/index")
						.permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/403");
			
			http.csrf().disable();	// for h2 console
			http.headers().frameOptions().disable();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
			 .usersByUsernameQuery("select user.Email, user.Password, user.userEnabled from user where user.Email=?")
	  .authoritiesByUsernameQuery("select role.UserEmail, role.roleDescription from role where role.UserEmail=?");	  
		}
	}
}
