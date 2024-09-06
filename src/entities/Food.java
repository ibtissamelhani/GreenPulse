package entities;

import java.time.LocalDate;

public class Food extends Consumption{
    private int id ;
    private String typeOfFood;
    private Double weight;

    public Food(LocalDate startDate, LocalDate endDate, Float value, int id, String typeOfFood, Double weight) {
        super(startDate, endDate, value);
        this.id = id;
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

    public int calcImpact() {
        System.out.println("food impact");
        return 0;
    };
}
