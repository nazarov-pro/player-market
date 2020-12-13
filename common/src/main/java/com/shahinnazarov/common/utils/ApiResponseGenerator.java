package com.shahinnazarov.common.utils;

import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.container.models.ApiMetadata;
import com.shahinnazarov.common.container.models.ApiSingleResponse;
import com.shahinnazarov.common.container.models.ApiResponse;

import java.util.Collection;

public interface ApiResponseGenerator {
    <T> ApiSingleResponse<T> generate(T item);
    <T> ApiCollectionResponse<T> generateForCollection(Collection<T> items);
    ApiMetadata generateMetadata();
    ApiResponse generateMessage(ApiMetadata metadata);
}
