package com.shahinnazarov.player.container.enums;

import com.shahinnazarov.player.utils.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 02000-03000
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
    );

    private final int code;
    private final String reason;
    private final String description;

    public String getFormattedCode() {
        return String.format(
                "%s:%s:%05d",
                Constants.APP_EXCEPTION_PREFIX,
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
