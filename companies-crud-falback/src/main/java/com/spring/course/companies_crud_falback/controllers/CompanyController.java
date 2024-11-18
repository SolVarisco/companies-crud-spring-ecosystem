package com.spring.course.companies_crud_falback.controllers;

import com.spring.course.companies_crud_falback.models.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;

@RestController
@RequestMapping(path = "company")
@Slf4j
public class CompanyController {

    private static final Company DEFAULT_COMPANY = Company
            .builder()
            .id(0L)
            .founder("Fallback")
            .name("Fallback company")
            .logo("http://default-logo.com")
            .foundationDate(LocalDate.now())
            .webSites(Collections.emptyList())
            .build();

    @GetMapping(path = "{name}")
    public ResponseEntity<Company> get(@PathVariable String name) {
        log.info("GET in fallback: company {}", name);
        return ResponseEntity.ok(DEFAULT_COMPANY);
    }

}

