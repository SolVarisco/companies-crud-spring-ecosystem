package com.spring.course.report.beans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
public class LoadBalancerConfiguration {

    @Bean
    public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
        log.info("Configurando load balancer");
        return ServiceInstanceListSupplier
                .builder()
                .withBlockingDiscoveryClient() // Indica que no es reactivo
                .build(context);
    }
}
