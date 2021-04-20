package com.magneto.mutant.swagger;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	   @Bean
	   public Docket apiDocket() {
	       
	       Contact contact = new Contact(
	               "Pedro Antonio Chacón Garnica",
	               "#", 
	               "pedro.chacon29@hotmail.com"
	       );
	       
	       List<VendorExtension> vendorExtensions = new ArrayList<>();
	       
	       ApiInfo apiInfo = new ApiInfo(
	      "Magneto Reclutamiento de Mutantes", 
	      "Esta página web documenta los endpoint de microservicios para la identificación de mutantes", "1.0",
	      "#", contact, 
	      "Software desarrollado bajo licencia Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",vendorExtensions);
	       
	       Docket docket =  new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.magneto.mutant.controller"))
	                .paths(PathSelectors.any())
	                .build();
	       
	       return docket;
	       
	    } 
}
