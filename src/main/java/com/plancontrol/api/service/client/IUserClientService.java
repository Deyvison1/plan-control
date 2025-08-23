package com.plancontrol.api.service.client;

import java.util.UUID;

import com.networkshared.api.dtos.UserDTO;

public interface IUserClientService {
	UUID findUUIDByNick(String nick);
	UserDTO findByUUID(UUID uuid);
}
