package com.spring.course.report_listener.reporistories;

import com.spring.course.report_listener.documents.ReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<ReportDocument, String> {
}
