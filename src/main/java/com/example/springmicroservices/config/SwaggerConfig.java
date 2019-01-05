package com.example.springmicroservices.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is the configuration class for swagger documenetation
 * URL: http://localhost:8080/v2/api-docs, http://localhost:8080/swagger-ui.html
 * 
 * @author HP
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final Contact DEFAULT_CONTACT = new Contact("Gagandeep Singh Saini","www.fis.com","g.saini2692@gmail.com");
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("My Rest Service", "Api Description", "1.0",
			"urn:local", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE_2.0");
	private static final Set<String> DEFAULT_PRODUCES_CONSUMES = 
			new HashSet<String>(Arrays.asList("application/json", "application/xml"));   //"application/json", "application/xml"

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_CONSUMES)
				.consumes(DEFAULT_PRODUCES_CONSUMES);
	}

}
