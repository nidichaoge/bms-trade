package com.mouse.bms.trade.config;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.time.Duration;
import java.time.Instant;

@SpringBootApplication(scanBasePackages = {"com.mouse.bms.trade"},exclude = {DataSourceAutoConfiguration.class})
@EnableDubboConfiguration
//@Enable
//@EnableReactiveMongoRepositories("com.mouse.bms.trade.dal.repository")
//@EnableRedisRepositories("com.mouse.bms.trade")
//@EntityScan("com.mouse.bms.trade.dal")
public class BmsTradeConfigApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(BmsTradeConfigApplication.class);

    public static void main(String[] args) {
        Instant startTime = Instant.now();

        SpringApplication.run(BmsTradeConfigApplication.class, args);

        Instant endTime = Instant.now();
        LOGGER.info("启动成功，耗时:" + Duration.between(startTime, endTime).getSeconds() + "S");
    }

}
