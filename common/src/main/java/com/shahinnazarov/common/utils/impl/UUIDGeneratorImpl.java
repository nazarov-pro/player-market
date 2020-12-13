package com.shahinnazarov.common.utils.impl;

import com.shahinnazarov.common.utils.UUIDGenerator;

import java.util.UUID;

/***
 * UUIDGeneratorImpl is a wrapper for generating UUID.
 */
public class UUIDGeneratorImpl implements UUIDGenerator {
    /**
     * generate function generates UUID as a string
     * @return string UUID
     */
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
