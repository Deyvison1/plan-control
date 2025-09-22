package com.plancontrol.api.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.plancontrol.api.dto.ProductFilterDTO;
import com.plancontrol.api.models.Product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductSpecification {
	public static Specification<Product> filterBy(ProductFilterDTO filter) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (filter != null) {
				addNameFilter(filter, root, cb, predicates);
				addDescriptionFilter(filter, root, cb, predicates);
				addCategoryNameFilter(filter, root, cb, predicates);
				addPriceFilter(filter, root, cb, predicates);
				addSpeedFilter(filter, root, cb, predicates);
				addDateFilter(filter, root, cb, predicates);
			}

			// Evita duplicatas quando usamos JOINs
			query.distinct(true);

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}

	private static void addNameFilter(ProductFilterDTO filter, Root<Product> root, CriteriaBuilder cb,
			List<Predicate> predicates) {
		if (filter.getName() != null && !filter.getName().isEmpty()) {
			predicates.add(cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
		}
	}

	private static void addDescriptionFilter(ProductFilterDTO filter, Root<Product> root, CriteriaBuilder cb,
			List<Predicate> predicates) {
		if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
			predicates
					.add(cb.like(cb.lower(root.get("description")), "%" + filter.getDescription().toLowerCase() + "%"));
		}
	}
	
	private static void addDateFilter(ProductFilterDTO filter, Root<Product> root, CriteriaBuilder cb,
			List<Predicate> predicates) {
		if (filter.getCreated() != null) {
	        LocalDate date = filter.getCreated();

	        LocalDateTime startDate = date.atStartOfDay(); // 00:00:00
	        LocalDateTime endDate = date.atTime(LocalTime.MAX); // 23:59:59.999999999

	        predicates.add(cb.between(root.get("created"), startDate, endDate));
	    }
	}

	private static void addCategoryNameFilter(ProductFilterDTO filter, Root<Product> root, CriteriaBuilder cb,
			List<Predicate> predicates) {

		if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {
			// JOIN entre Produto e Categoria
			Join<Object, Object> categoriesJoin = root.join("category", JoinType.INNER);

			predicates
					.add(cb.like(cb.lower(categoriesJoin.get("name")), "%" + filter.getCategory().toLowerCase() + "%"));
		}
	}

	private static void addPriceFilter(ProductFilterDTO filter, Root<Product> root, CriteriaBuilder cb,
			List<Predicate> predicates) {

		if (filter.getValue() != null) {
			// Força a escala para 2 casas decimais (ex: 85.00)
			BigDecimal priceToCompare = filter.getValue().setScale(2, RoundingMode.HALF_UP);

			predicates.add(cb.equal(root.get("value"), priceToCompare));
		}

		if (filter.getValueWifi() != null) {
			// Força a escala para 2 casas decimais (ex: 85.00)
			BigDecimal priceToCompare = filter.getValueWifi().setScale(2, RoundingMode.HALF_UP);

			predicates.add(cb.equal(root.get("valueWifi"), priceToCompare));
		}

		if (filter.getTaxaAdesao() != null) {
			BigDecimal priceToCompare = filter.getTaxaAdesao().setScale(2, RoundingMode.HALF_UP);

			predicates.add(cb.equal(root.get("taxaAdesao"), priceToCompare));
		}
	}

	private static void addSpeedFilter(ProductFilterDTO filter, Root<Product> root, CriteriaBuilder cb,
			List<Predicate> predicates) {

		if (filter.getSpeedDownload() != null) {
			predicates.add(cb.equal(root.get("speedDownload"), filter.getSpeedDownload()));
		}

		if (filter.getSpeedUpload() != null) {
			predicates.add(cb.equal(root.get("speedUpload"), filter.getSpeedUpload()));
		}
	}
}
