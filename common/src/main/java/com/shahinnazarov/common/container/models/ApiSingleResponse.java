package com.shahinnazarov.common.container.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/***
 * ApiSingleResponse for a single item response
 * @param <T> - type of item
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiSingleResponse<T> extends ApiResponse {
    private T item;

}
