package com.plancontrol.api.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

@Component
public class TokenPropagationFilter implements ExchangeFilterFunction {
	
	@Override
	public Mono<ClientResponse> filter(ClientRequest request,
			ExchangeFunction next) {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		if (attrs != null) {
			HttpServletRequest servletRequest = attrs.getRequest();
			String token = servletRequest.getHeader("Authorization");
			String xInternalToken = servletRequest.getHeader("x-internal-token");

			if (token != null) {
				ClientRequest newRequest = ClientRequest.from(request).headers(header -> {
					header.set("Authorization", token);
					header.set("x-internal-token", xInternalToken);
				}).build();

				return next.exchange(newRequest);
			}
		}

		return next.exchange(request);
	}
}
