package com.arquitetura.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arquitetura.security.JWTAutheticationFilter;
import com.arquitetura.security.JWTAuthorizationFilter;
import com.arquitetura.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private Environment enviroment;
	
	@Value("${spring.profiles.active}")
    private String profile;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	public static final String DEV = "dev";
	
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
			"/usuario/**",
			"/teste/**",
			"/swagger-ui.html/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/cliente/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/usuario/**",
			"/auth/forgot/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if (Arrays.asList(enviroment.getActiveProfiles()).contains("dev")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.anyRequest().authenticated();
		http.addFilter(new JWTAutheticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//		return (CorsConfigurationSource) source;
//	}
	
	/**
	 * first example
	 */
	
//	@Bean
//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		return super.authenticationManager();
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("ivonildolopes").password("123456").roles("ADM");
//	}

//	@SuppressWarnings("deprecation")
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
}
