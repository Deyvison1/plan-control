package com.plancontrol.api.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import com.networkshared.api.dtos.base.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ProductDTO extends BaseDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private String name;

	private String description;

	private BigDecimal valueWifi;

	private BigDecimal taxaAdesao;

	private Integer speedDownload;

	private Integer speedUpload;

	private BigDecimal value;

	private CategoryDTO category;
	
	public ProductDTO() {
		
	}

}
