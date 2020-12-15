package com.shahinnazarov.common.utils;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiMetadata;
import com.shahinnazarov.common.container.models.ApiResponse;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.common.utils.impl.ApiResponseGeneratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ApiResponseGeneratorTest {
    private ApiResponseGenerator responseGenerator;

    @BeforeEach
    public void setUp() {
        responseGenerator = new ApiResponseGeneratorImpl();
    }

    @Test
    void testGenerateMetadata() {
        ApiMetadata apiMetadata = responseGenerator.generateMetadata();
        Assertions.assertTrue(apiMetadata.isSuccess());
        Assertions.assertEquals(Constants.API_OK, apiMetadata.getStatus());
    }

    @Test
    void testGenerateApiResponse() {
        ApiMetadata apiMetadata = responseGenerator.generateMetadata();
        ApiResponse apiResponse = responseGenerator.generateMessage(apiMetadata);
        Assertions.assertEquals(apiMetadata, apiResponse.getMetadata());
    }


    @Test
    void testGenerateApiSingleResponse() {
        String message = "msg";
        ApiSingleResponse<String> singleResponse = responseGenerator.generate(message);
        Assertions.assertTrue(singleResponse.getMetadata().isSuccess());
        Assertions.assertEquals(Constants.API_OK, singleResponse.getMetadata().getStatus());
        Assertions.assertEquals(message, singleResponse.getItem());
    }

    @Test
    void testGenerateApiCollectionResponse() {
        List<Integer> items = Arrays.asList(1, 2);
        ApiCollectionResponse<Integer> collectionResponse = responseGenerator.generateForCollection(items);
        Assertions.assertTrue(collectionResponse.getMetadata().isSuccess());
        Assertions.assertEquals(Constants.API_OK, collectionResponse.getMetadata().getStatus());
        Assertions.assertEquals(items, collectionResponse.getItems());
    }


}
