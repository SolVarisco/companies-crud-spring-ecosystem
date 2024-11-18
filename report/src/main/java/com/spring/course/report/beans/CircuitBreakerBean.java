package com.spring.course.report.beans;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerBean {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCircuitBreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig
                .custom()
                .failureRateThreshold(60)
                .waitDurationInOpenState(Duration.ofSeconds(70))
                .slidingWindowSize(2)
                .build();

        TimeLimiterConfig timeConfig = TimeLimiterConfig
                .custom()
                .timeoutDuration(Duration.ofSeconds(5))
                .build();

        return resilience4JCircuitBreakerFactory -> resilience4JCircuitBreakerFactory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(timeConfig)
                .circuitBreakerConfig(config)
                .build());
    }
}
