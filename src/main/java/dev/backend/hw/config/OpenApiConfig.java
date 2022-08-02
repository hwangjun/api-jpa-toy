package dev.backend.hw.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * swagger 3.0 이름이 openapi 로 변겯됨
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {
        Info info = new Info().title("Demo API").version(appVersion)
                .description("Spring Boot 와 Open Api(Swagger) 이용한 REST API 입니다.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("hwangjun").url("https://github.com/hwangjun").email("hwangjun9349@gmail.com"))
                .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

}
