package com.plancontrol.api.dto;

import java.math.BigDecimal;

import com.plancontrol.api.models.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {
	private Long id;

	private String name;

	private String description;

	private BigDecimal valueWifi;

	private BigDecimal taxaAdesao;

	private Integer speedDownload;

	private Integer speedUpload;

	private BigDecimal value;

	private Category category;

}
