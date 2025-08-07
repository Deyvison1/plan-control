package com.plancontrol.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plancontrol.api.dto.CategoryDTO;
import com.plancontrol.api.models.Category;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper extends IBaseMapper<Category, CategoryDTO> {

}
