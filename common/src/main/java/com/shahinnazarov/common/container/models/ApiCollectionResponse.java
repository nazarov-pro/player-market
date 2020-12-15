package com.shahinnazarov.common.container.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

/***
 * ApiCollectionResponse returns list of objects
 * <ul>
 *     <li>maxPages - indicates maximum available pages</li>
 *     <li>maxElementsPerPage - indicates maximum elements in a page</li>
 *     <li>currentPageIndex - indicates current page index</li>
 * </ul>
 * @param <T> defines type of item
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApiCollectionResponse<T> extends ApiResponse {
    private Collection<T> items;
    private Integer maxPages;
    private Integer maxElementsPerPage;
    private Integer currentPageIndex;

}
