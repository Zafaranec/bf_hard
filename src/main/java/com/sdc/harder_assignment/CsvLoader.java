package com.sdc.harder_assignment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

public class CsvLoader {
	private final String path;
	private final List<String> columns;
	private final List<Map<String, String>> data;

	public CsvLoader(String path) throws IOException {
		this.path = path;
		try (
			InputStream file = new FileInputStream(Path.of(path).toFile());
			CSVParser parser = CSVParser.parse(new BufferedReader(new InputStreamReader(file)), CSVFormat.DEFAULT.builder()
				.setHeader()
				.setSkipHeaderRecord(true)
				.setDelimiter(";")
				.setQuoteMode(QuoteMode.MINIMAL)
				.setNullString("N/A")
				.setIgnoreSurroundingSpaces(true)
				.get())
		) {
			columns = parser.getHeaderNames();
			data = parser.stream().map(CSVRecord::toMap).collect(Collectors.toList());
		}
	}

	public String getPath() {
		return path;
	}

	public List<String> getColumns() {
		return columns;
	}

	public List<Map<String, String>> getData() {
		return data;
	}
}
