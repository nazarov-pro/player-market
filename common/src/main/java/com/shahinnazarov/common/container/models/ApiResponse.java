package com.shahinnazarov.common.container.models;

import lombok.Data;


/***
 * ApiResponse - a default structure for response, it will be used for void based responses
 * metadata - metadata about response
 */
@Data
public class ApiResponse {
    private ApiMetadata metadata;
}
