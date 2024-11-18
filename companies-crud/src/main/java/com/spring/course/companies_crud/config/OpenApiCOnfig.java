package com.spring.course.companies_crud.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "companies CRUD",
                version = "0.0.1",
                description = "CRUD for managing companies"
        )
)
public class OpenApiCOnfig {
}
