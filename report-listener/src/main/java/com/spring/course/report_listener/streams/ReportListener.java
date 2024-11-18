package com.spring.course.report_listener.streams;

import com.spring.course.report_listener.documents.ReportDocument;
import com.spring.course.report_listener.reporistories.ReportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
@AllArgsConstructor
public class ReportListener {

    private final ReportRepository reportRepository;

    @Bean
    public Consumer<String> consumerReport() {
        return report -> {
            log.info("Saving report: {}", report);
            reportRepository.save(ReportDocument.builder()
                            .content(report)
                    .build());
            log.info("Report {} saved", report);
        };
    }
}
