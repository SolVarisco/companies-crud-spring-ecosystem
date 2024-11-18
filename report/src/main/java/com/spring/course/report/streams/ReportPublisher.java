package com.spring.course.report.streams;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReportPublisher {

    private final static String TOPIC_NAME = "consumerReport";
    private final StreamBridge streamBridge;

    /*
    * topic = consumerReport
     */
    public void publishReport(String report) {
      streamBridge.send(TOPIC_NAME, report);
      streamBridge.send(TOPIC_NAME + "-in-0", report);
      streamBridge.send(TOPIC_NAME + "-out-0", report);
    }
}
