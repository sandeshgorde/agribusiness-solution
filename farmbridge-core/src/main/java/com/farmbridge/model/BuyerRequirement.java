package com.farmbridge.model;

public class BuyerRequirement {

    private final String cropName;
    private final double quantityKg;
    private final String minGrade;

    public BuyerRequirement(String cropName, double quantityKg, String minGrade) {
        if (cropName == null || cropName.isBlank()) {
            throw new IllegalArgumentException("Crop name must not be empty");
        }
        if (!Double.isFinite(quantityKg) || quantityKg <= 0) {
            throw new IllegalArgumentException("Quantity must be a finite number greater than zero");
        }
        if (minGrade == null || minGrade.length() != 1 || "ABC".indexOf(minGrade.charAt(0)) < 0) {
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
