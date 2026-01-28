package com.sdc.harder_assignment.exporters;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface Exporter {
	void export(Path path, List<Map<String, String>> data) throws IOException;
}
