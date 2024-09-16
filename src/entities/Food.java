package entities;

import java.time.LocalDate;

public class Food extends Consumption{
    private int id ;
    private String typeOfFood;
    private Double weight;


    public Food(LocalDate startDate, LocalDate endDate, Float value, ConsumptionType consumptionType, String typeOfFood, Double weight) {
        super(startDate, endDate, value, consumptionType);
        this.typeOfFood = typeOfFood;
        this.weight = weight;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public double calculerImpact() {
        double impact = 0.0;
        if ("meat".equalsIgnoreCase(typeOfFood)) {
            impact = super.getValue()*weight * 5.0;
        } else if ("vegetable".equalsIgnoreCase(typeOfFood)) {
            impact = super.getValue()*weight * 0.5;
        }
        return impact;
    }
}
