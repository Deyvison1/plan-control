package com.plancontrol.api.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.networkshared.api.dtos.UserDTO;
import com.networkshared.api.dtos.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class CategoryDTO extends BaseDTO implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	private String name;
	
	private String description;

	private LocalDateTime created;

	private LocalDateTime updated;
	
	private UserDTO userUpdated;
	
	private UUID userUpdateId;
	
	public CategoryDTO() {

	}

}
