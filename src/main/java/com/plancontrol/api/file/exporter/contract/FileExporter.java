package com.plancontrol.api.file.exporter.contract;

import java.util.List;

import org.springframework.core.io.Resource;

import com.plancontrol.api.dto.CategoryDTO;


public interface FileExporter {
	Resource exportFile(List<CategoryDTO> listCategoryDTO) throws Exception;
}
