package com.shahinnazarov.common.container.exceptions;

import com.shahinnazarov.common.utils.Constants;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * AbstractBaseException - abstract base exception
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractBaseException extends RuntimeException {
    private final String code;
    private final String reason;
    private final String description;
    private final int httpStatusCode;

    protected AbstractBaseException(
            String code, String reason, String description, int httpStatusCode
    ) {
        super(description);
        this.code = code;
        this.reason = reason;
        this.description = description;
        this.httpStatusCode = httpStatusCode;
    }

    protected AbstractBaseException(
            String code, String reason, String description
    ) {
        super(description);
        this.code = code;
        this.reason = reason;
        this.description = description;
        this.httpStatusCode = Constants.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR;
    }
}
