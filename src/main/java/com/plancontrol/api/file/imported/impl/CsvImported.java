package com.plancontrol.api.file.imported.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import com.plancontrol.api.dto.CategoryDTO;
import com.plancontrol.api.file.imported.contract.FileImported;

@Component
public class CsvImported implements FileImported {

	@Override
	public List<CategoryDTO> importFile(InputStream inputStream) throws Exception {
		@SuppressWarnings("deprecation")
		CSVFormat format = CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(true).setIgnoreEmptyLines(true)
				.setTrim(true).build();

		Iterable<CSVRecord> records = format.parse(new InputStreamReader(inputStream));
		return parseRecordsToCategoryDTO(records);
	}

	private List<CategoryDTO> parseRecordsToCategoryDTO(Iterable<CSVRecord> records) {
		List<CategoryDTO> listCategoryDTO = new ArrayList<CategoryDTO>();

		for (CSVRecord record : records) {
			listCategoryDTO
					.add(CategoryDTO.builder().description(record.get("description")).name(record.get("name")).build());
		}
		return listCategoryDTO;
	}
}
