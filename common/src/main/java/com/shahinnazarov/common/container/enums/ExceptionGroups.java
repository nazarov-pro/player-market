package com.shahinnazarov.common.container.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ExceptionGroups - Global exception groups for separating exceptions
 */
@RequiredArgsConstructor
@Getter
public enum ExceptionGroups {
    GENERAL("CMN")

    ;

    private final String groupName;
}
