package com.shahinnazarov.common.utils;

import com.shahinnazarov.common.utils.impl.UUIDGeneratorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UUIDGeneratorTest {
    private UUIDGenerator uuidGenerator;

    @BeforeEach
    public void setUp() {
        uuidGenerator = new UUIDGeneratorImpl();
    }

    @Test
    void testGenerate() {
        String uuid = uuidGenerator.generate();
        Assertions.assertNotNull(uuid);
        Assertions.assertTrue(uuid.length() > 10);
    }
}
