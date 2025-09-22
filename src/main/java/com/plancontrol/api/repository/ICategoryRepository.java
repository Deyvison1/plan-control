package com.plancontrol.api.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.plancontrol.api.models.Category;

public interface ICategoryRepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {
	Page<Category> findAll(Pageable page);

	Long countBy();
}
