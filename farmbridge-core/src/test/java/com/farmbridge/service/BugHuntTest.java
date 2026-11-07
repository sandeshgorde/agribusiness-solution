package com.farmbridge.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.farmbridge.model.BuyerRequirement;
import com.farmbridge.model.ProduceLot;
import org.junit.jupiter.api.Test;

class BugHuntTest {

    private final MatchingService service = new MatchingService();

    @Test
    void lotRejectsNaNQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProduceLot("Tomato", Double.NaN, "A"));
    }

    @Test
    void lotRejectsInfiniteQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProduceLot("Tomato", Double.POSITIVE_INFINITY, "A"));
    }

    @Test
    void requirementRejectsNaNQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> new BuyerRequirement("Tomato", Double.NaN, "A"));
    }

    @Test
    void requirementRejectsInfiniteQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> new BuyerRequirement("Tomato", Double.POSITIVE_INFINITY, "A"));
    }

    @Test
    void controlCropNameIsCaseInsensitive() {
        ProduceLot lot = new ProduceLot("TOMATO", 500, "A");
        BuyerRequirement req = new BuyerRequirement("tomato", 400, "A");
        assertTrue(service.matches(lot, req));
    }

    @Test
    void controlExactQuantityMatches() {
        ProduceLot lot = new ProduceLot("Tomato", 400, "A");
        BuyerRequirement req = new BuyerRequirement("Tomato", 400, "A");
        assertTrue(service.matches(lot, req));
    }
}
