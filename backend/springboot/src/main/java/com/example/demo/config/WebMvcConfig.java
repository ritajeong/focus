package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:3000/");
////        configuration.addAllowedOriginPattern("*");
//        configuration.addAllowedMethod(HttpMethod.GET);
//        configuration.addAllowedMethod(HttpMethod.POST);
//        configuration.addAllowedMethod(HttpMethod.DELETE);
//        configuration.addAllowedMethod(HttpMethod.PUT);
//        configuration.addAllowedHeader("*");
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**").allowedOrigins("https://i5a107.p.ssafy.io/").allowedMethods("*").allowCredentials(true).maxAge(360);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    		registry.addResourceHandler("/resources/**")
    				.addResourceLocations("/WEB-INF/resources/");
    		
    		registry.addResourceHandler("swagger-ui.html")
    				.addResourceLocations("classpath:/META-INF/resources/");

    		registry.addResourceHandler("/webjars/**")
    				.addResourceLocations("classpath:/META-INF/resources/webjars/");
    		
    		/*
    		 * 
    		 * Front-end에서 참조하는 URL을 /dist로 매핑
    		 * 
    		 */
        registry.addResourceHandler("/css/**")
        			.addResourceLocations("classpath:/static/css/"); 
        registry.addResourceHandler("/scss/**")
                .addResourceLocations("classpath:/static/assets/scss/"); 
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/assets/fonts/"); 
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts/"); 
        registry.addResourceHandler("/icons/**")
				.addResourceLocations("classpath:/static/icons/");
        registry.addResourceHandler("/img/**")
			.addResourceLocations("classpath:/static/img/"); 
        registry.addResourceHandler("/js/**")
				.addResourceLocations("classpath:/static/js/");  
    }

    public Filter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }

    @Bean
    public FilterRegistrationBean loggingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(requestLoggingFilter());
        registration.addUrlPatterns("/api/*");
        return registration;
    }
}
