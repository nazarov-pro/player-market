package com.shahinnazarov.common.container.enums;

import com.shahinnazarov.common.utils.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * General exceptions mappings (02000 - 03000 reserved for general purposes)
 */
@RequiredArgsConstructor
@Getter
public enum GeneralExceptions {

    DATE_TIME_FORMATTING_PROBLEM(
            2000,
            "Invalid date(time) format %s.",
            "Date(time) formatting problem occurred. (Value: %s)"
    ),
    NOT_FOUND_EXCEPTION(
            2001,
            "Element not found (%s).",
            "Failed to identify %s, please check the value."
    ),
    MODIFICATION_FAILED_EXCEPTION(
            2002,
            "Not possible to modify %s.",
            "Failed to modify %s, please try again later."
    ),
    NOT_ENOUGH_AMOUNT_EXCEPTION(
            2003,
            "%s is not enough to process operation, required amount %s.",
            "Failed to charge %s with %s amount, please check the balance."
    ),
    OPERATION_ALREADY_EXECUTED(
            2004,
            "%s operation is already executed.",
            "Failed to execute %s operation, please check the data is valid."
    ),
    REMOTE_SERVICE_CALL_FAILED(
            2005,
            "%s service calling was failed.",
            "Failed to call %s service, please try again later."
    ),
    INVALID_PROPERTY(
            2006,
            "Invalid property %s.",
            "Validation of %s property was failed, please check the value."
    ),
    ;

    private final int code;
    private final String reason;
    private final String description;
    private final int httpStatusCode;

    GeneralExceptions(int code, String reason, String description) {
        this.code = code;
        this.reason = reason;
        this.description = description;
        this.httpStatusCode = Constants.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR;
    }

    public String getFormattedCode(String applicationPrefix) {
        return String.format(
                "%s:%s:%05d",
                applicationPrefix,
                ExceptionGroups.GENERAL.getGroupName(),
                code
        );
    }

    public String getFormattedReason(Object... elements) {
        return String.format(reason, elements);
    }

    public String getFormattedDescription(Object... elements) {
        return String.format(description, elements);
    }
}
