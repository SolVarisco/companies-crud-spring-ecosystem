package com.spring.course.report.repositories;

import com.spring.course.report.models.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
@Slf4j
public class CompaniesFallbackRepository {

    private final WebClient webCLient;
    private final String uri;

    public CompaniesFallbackRepository(WebClient webCLient,
                                       @Value("${fallback.uri}") String uri) {
        this.webCLient = webCLient;
        this.uri = uri;
    }

    public Company getByName(String name) {
        log.warn("Llamando a companies fallback");
        return webCLient
                .get()
                .uri(uri, name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Company.class)
                .log()
                .block();
    }
}
