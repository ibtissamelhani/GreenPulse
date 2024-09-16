package entities;

import java.time.LocalDate;

public class Housing extends Consumption {
    private int id;
    private  Double energyConsumption;
    private String energyType;

    public Housing() {
    }

    public Housing(LocalDate startDate, LocalDate endDate, Float value, ConsumptionType consumptionType, Double energyConsumption, String energyType) {
        super(startDate, endDate, value, consumptionType);
        this.energyConsumption = energyConsumption;
        this.energyType = energyType;
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

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyTypes) {
        this.energyType = energyType;
    }

    @Override
    public double calculerImpact() {
        double impact = 0.0;
        if ("electricity".equalsIgnoreCase(energyType)) {
            impact = super.getValue() * energyConsumption * 1.5;
        } else if ("gas".equalsIgnoreCase(energyType)) {
            impact = super.getValue() * energyConsumption * 2.0;
        }
        return impact;
    }
}
