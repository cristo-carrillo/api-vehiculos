package com.vehiculos.documentation;

import com.vehiculos.utils.ApiResponse;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.JsonSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


@Configuration
@SecurityScheme(name = "jwt", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(@Value("${appDescription}") String appDescription,
                                 @Value("${appTittle}") String appTittle,
                                 @Value("${appVersion}") String appVersion) {
        Schema<?> mapSchema = new Schema<ApiResponse>()
                .addProperty("message", new StringSchema().example("Éxito"))
                .addProperty("data", new JsonSchema().example(""));
        Schema<?> errorSchema = new Schema<Map<String, String>>()
                .addProperty("error", new StringSchema().example(new ApiResponse("Error", "")));
        return new OpenAPI()
                .info(new Info()
                        .title(appTittle)
                        .description(appDescription)
                        .contact(contact())
                        .version(appVersion)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                        .components(new Components()
                                .addSchemas("message", mapSchema)
                                .addSchemas("error", errorSchema));
    }

    private Contact contact() {
        Contact contact = new Contact();
        contact.email("cjcarrillos@ufpso.edu.co\n" +
                "ypadrol@ufpso.edu.co");
        contact.name("Cristo Carrillo" +
                "\nYurgen Prado");
        return contact;
    }
}
