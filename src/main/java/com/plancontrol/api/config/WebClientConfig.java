package com.plancontrol.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

	@Value("${api.gateway.url}")
	private String gatewayUrl;

	private final TokenPropagationFilter tokenPropagationFilter;

	@Bean
	WebClient webClient() {
		return WebClient.builder().baseUrl(gatewayUrl).filter(tokenPropagationFilter)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
}
