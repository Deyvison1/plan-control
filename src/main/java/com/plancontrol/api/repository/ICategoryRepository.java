package com.plancontrol.api.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.networkshared.api.dtos.ICategoryUserProjection;
import com.plancontrol.api.models.Category;

public interface ICategoryRepository extends JpaRepository<Category, UUID> {
	Page<Category> findAll(Pageable page);

	Long countBy();
}
