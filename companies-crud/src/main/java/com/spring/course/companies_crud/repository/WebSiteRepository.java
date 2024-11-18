package com.spring.course.companies_crud.repository;

import com.spring.course.companies_crud.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebSiteRepository extends JpaRepository<WebSite, Long> {
}
