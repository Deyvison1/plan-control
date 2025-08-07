package com.plancontrol.api.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDTO {
	private Long id;

	private String name;

	private String description;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
