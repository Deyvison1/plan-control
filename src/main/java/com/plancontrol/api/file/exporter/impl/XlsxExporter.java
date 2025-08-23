package com.plancontrol.api.file.exporter.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.plancontrol.api.dto.CategoryDTO;
import com.plancontrol.api.file.FormatDataUtils;
import com.plancontrol.api.file.exporter.contract.FileExporter;

@Component
public class XlsxExporter implements FileExporter {

	@Override
	public Resource exportFile(List<CategoryDTO> listCategoryDTO) throws Exception {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Categorias");

			Row headerRow = sheet.createRow(0);
			String[] headers = { "ID", "Nome", "Descrição", "Criado em", "Atualizado em" };

			for (int i = 0; i < headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
				cell.setCellStyle(createHeaderCellStyle(workbook));
			}

			int rowIndex = 1;
			for (CategoryDTO categoryDTO : listCategoryDTO) {
				Row row = sheet.createRow(rowIndex++);
				row.createCell(0).setCellValue(categoryDTO.getUuid().toString());
				row.createCell(1).setCellValue(categoryDTO.getName());
				row.createCell(2).setCellValue(categoryDTO.getDescription());
				row.createCell(3).setCellValue(FormatDataUtils.formatData(categoryDTO.getCreatedAt()));
				row.createCell(4).setCellValue(FormatDataUtils.formatData(categoryDTO.getUpdatedAt()));
			}

			for (int i = 0; i < headers.length; i++) {
				sheet.autoSizeColumn(i);
			}

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);

			return new ByteArrayResource(outputStream.toByteArray());
		}
	}

	private CellStyle createHeaderCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;
	}

}
