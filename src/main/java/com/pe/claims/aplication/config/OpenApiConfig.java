package com.pe.claims.aplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

//@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact =  @Contact(
                        name = "Fernando",
                        email = "rojofernando296@gmailcom",
                        url = "https://my-profile-six-rust.vercel.app/"
                ),
                description = "PROYECTO INTEGRADOR 1 - CLAIMS",
                title = "OpenApi specification - Fernando",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terms of service"
        ),
        /*servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "http://52.90.246.81:8080"
                )
        },
        */
        security = {
                @SecurityRequirement(
                        name = "Api-Key"
                )
}
)
@SecurityScheme(
        name = "Api-Key",
        description = "Set your API TOKEN here",
        scheme = "api_key",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER
        //name = "Api-Key"

)
public class OpenApiConfig {

}
