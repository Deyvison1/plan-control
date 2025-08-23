package com.plancontrol.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.networkshared.api.dtos.UserDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDTO {
	private UUID uuid;

	private String name;

	private String description;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	private UserDTO userCreated;
	
	private UserDTO userUpdated;
	
	private UUID userUpdateId;
}
