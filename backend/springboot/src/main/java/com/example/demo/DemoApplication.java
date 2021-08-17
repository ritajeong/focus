package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
