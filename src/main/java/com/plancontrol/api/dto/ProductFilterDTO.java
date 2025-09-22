package com.plancontrol.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilterDTO {
	
	private String name;

	private String description;

	private BigDecimal valueWifi;

	private BigDecimal taxaAdesao;

	private Integer speedDownload;

	private Integer speedUpload;

	private BigDecimal value;

	private String category;
	
	private LocalDate created;

}
