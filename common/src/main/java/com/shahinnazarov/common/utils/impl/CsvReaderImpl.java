package com.shahinnazarov.common.utils.impl;

import com.shahinnazarov.common.utils.CsvReader;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CsvReaderImpl default implementation of {@link CsvReader}
 */
@RequiredArgsConstructor
public class CsvReaderImpl implements CsvReader {
    private final String splitter;
    private final InputStream inputStream;

    /**
     * Get All List as List of string elements
     * @return List of strings
     * @throws IOException if reading fails
     */
    @Override
    public List<List<String>> getAll() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<List<String>> list = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            list.add(Arrays.asList(line.split(splitter)));
        }
        return list;
    }
}
