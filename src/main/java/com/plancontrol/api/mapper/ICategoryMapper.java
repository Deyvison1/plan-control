package com.plancontrol.api.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.plancontrol.api.dto.CategoryDTO;
import com.plancontrol.api.models.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper extends IBaseMapper<Category, CategoryDTO> {
	
	@Override
	@Mapping(target = "uuid", source = "uuid")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	CategoryDTO toDto(Category entity);

	@Override
	@Mapping(target = "uuid", source = "uuid")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	Category toEntity(CategoryDTO dto, @MappingTarget Category entity);

	@Override
	List<CategoryDTO> toDto(List<Category> entities);
}
