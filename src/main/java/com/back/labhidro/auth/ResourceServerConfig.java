package com.back.labhidro.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
 * @Autor: Javiko
 * 
 * Para dar accesos a las del back  a los usuarios segun su rol
 * Configuracion de cors
 * 
 * */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, 	"/api/servicio/lista/disponible", 
										"/api/servicio/uploads/img/**", 
										"/api/paquete/lista/disponible",
										"/api/paquete/uploads/img/**",
										"/api/matriz/uploads/img/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/cliente/crear").permitAll()
		// admin y servicio
		.antMatchers("/api/tipo/**").hasAnyRole("ADMIN", "SERVICIO")
		.antMatchers("/api/servicio/**").hasAnyRole("ADMIN", "SERVICIO")
		.antMatchers("/api/paquete/**").hasAnyRole("ADMIN", "SERVICIO")
		// admin y venta
		.antMatchers("/api/venta/**").hasAnyRole("ADMIN", "VENTA")		
		.antMatchers("/api/cliente/**").hasAnyRole("ADMIN", "VENTA")
		// admin
		.antMatchers("/api/sexo/**").hasRole("ADMIN")
		.antMatchers("/api/matriz/**").hasRole("ADMIN")
		.antMatchers("/api/usuario/**").hasRole("ADMIN")
		.antMatchers("/api/rol/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());

	}
	
	

	// Configuracion Cors para informacion cruzada
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuracion = new CorsConfiguration();
		configuracion.setAllowedOrigins(Arrays.asList("*"));
		configuracion.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuracion.setAllowCredentials(true);
		configuracion.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuracion);

		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
