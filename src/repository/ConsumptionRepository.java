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

    public int save(LocalDate startDate, LocalDate endDate, Float value, Double impact, ConsumptionType consumptionType, int userId) {
        String query = "INSERT INTO consumption (start_date, end_date, value, consumption_impact,user_id, consumption_type) " +
                "VALUES (?, ?, ?, ?,?, ?::consumption_type) RETURNING id";
        int consumptionId = -1;

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setDate(1, Date.valueOf(startDate));
            stm.setDate(2, Date.valueOf(endDate));
            stm.setDouble(3, value);
            stm.setDouble(4, impact);
            stm.setInt(5, userId);
            stm.setObject(6, consumptionType.name());

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


//    public int save (LocalDate startDate, LocalDate endDate, Float value, Double impact, ConsumptionType consumptionType) {
//        String query ="insert into consumption (start_date,end_date,value,consumption_impact,consumption_type) values (?,?,?,?,?)";
//        int consumptionId = -1;
//        try(PreparedStatement stm = connection.prepareStatement(query)) {
//            stm.setDate(1, Date.valueOf(startDate));
//            stm.setDate(2, Date.valueOf(endDate));
//            stm.setFloat(3, value);
//            stm.setDouble(4, impact);
//            stm.setObject(5, consumptionType.name());
//
//            int affectedRows = stm.executeUpdate();
//
//            // Check if a row was inserted and get the generated key (ID)
//            if (affectedRows > 0) {
//                try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
//                    if (generatedKeys.next()) {
//                        consumptionId = generatedKeys.getInt(1);
//                    }
//                }
//            }
//        }catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return consumptionId;
//    }
}
