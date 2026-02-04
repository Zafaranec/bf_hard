package com.sdc.harder_assignment.exporters;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExporter implements Exporter {

	private String sheetName;
	private List<String> columns;

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	@Override
	public void export(Path path, List<Map<String, String>> data) throws IOException {
		try (Workbook xlsxWorkbook = new XSSFWorkbook();
			OutputStream fos = Files.newOutputStream(path)
		) {
			Sheet xlsxSheet = xlsxWorkbook.createSheet(sheetName);
			// Export the table header first
			Row headerRow = xlsxSheet.createRow(0);
			for (int colNum = 0; colNum < columns.size(); colNum++) {
				var cell = headerRow.createCell(colNum);
				cell.setCellValue(columns.get(colNum));
			}
			// Export data
			for (int rowNum = 1; rowNum <= data.size(); rowNum++) {
				var dataRow = data.get(rowNum - 1);
				Row xlsxRow = xlsxSheet.createRow(rowNum);
				for (int colNum = 0; colNum < columns.size(); colNum++) {
					Cell cell = xlsxRow.createCell(colNum);
					cell.setCellValue(dataRow.get(columns.get(colNum)));
				}
			}
			xlsxWorkbook.write(fos);
		}
	}
}
