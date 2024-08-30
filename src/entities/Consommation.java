package entities;

import java.time.LocalDate;
import java.util.Scanner;

public class Consommation {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Float value;
    private User user;

    public Consommation() {
    }

    public Consommation(Long id, LocalDate startDate, LocalDate endDate, Float value) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
