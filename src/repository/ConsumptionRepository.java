package repository;

import database.DBConfiguration;
import entities.ConsumptionType;

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

}
