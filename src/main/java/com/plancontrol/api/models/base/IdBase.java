package com.plancontrol.api.models.base;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class IdBase {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;

	@Column(name = "created", nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime created;
	@Column(name = "updated", nullable = true, updatable = true)
	private LocalDateTime updated;

	@Column(name = "user_update_id")
	private UUID userUpdateId;
}