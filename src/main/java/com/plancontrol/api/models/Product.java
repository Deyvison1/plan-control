package com.plancontrol.api.models;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import com.plancontrol.api.models.base.IdBase;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "products", schema = "plan_control")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends IdBase implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private String name;

	private String description;

	private Integer speedDownload;

	private Integer speedUpload;

	private BigDecimal taxaAdesao;

	private BigDecimal valueWifi;

	private BigDecimal value;

	@ManyToOne
	private Category category;
	
}
