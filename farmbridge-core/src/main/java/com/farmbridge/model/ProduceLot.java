package com.farmbridge.model;

public class ProduceLot {

    private final String cropName;
    private final double quantityKg;
    private final String grade;

    public ProduceLot(String cropName, double quantityKg, String grade) {
        if (cropName == null || cropName.isBlank()) {
            throw new IllegalArgumentException("Crop name must not be empty");
        }
        if (quantityKg <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (grade == null || !grade.matches("[ABC]")) {
            throw new IllegalArgumentException("Grade must be A, B or C");
        }
        this.cropName = cropName.trim();
        this.quantityKg = quantityKg;
        this.grade = grade;
    }

    public String getCropName() {
        return cropName;
    }

    public double getQuantityKg() {
        return quantityKg;
    }

    public String getGrade() {
        return grade;
    }
}
