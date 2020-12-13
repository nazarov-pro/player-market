package com.shahinnazarov.team.container.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractBaseException extends RuntimeException {
    private final String code;
    private final String reason;
    private final String description;

    protected AbstractBaseException(String code, String reason, String description) {
        super(description);
        this.code = code;
        this.reason = reason;
        this.description = description;
    }

    public int getHttpStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
