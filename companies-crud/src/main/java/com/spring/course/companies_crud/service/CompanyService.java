package com.spring.course.companies_crud.service;

import com.spring.course.companies_crud.entity.Company;

public interface CompanyService {
    Company readByName(String name);
    Company create(Company company);
    Company update(Company company, String name);
    void delete(String name);
}
