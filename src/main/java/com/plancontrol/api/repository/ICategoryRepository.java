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

	@Query(value = """
			SELECT
			    c.uuid as category_uuid,
			    c.name as category_name,
			    c.description as category_description,
			    u.nick as user_nick,
			    u.email as user_email
			FROM plan_control.categories c
			LEFT JOIN auth.users u ON c.user_update_id = u.uuid
			WHERE c.uuid = :uuid
			""", nativeQuery = true)
	ICategoryUserProjection findCategoryWithUserInfo(@Param("uuid") UUID uuid);
}
