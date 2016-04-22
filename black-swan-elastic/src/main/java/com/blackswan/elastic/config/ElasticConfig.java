package com.blackswan.elastic.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@PropertySource(value = "classpath:elasticsearch.properties")
@EnableElasticsearchRepositories(basePackages = "com.blackswan.elastic.repository")
public class ElasticConfig {
	@Resource
	private Environment environment;
	 private static Logger LOG = LoggerFactory.getLogger(ElasticConfig.class);
	@Bean
	public Client clientLocal() {
		TransportClient client = new TransportClient();
		TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"),
				Integer.parseInt(environment.getProperty("elasticsearch.port")));
		client.addTransportAddress(address);
		return client;
	}
    @Bean
    @Primary
    public Client clientBuild() {
        try {
            Path tmpDir = Files.createTempDirectory(Paths.get(System.getProperty("java.io.tmpdir")), "elasticsearch_data");

            ImmutableSettings.Builder elasticsearchSettings = ImmutableSettings.settingsBuilder()
                    .put("http.enabled", "false")
                    .put("path.data", tmpDir.toAbsolutePath().toString());

            LOG.debug(tmpDir.toAbsolutePath().toString());

            return new NodeBuilder()
                    .local(true)
                    .settings(elasticsearchSettings.build())
                    .node()
                    .client();
        } catch (IOException ioex) {
            LOG.error("Cannot create temp dir", ioex);
            throw new RuntimeException();
        }
    }

	 @Bean
	 public ElasticsearchOperations elasticsearchTemplateLocal() {
	        return new ElasticsearchTemplate(clientLocal());
	     }
	 @Bean
	 @Primary
	 public ElasticsearchOperations elasticsearchTemplateBuildIn() {
	        return new ElasticsearchTemplate(clientBuild());
	     } 
}
