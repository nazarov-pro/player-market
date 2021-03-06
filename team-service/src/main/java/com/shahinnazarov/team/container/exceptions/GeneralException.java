package com.shahinnazarov.team.container.exceptions;

import com.shahinnazarov.common.container.enums.GeneralExceptions;
import com.shahinnazarov.common.container.exceptions.AbstractBaseException;
import com.shahinnazarov.team.utils.Constants;

/**
 * General exceptions for team service
 */
public class GeneralException extends AbstractBaseException {

    public GeneralException(String code, String reason, String description) {
        super(code, reason, description);
    }

    public GeneralException(String code, String reason, String description, int httpStatusCode) {
        super(code, reason, description, httpStatusCode);
    }

    public static GeneralException of(GeneralExceptions generalExceptions, Object... elements) {
        return new GeneralException(
                generalExceptions.getFormattedCode(Constants.APP_EXCEPTION_PREFIX),
                generalExceptions.getFormattedReason(elements),
                generalExceptions.getFormattedDescription(elements),
                generalExceptions.getHttpStatusCode()
        );
    }
}
