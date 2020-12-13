package com.shahinnazarov.team.container.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionGroups {
    GENERAL("CMN")

    ;

    private final String groupName;
}
