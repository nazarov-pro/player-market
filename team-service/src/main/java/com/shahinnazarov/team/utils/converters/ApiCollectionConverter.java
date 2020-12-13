package com.shahinnazarov.team.utils.converters;

import com.shahinnazarov.common.container.BaseConverter;
import com.shahinnazarov.common.container.models.ApiCollectionResponse;
import com.shahinnazarov.common.utils.ApiResponseGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiCollectionConverter {
    private final ApiResponseGenerator responseGenerator;

    public <F, T> ApiCollectionResponse<T> convert(Page<F> item, BaseConverter<F, T> converter) {
        ApiCollectionResponse<T> response = responseGenerator.generateForCollection(
                item.getContent().stream().map(converter::convert).collect(Collectors.toList())
        );
        response.setCurrentPageNumber(item.getNumber());
        response.setMaxPages(item.getTotalPages());
        response.setMaxElementsPerPage(item.getPageable().getPageSize());
        return response;
    }

}
