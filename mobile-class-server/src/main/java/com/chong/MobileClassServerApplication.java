package com.chong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MobileClassServerApplication extends SpringBootServletInitializer implements DisposableBean {
	private static final Logger APPLICATION_LOGGER = LoggerFactory.getLogger(MobileClassServerApplication.class);
	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) {
		ctx = SpringApplication.run(MobileClassServerApplication.class, args);
		APPLICATION_LOGGER.info("Mobile Class Server started.");
	}

	@Override
	public void destroy() throws Exception {
		if (ctx != null && ctx.isRunning()) {
			ctx.close();
		}
	}
}
