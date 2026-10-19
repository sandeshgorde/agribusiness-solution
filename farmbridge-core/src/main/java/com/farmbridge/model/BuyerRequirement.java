package com.farmbridge.model;

public class BuyerRequirement {

    private final String cropName;
    private final double quantityKg;
    private final String minGrade;

    public BuyerRequirement(String cropName, double quantityKg, String minGrade) {
        if (cropName == null || cropName.isBlank()) {
            throw new IllegalArgumentException("Crop name must not be empty");
        }
        if (quantityKg <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (minGrade == null || !minGrade.matches("[ABC]")) {
            throw new IllegalArgumentException("Grade must be A, B or C");
        }
        this.cropName = cropName.trim();
        this.quantityKg = quantityKg;
        this.minGrade = minGrade;
    }

    public String getCropName() {
        return cropName;
    }

    public double getQuantityKg() {
        return quantityKg;
    }

    public String getMinGrade() {
        return minGrade;
    }
}
