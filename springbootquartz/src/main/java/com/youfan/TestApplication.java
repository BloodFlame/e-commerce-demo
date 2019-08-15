package com.youfan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * quartz定时任务分布式节点-1
 * 程序启动入口
 */
@SpringBootApplication
public class TestApplication {
    /**
	 * logback
	 */
	private static Logger logger = LoggerFactory.getLogger(TestApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(TestApplication.class, args);

		logger.info("【【【【【【定时任务分布式节点 - quartz-cluster-node-first 已启动】】】】】】");
	}
}
