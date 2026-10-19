package com.farmbridge.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.farmbridge.model.BuyerRequirement;
import com.farmbridge.model.ProduceLot;
import org.junit.jupiter.api.Test;

class MatchingServiceTest {

    private final MatchingService service = new MatchingService();

    @Test
    void matchesWhenCropQuantityAndGradeAreAllOk() {
        ProduceLot lot = new ProduceLot("Tomato", 500, "A");
        BuyerRequirement req = new BuyerRequirement("Tomato", 400, "A");
        assertTrue(service.matches(lot, req));
    }

    @Test
    void matchesWhenLotGradeIsBetterThanMinimum() {
        ProduceLot lot = new ProduceLot("Tomato", 500, "A");
        BuyerRequirement req = new BuyerRequirement("Tomato", 400, "B");
        assertTrue(service.matches(lot, req));
    }

    @Test
    void doesNotMatchWhenCropIsDifferent() {
        ProduceLot lot = new ProduceLot("Onion", 500, "A");
        BuyerRequirement req = new BuyerRequirement("Tomato", 400, "A");
        assertFalse(service.matches(lot, req));
    }

    @Test
    void doesNotMatchWhenQuantityIsTooLow() {
        ProduceLot lot = new ProduceLot("Tomato", 300, "A");
        BuyerRequirement req = new BuyerRequirement("Tomato", 400, "A");
        assertFalse(service.matches(lot, req));
    }

    @Test
    void doesNotMatchWhenGradeIsBelowMinimum() {
        ProduceLot lot = new ProduceLot("Tomato", 500, "C");
        BuyerRequirement req = new BuyerRequirement("Tomato", 400, "B");
        assertFalse(service.matches(lot, req));
    }

    @Test
    void throwsWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.matches(null, null));
    }
}
