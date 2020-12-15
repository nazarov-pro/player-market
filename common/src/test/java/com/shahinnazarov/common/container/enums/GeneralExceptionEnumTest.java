package com.shahinnazarov.common.container.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GeneralExceptionEnumTest {

    @Test
    void testFormatting() {
        String applicationPrefix = "TEST";
        GeneralExceptions exception = GeneralExceptions.NOT_FOUND_EXCEPTION;
        String formattedCode = exception.getFormattedCode(applicationPrefix);
        String[] urnElements = formattedCode.split(":");
        Assertions.assertEquals(3, urnElements.length);
        Assertions.assertEquals(applicationPrefix, urnElements[0]);
        Assertions.assertEquals(ExceptionGroups.GENERAL.getGroupName(), urnElements[1]);
        Assertions.assertEquals(exception.getCode(), Integer.parseInt(urnElements[2]));
    }
}
