package com.shahinnazarov.common.utils.impl;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiMetadata;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.common.container.models.ApiResponse;
import com.shahinnazarov.common.utils.ApiResponseGenerator;
import com.shahinnazarov.common.utils.Constants;

import java.util.Collection;

/**
 * ApiResponseGeneratorImpl contains helper functions
 */
public class ApiResponseGeneratorImpl implements ApiResponseGenerator {
    /**
     * Generating {@link ApiSingleResponse} object as successful response
     * @param item object which wrapped in {@link ApiSingleResponse}
     * @param <T> type of item object
     * @return ApiSingleResponse with item
     */
    @Override
    public <T> ApiSingleResponse<T> generate(T item) {
        ApiSingleResponse<T> response = new ApiSingleResponse<>();
        response.setMetadata(generateMetadata());
        response.setItem(item);
        return response;
    }

    /**
     * Generating {@link ApiCollectionResponse} object
     * @param items collection of elements
     * @param <T> type elements in items collection
     * @return ApiCollectionResponse with items
     */
    @Override
    public <T> ApiCollectionResponse<T> generateForCollection(Collection<T> items) {
        ApiCollectionResponse<T> response = new ApiCollectionResponse<>();
        response.setMetadata(generateMetadata());
        response.setItems(items);
        return response;
    }

    /**
     * Generating {@link ApiMetadata} object
     * @return ApiMetadata as successful
     */
    @Override
    public ApiMetadata generateMetadata() {
        ApiMetadata apiMetadata = new ApiMetadata();
        apiMetadata.setSuccess(true);
        apiMetadata.setStatus(Constants.API_OK);
        return apiMetadata;
    }

    /**
     * Generating {@link ApiResponse} object
     * @param metadata metadata object
     * @return ApiResponse as successful
     */
    @Override
    public ApiResponse generateMessage(ApiMetadata metadata) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMetadata(metadata);
        return apiResponse;
    }
}
