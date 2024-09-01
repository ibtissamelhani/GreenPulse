package entities;

import java.time.LocalDate;

public class Consumption {

    private LocalDate startDate;
    private LocalDate endDate;
    private Float value;

    public Consumption() {
    }

    public Consumption(LocalDate startDate, LocalDate endDate, Float value) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
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

}
