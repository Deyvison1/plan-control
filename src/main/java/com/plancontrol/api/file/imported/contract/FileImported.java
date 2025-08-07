package com.plancontrol.api.file.imported.contract;

import java.io.InputStream;
import java.util.List;

import com.plancontrol.api.dto.CategoryDTO;

public interface FileImported {

	List<CategoryDTO> importFile(InputStream inputStream) throws Exception;
}
