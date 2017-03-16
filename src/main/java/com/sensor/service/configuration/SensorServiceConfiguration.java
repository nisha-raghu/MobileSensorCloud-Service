package com.sensor.service.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by nisha on 11/12/16.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.sensor.service")
public class SensorServiceConfiguration {
}
