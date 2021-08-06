package com.example.demo;

import java.nio.charset.Charset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	  @Bean
//	    public HttpMessageConverter<String> responseBodyConverter() {
//	        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
//	    }
//
//	    @Bean
//	    public CharacterEncodingFilter characterEncodingFilter() {
//	        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//	        characterEncodingFilter.setEncoding("UTF-8");
//	        characterEncodingFilter.setForceEncoding(true);
//	        return characterEncodingFilter;
//	    }
}
