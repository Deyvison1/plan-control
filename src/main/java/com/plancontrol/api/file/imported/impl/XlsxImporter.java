package com.plancontrol.api.file.imported.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.plancontrol.api.dto.CategoryDTO;
import com.plancontrol.api.file.imported.contract.FileImported;

@Component
public class XlsxImporter implements FileImported {

	@Override
	public List<CategoryDTO> importFile(InputStream inputStream) throws Exception {
		try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			if (rowIterator.hasNext())
				rowIterator.next();

			return parseRowsToProductDTOList(rowIterator);
		}
	}

	private List<CategoryDTO> parseRowsToProductDTOList(Iterator<Row> rowIterator) {
		List<CategoryDTO> listCategory = new ArrayList<>();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (isRowValid(row)) {
				listCategory.add(parseRowToProductDTO(row));
			}
		}
		return listCategory;
	}

	private CategoryDTO parseRowToProductDTO(Row row) {
		return CategoryDTO.builder().description(row.getCell(0).getStringCellValue())
				.name(row.getCell(1).getStringCellValue()).build();
	}

	private static boolean isRowValid(Row row) {
		return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK;
	}

}
