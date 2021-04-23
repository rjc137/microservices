package com.sample.emessenger.api.config;

import org.springdoc.data.rest.SpringDocDataRestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringDocDataRestConfiguration.class)
public class SwaggerConfig {

}
