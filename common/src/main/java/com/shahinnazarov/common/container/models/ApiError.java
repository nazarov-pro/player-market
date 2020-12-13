package com.shahinnazarov.common.container.models;

import lombok.Data;

/***
 * ApiError
 * <u>
 *     <li>
 *         code - describes error's code with URN format(PREFIX:GROUP:CODE). 
 *         <p>Example: PLY:CMN:004 - Player service's common error with 001 code</p>
 *     </li>
 *     <li>reason - an user friendly short version of error</li>
 *     <li>description - a detailed explanation of error</li>
 * </u>
 */
@Data
public class ApiError {
    private String code;
    private String reason;
    private String description;
    
}
