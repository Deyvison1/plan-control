package com.plancontrol.api.file.exporter.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.plancontrol.api.dto.CategoryDTO;
import com.plancontrol.api.file.FormatDataUtils;
import com.plancontrol.api.file.exporter.contract.FileExporter;

@Component
public class CsvExporter implements FileExporter {

	@Override
	public Resource exportFile(List<CategoryDTO> listCategoryDTO) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

		CSVFormat csvFormat = CSVFormat.Builder.create()
				.setHeader("ID", "Nome", "Descrição", "Criado em", "Atualizado em").setSkipHeaderRecord(false).build();

		try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
			for (CategoryDTO categoryDTO : listCategoryDTO) {
				csvPrinter.printRecord(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getDescription(),
						FormatDataUtils.formatData(categoryDTO.getCreatedAt()),
						FormatDataUtils.formatData(categoryDTO.getUpdatedAt()));
			}
		}
		return new ByteArrayResource(outputStream.toByteArray());
	}

}
