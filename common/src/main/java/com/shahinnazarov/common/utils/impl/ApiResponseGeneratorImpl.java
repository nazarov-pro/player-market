package com.shahinnazarov.common.utils.impl;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiMetadata;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.common.container.models.ApiResponse;
import com.shahinnazarov.common.utils.ApiResponseGenerator;

import java.util.Collection;

public class ApiResponseGeneratorImpl implements ApiResponseGenerator {
    @Override
    public <T> ApiSingleResponse<T> generate(T item) {
        ApiSingleResponse<T> response = new ApiSingleResponse<>();
        response.setMetadata(generateMetadata());
        response.setItem(item);
        return response;
    }

    @Override
    public <T> ApiCollectionResponse<T> generateForCollection(Collection<T> items) {
        ApiCollectionResponse<T> response = new ApiCollectionResponse<>();
        response.setMetadata(generateMetadata());
        response.setItems(items);
        return response;
    }

    @Override
    public ApiMetadata generateMetadata() {
        ApiMetadata apiMetadata = new ApiMetadata();
        apiMetadata.setSuccess(true);
        apiMetadata.setStatus("OK");
        return apiMetadata;
    }

    @Override
    public ApiResponse generateMessage(ApiMetadata metadata) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMetadata(metadata);
        return apiResponse;
    }
}
