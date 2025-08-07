package com.plancontrol.api.models;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@RequiredArgsConstructor
public class Product {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Integer speedDownload;

	private Integer speedUpload;

	private BigDecimal taxaAdesao;

	private BigDecimal valueWifi;

	private BigDecimal value;

	@ManyToOne
	private Category category;

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	@Column(name = "updated_at", nullable = true, updatable = true)
	private LocalDateTime updatedAt;
	
}
