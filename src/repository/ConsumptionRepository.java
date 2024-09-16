package repository;

import database.DBConfiguration;
import entities.*;

import java.sql.*;
import java.time.LocalDate;

public class ConsumptionRepository {


    private final Connection connection ;

    public ConsumptionRepository() {
        this.connection = DBConfiguration.getInstance().getConnection();
    }

    public int save(LocalDate startDate, LocalDate endDate, Float value, ConsumptionType consumptionType, int userId) {
        String query = "INSERT INTO consumptions (start_date, end_date, value,user_id, consumption_type) " +
                "VALUES (?, ?, ?, ?,?, ?::consumption_type) RETURNING id";
        int consumptionId = -1;

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setDate(1, Date.valueOf(startDate));
            stm.setDate(2, Date.valueOf(endDate));
            stm.setDouble(3, value);
            stm.setInt(4, userId);
            stm.setObject(5, consumptionType.name());

            // Execute the query and retrieve the generated ID
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    consumptionId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return consumptionId;
    }

    public void saveConsumption(Consumption consumption, int userId) {
        String query1 = "INSERT INTO consumptions (start_date, end_date, value, user_id, consumption_type) " +
                "VALUES (?, ?, ?, ?, ?::consumption_type) RETURNING id";

        String query2 = "";

        try  {
            connection.setAutoCommit(false); // Start transaction

            int consumptionId;
            try (PreparedStatement stm = connection.prepareStatement(query1)) {
                stm.setDate(1, Date.valueOf(consumption.getStartDate()));
                stm.setDate(2, Date.valueOf(consumption.getEndDate()));
                stm.setDouble(3, consumption.getValue());
                stm.setInt(4, userId);
                stm.setObject(5, consumption.getConsumptionType().name());
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        consumptionId = rs.getInt(1);
                    } else {
                        throw new SQLException("Failed to retrieve consumption ID.");
                    }
                }
            }


            if (consumption instanceof Transport) {
                query2 = "INSERT INTO transports (distance_traveled, vehicle_type, consumption_id) VALUES (?, ?, ?)";
                Transport transport = (Transport) consumption;
                try (PreparedStatement stm = connection.prepareStatement(query2)) {
                    stm.setDouble(1, transport.getDistanceTraveled());
                    stm.setString(2, transport.getVehicleType());
                    stm.setInt(3, consumptionId);
                    stm.executeUpdate();
                }
            } else if (consumption instanceof Housing) {
                query2 = "INSERT INTO housings (energy_consumption, energy_type, consumption_id) VALUES (?, ?, ?)";
                Housing housing = (Housing) consumption;
                try (PreparedStatement stm = connection.prepareStatement(query2)) {
                    stm.setDouble(1, housing.getEnergyConsumption());
                    stm.setString(2, housing.getEnergyType());
                    stm.setInt(3, consumptionId);
                    stm.executeUpdate();
                }
            } else if (consumption instanceof Food) {
                query2 = "INSERT INTO foods (type_of_food, weight, consumption_id) VALUES (?, ?, ?)";
                Food food = (Food) consumption;
                try (PreparedStatement stm = connection.prepareStatement(query2)) {
                    stm.setString(1, food.getTypeOfFood());
                    stm.setDouble(2, food.getWeight());
                    stm.setInt(3, consumptionId);
                    stm.executeUpdate();
                }
            } else {
                throw new IllegalArgumentException("Unknown consumption type.");
            }

            connection.commit(); // Commit transaction
            System.out.println("Consumption saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving consumption: " + e.getMessage());
            try {
                if (connection != null && !connection.getAutoCommit()) {
                    connection.rollback(); // Rollback transaction on error
                }
            } catch (SQLException rollbackEx) {
                System.out.println("Error rolling back transaction: " + rollbackEx.getMessage());
            }
        }
    }

}
