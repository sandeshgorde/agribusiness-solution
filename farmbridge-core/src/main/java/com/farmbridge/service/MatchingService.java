package com.farmbridge.service;

import com.farmbridge.model.BuyerRequirement;
import com.farmbridge.model.ProduceLot;

public class MatchingService {

    public boolean matches(ProduceLot lot, BuyerRequirement requirement) {
        if (lot == null || requirement == null) {
            throw new IllegalArgumentException("Lot and requirement must not be null");
        }
        boolean sameCrop = lot.getCropName().equalsIgnoreCase(requirement.getCropName());
        boolean enoughQuantity = lot.getQuantityKg() >= requirement.getQuantityKg();
        boolean gradeOk = lot.getGrade().compareTo(requirement.getMinGrade()) <= 0;
        return sameCrop && enoughQuantity && gradeOk;
    }
}
