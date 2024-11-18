package com.spring.course.report.controllers;

import com.spring.course.report.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/report")
public class ReportController {

    private ReportService reportService;

    @GetMapping(path = "{name}")
    public ResponseEntity<Map<String, String>> getReport(@PathVariable String name) {
        Map<String, String> response = Map.of("report", reportService.makeReport(name));
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<String> postReport(@RequestBody String name) {
        return ResponseEntity.ok(reportService.saveReport(name));
    }

    @DeleteMapping(path = "{name}")
    public ResponseEntity<Void> deleteReport(@PathVariable String name) {
        reportService.deleteReport(name);
        return ResponseEntity.noContent().build();
    }

}
