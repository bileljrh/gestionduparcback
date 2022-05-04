package com.gesparc.configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

//	
//	@Bean
//	public OpenAPI customOpenAPI(@Value("${app.version}") String appVersion) {
//		return new OpenAPI()
//				.components(new Components()
//						.addSecuritySchemes("basicScheme",
//								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic"))
//						.addParameters("myHeader1",
//								new Parameter().in("header").schema(new StringSchema()).name("myHeader1"))
//						.addHeaders("myHeader2",
//								new Header().description("myHeader2 header").schema(new StringSchema())))
//				.info(new Info().title("Tfm GESPARC API").version(appVersion)
//						.description("API for work scope management.")
//						.contact(new Contact().name("Tfm").email("contact@tfm.com.tn").url("https://www.tfm.com.tn")));
//	}

}
