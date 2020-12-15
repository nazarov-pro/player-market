package com.shahinnazarov.common.utils;

import java.io.IOException;
import java.util.List;

/**
 * CSV file reader
 */
public interface CsvReader {
    List<List<String>> getAll() throws IOException;
}
