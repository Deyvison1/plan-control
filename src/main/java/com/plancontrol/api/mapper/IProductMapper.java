package com.plancontrol.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plancontrol.api.dto.ProductDTO;
import com.plancontrol.api.models.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper extends IBaseMapper<Product, ProductDTO> {

}
