package com.sdc.harder_assignment;

import com.sdc.harder_assignment.exporters.ExcelExporter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		System.out.println(" -- CSV to Excel -- ");
		CsvLoader csv;
		try {
			csv = new CsvLoader("test_data.csv");
			System.out.println("Loaded columns:");
			System.out.println(csv.getColumns());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		var processing = new TableProcessing();
		List<Map<String, String>> rows = csv.getData().stream().map(processing::processRow).collect(Collectors.toList());
		var exporter = new ExcelExporter();
		exporter.setColumns(processing.getColumns());
		exporter.setSheetName("export");
		try {
			exporter.export(Path.of("export.xlsx"), rows);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

