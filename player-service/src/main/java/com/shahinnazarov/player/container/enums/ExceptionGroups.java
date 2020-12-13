package com.shahinnazarov.player.container.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionGroups {
    GENERAL("CMN")

    ;

    private final String groupName;
}
