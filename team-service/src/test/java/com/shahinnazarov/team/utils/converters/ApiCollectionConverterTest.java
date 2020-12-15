package com.shahinnazarov.team.utils.converters;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.utils.impl.ApiResponseGeneratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;

class ApiCollectionConverterTest {
    private ApiCollectionConverter collectionConverter;

    @BeforeEach
    public void setUp() {
        collectionConverter = new ApiCollectionConverter(
                new ApiResponseGeneratorImpl()
        );
    }

    @Test
    void testConvert() {
        Page<String> page = new PageImpl<>(
                Arrays.asList("one", "two", "three"),
                PageRequest.of(1,2),
                10
        );

        ApiCollectionResponse<String> response = collectionConverter.convert(page, (s) -> s);
        Assertions.assertEquals(page.getContent(), response.getItems());
        Assertions.assertEquals(page.getTotalPages(), response.getMaxPages());
        Assertions.assertEquals(page.getPageable().getPageNumber(), response.getCurrentPageIndex());
        Assertions.assertEquals(page.getPageable().getPageSize(), response.getMaxElementsPerPage());
    }
}
