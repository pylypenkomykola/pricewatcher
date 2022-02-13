package com.mykolapylypenko.pricewatcher.domain.format;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringLogFormatText {

    @Test
    void testStringFormatter() {
        String text = "Some text price: {}, {}";

        String result = StringLogFormat.format(text, 1111, "2222");

        assertEquals("Some text price: 1111, 2222", result);
    }
}
