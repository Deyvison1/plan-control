package com.plancontrol.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import com.plancontrol.api.dto.CategoryFilterDTO;
import com.plancontrol.api.models.Category;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CategorySpecification {

	public static Specification<Category> filterBy(CategoryFilterDTO filter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (filter != null) {
				addNameFilter(filter, root, cb, predicates);
				addDescriptionFilter(filter, root, cb, predicates);
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

	private static void addNameFilter(CategoryFilterDTO filter, Root<Category> root,
			CriteriaBuilder cb, List<Predicate> predicates) {
		if (filter.getName() != null && !filter.getName().isEmpty()) {
			predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
		}
	}

	private static void addDescriptionFilter(CategoryFilterDTO filter, Root<Category> root,
			CriteriaBuilder cb, List<Predicate> predicates) {
		if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
			predicates
					.add(cb.like(cb.lower(root.get("description")), "%" + filter.getDescription().toLowerCase() + "%"));
		}
	}
}
