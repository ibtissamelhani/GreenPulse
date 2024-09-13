package entities;

import java.time.LocalDate;

public class Transport extends Consumption {
    private int id;
    private Double distanceTraveled;
    private String vehicleType;

    public Transport() {
    }

    public Transport(LocalDate startDate, LocalDate endDate, Float value, ConsumptionType consumptionType, Double distanceTraveled, String vehicleType) {
        super(startDate, endDate, value, consumptionType);
        this.distanceTraveled = distanceTraveled;
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(Double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public double calculerImpact() {
        double impact = 0.0;
        if ("car".equalsIgnoreCase(vehicleType)) {
            impact = super.getValue()*distanceTraveled * 0.5;
        } else if ("train".equalsIgnoreCase(vehicleType)) {
            impact = super.getValue()*distanceTraveled * 0.1;
        }
        return impact;
    }
}
