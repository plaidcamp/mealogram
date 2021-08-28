package com.plaidcamp.mealogram.common.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StatusCheckConfiguration {

    Logger logger = LoggerFactory.getLogger(StatusCheckConfiguration.class);

    @Value("${profile}")
    private String profile;

    @PostConstruct
    private void method() {
        logger.info("------------PROFILE : " + profile.toUpperCase() + "-----------------");
    }

}
