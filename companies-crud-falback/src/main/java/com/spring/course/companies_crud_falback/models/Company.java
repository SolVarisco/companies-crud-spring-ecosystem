package com.spring.course.companies_crud_falback.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private Long id;
    private String name;
    private String logo;
    private LocalDate foundationDate;
    private String founder;
    private List<WebSite> webSites;
}
