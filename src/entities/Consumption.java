package entities;

import java.time.LocalDate;

public abstract class Consumption {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Float value;
    private Double consumptionImpact;
    private ConsumptionType consumptionType;

    public Consumption() {
    }

    public Consumption(LocalDate startDate, LocalDate endDate, Float value, ConsumptionType consumptionType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
        this.consumptionType = consumptionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Double getConsumptionImpact() {
        return consumptionImpact;
    }

    public void setConsumptionImpact(Double consumptionImpact) {
        this.consumptionImpact = consumptionImpact;
    }

    public ConsumptionType getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(ConsumptionType consumptionType) {
        this.consumptionType = consumptionType;
    }

    public abstract double calculerImpact();

    @Override
    public String toString() {
        return "Consumption{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", value=" + value +
                '}';
    }
}
