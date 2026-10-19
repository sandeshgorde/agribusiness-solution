package com.farmbridge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.farmbridge.model.ProduceLot;
import org.junit.jupiter.api.Test;

class ProduceLotValidationTest {

    @Test
    void rejectsEmptyCropName() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new ProduceLot("  ", 100, "A"));
        assertEquals("Crop name must not be empty", e.getMessage());
    }

    @Test
    void rejectsZeroQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new ProduceLot("Tomato", 0, "A"));
    }

    @Test
    void rejectsNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new ProduceLot("Tomato", -50, "A"));
    }

    @Test
    void rejectsInvalidGrade() {
        assertThrows(IllegalArgumentException.class, () -> new ProduceLot("Tomato", 100, "Z"));
    }

    @Test
    void trimsCropName() {
        ProduceLot lot = new ProduceLot("  Tomato  ", 100, "A");
        assertEquals("Tomato", lot.getCropName());
    }
}
