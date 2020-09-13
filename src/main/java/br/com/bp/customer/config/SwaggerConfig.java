package br.com.bp.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author <a href="mailto:thiagobritorocha@yahoo.com.br">thiago.rocha</a>
 * @date 11 de sep de 2020 10:34:45
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket customerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        	.groupName("customer-api-1.0")
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/api/customer/v1.0.*.*"))
            .build()
            .apiInfo(new ApiInfoBuilder()
            		.version("1.0")
            		.title("Customer API")
            		.description("Documentation Customer API v1.0")
            		.build());
    }
    

	@Bean
	public Docket swaggerPersonApi11() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("customer-api-1.1")
	        .select()
	        .apis(RequestHandlerSelectors.any())
	        .paths(PathSelectors.regex("/api/customer/v1.1.*"))
	        .build()
	        .apiInfo(new ApiInfoBuilder()
	        		.version("1.1")
	        		.title("Person API")
	        		.description("Documentation Customer API v1.1")
	        		.build());
	}
}