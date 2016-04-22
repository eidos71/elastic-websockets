package com.blackswan.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.blackswan")
@EnableAutoConfiguration(exclude = {ElasticsearchAutoConfiguration.class})
public class BlackSwanElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackSwanElasticApplication.class, args);
	}
}
