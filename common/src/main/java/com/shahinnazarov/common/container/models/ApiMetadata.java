package com.shahinnazarov.common.container.models;

import lombok.Data;

import java.util.Collection;

/***
 * ApiMetadata describes metadata for a specific request
 * <u>
 *     <li>success - defines operation is successful or not (usable for client side)</li>
 *     <li>status - defines status of returned message</li>
 *     <li>errors - list of errors as {@link ApiError} object </li>
 * </u>
 */
@Data
public class ApiMetadata {
    private boolean success;
    private String status;
    private Collection<ApiError> errors;

}
