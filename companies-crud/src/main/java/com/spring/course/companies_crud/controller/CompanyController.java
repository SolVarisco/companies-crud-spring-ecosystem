package com.spring.course.companies_crud.controller;

import com.spring.course.companies_crud.entity.Company;
import com.spring.course.companies_crud.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping(path = "company")
@Slf4j
@Tag(name = "Companies Reource")
public class CompanyController {
    private final CompanyService companyService;

    @Operation(summary = "get a company by name")
    @GetMapping(path = "{name}")
    public ResponseEntity<Company> get(@PathVariable String name) {
        log.info("GET: company {}", name);
        return ResponseEntity.ok(companyService.readByName(name));
    }

    @Operation(summary = "create a company")
    @PostMapping
    public ResponseEntity<Company> post(@RequestBody Company company) {
        log.info("POST: company {}", company.getName());
        return ResponseEntity
                .created(URI.create(companyService.create(company).getName()))
                .build();
    }

    @Operation(summary = "update a company by name")
    @PutMapping(path = "{name}")
    public ResponseEntity<Company> put(@PathVariable String name,
                                       @RequestBody Company company) {
        log.info("PUT: company {}", name);
        return ResponseEntity.ok(companyService.update(company, name));
    }

    @Operation(summary = "delete a company by name")
    @DeleteMapping(path = "{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        log.info("DELETE: company {}", name);
        companyService.delete(name);
        return ResponseEntity.noContent().build();
    }
}
