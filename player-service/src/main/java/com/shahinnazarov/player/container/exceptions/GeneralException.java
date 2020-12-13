package com.shahinnazarov.player.container.exceptions;

import com.shahinnazarov.player.container.enums.GeneralExceptions;

public class GeneralException extends AbstractBaseException {

    public GeneralException(String code, String reason, String description) {
        super(code, reason, description);
    }

    public static GeneralException of(GeneralExceptions generalExceptions, Object... elements) {
        return new GeneralException(
                generalExceptions.getFormattedCode(),
                generalExceptions.getFormattedReason(elements),
                generalExceptions.getFormattedDescription(elements)
        );
    }
}
