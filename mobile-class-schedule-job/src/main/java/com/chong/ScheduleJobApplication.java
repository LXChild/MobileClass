package com.chong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduleJobApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleJobApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ScheduleJobApplication.class, args);
		LOGGER.info("mobile class schedule job started.");
	}
}
