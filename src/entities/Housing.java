package entities;

import java.time.LocalDate;

public class Housing extends Consumption {
    private int id;
    private  Double energyConsumption;
    private String energyTypes;

    public Housing() {
    }

    public Housing(LocalDate startDate, LocalDate endDate, Float value, int id, Double energyConsumption, String energyTypes) {
        super(startDate, endDate, value);
        this.id = id;
        this.energyConsumption = energyConsumption;
        this.energyTypes = energyTypes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public String getEnergyTypes() {
        return energyTypes;
    }

    public void setEnergyTypes(String energyTypes) {
        this.energyTypes = energyTypes;
    }

    public int calcImpact() {
        System.out.println("housing impact");
        return 0;
    };
}
