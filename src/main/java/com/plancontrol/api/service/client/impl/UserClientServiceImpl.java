package com.plancontrol.api.service.client.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.networkshared.api.dtos.UserDTO;
import com.plancontrol.api.service.client.IUserClientService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserClientServiceImpl implements IUserClientService {

	private final WebClient webClient;

	@Override
	public UUID findUUIDByNick(String nick) {
		try {
			return webClient.get().uri("/api/users/get-user-id/{nick}", nick).retrieve().bodyToMono(UUID.class).block();
		} catch (WebClientResponseException.NotFound e) {
			return null;
		}
	}

	@Override
	public UserDTO findByUUID(UUID uuid) {
		try {
			return webClient.get().uri("/api/users/{uuid}", uuid).retrieve().bodyToMono(UserDTO.class).block();
		} catch (WebClientResponseException.NotFound e) {
			return null;
		}
	}

}
