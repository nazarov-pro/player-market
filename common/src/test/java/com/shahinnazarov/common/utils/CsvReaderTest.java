package com.shahinnazarov.common.utils;

import com.shahinnazarov.common.utils.impl.CsvReaderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class CsvReaderTest {
    @Test
    void testReading() throws IOException {
        String csvData = "ID;NAME;AGE\n" +
                "1;Shahin;23\n" +
                "2;John;34\n" +
                "3;Kate;25";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                csvData.getBytes()
        );
        CsvReader reader = new CsvReaderImpl(";", inputStream);

        List<List<String>> rows = reader.getAll();
        Assertions.assertEquals(4, rows.size());

        List<List<String>> expectedRows = Arrays.asList(
                Arrays.asList("ID", "NAME", "AGE"),
                Arrays.asList("1", "Shahin", "23"),
                Arrays.asList("2", "John", "34"),
                Arrays.asList("3", "Kate", "25")
        );
        Assertions.assertEquals(expectedRows, rows);
    }
}
