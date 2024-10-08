package com.shadril238.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.shadril238.accounts.controller") })
@EnableJpaRepositories("com.shadril238.accounts.repository")
@EntityScan("com.shadril238.accounts.entity")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") // Enable JPA Auditing
@OpenAPIDefinition(
        info = @Info(
                title = "Account microservice REST API documentation",
                description = "Eazy Bank Account microservice REST API documentation",
                version = "v1",
                contact = @Contact(
                        name = "Shadril Hassan",
                        email = "shadrilhassan@outlook.com",
                        url = "brainstation-23.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EazyBank Accounts microservice REST API documentation",
                url = "https://eazybank.com/swagger-ui.html"
        )
)
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

}
