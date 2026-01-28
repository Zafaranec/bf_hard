package com.sdc.harder_assignment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Renames and combines columns
 */
public class TableProcessing {
	private static final Map<String, List<String>> mapping = new LinkedHashMap<>();

	static {
		mapping.put("IP", List.of("ip"));
		mapping.put("Full name", List.of("name", "last_name"));
		mapping.put("Date of birth", List.of("date_of_birth"));
		mapping.put("Address", List.of("address"));
		mapping.put("City", List.of("city"));
		mapping.put("Zip code", List.of("zip_code"));
		mapping.put("Phone number", List.of("phone_num"));
	}

	public Map<String, String> processRow(Map<String, String> row) {
		return mapping.entrySet().stream().map(e ->
			Map.entry(
				e.getKey(),
				e.getValue().stream().map(col -> row.getOrDefault(col, "?"))
					.collect(Collectors.joining(" "))
			)
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> {
			throw new UnsupportedOperationException("Duplicate key");
		}, LinkedHashMap::new));
	}

	public List<String> getColumns() {
		return new ArrayList<>(mapping.keySet());
	}
}
