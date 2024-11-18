package com.spring.course.report.service;

import com.spring.course.report.helpers.ReportHelper;
import com.spring.course.report.models.Company;
import com.spring.course.report.models.WebSite;
import com.spring.course.report.repositories.CompaniesFallbackRepository;
import com.spring.course.report.repositories.CompaniesRepository;
import com.spring.course.report.streams.ReportPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
@AllArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final CompaniesRepository companiesRepository;
    private final CompaniesFallbackRepository companiesFallbackRepository;
    private final ReportHelper reportHelper;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
    private final ReportPublisher reportPublisher;

    @Override
    public String makeReport(String name) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("companies-circuitbreaker");
        return circuitBreaker.run(
                () -> makeReportMain(name),
                throwable -> makeReportFallback(name, throwable)
        );
    }

    @Override
    public String saveReport(String report) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<String> placeholder = reportHelper.getPlaceholdersFromTemplate(report);
        List<WebSite> webSites = Stream.of(placeholder.get(3))
                .map(webSite -> {
                    return WebSite.builder()
                            .name(webSite)
                            .build();
                })
                .toList();
        Company company = Company.builder()
                .name(placeholder.get(0))
                .foundationDate(LocalDate.parse(placeholder.get(1), format))
                .founder(placeholder.get(2))
                .webSites(webSites)
                .build();
        companiesRepository.post(company);
        reportPublisher.publishReport(report);
        return "Saved";
    }

    @Override
    public void deleteReport(String name) {
        companiesRepository.delete(name);
    }

    public String makeReportMain(String name) {
        return reportHelper.readTemplate(companiesRepository.getByName(name).orElseThrow());
    }

    public String makeReportFallback(String name, Throwable throwable) {
        log.warn(throwable.getMessage());
        return reportHelper.readTemplate(companiesFallbackRepository.getByName(name));
    }
}
