package com.spring.course.report.repositories;

import com.spring.course.report.beans.LoadBalancerConfiguration;
import com.spring.course.report.models.Company;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "companies-crud")
@LoadBalancerClient(name = "companies-crud", configuration = LoadBalancerConfiguration.class)
public interface CompaniesRepository {

    @GetMapping(path = "/companies-crud/company/{name}")
    Optional<Company> getByName(@PathVariable String name);

    @PostMapping(path = "/companies-crud/company")
    Optional<Company> post(@RequestBody Company company);

    @DeleteMapping(path = "/companies-crud/company/{name}")
    void delete(@PathVariable String name);
}
