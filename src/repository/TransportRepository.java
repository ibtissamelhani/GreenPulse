package repository;

import database.DBConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransportRepository {

    private final Connection connection;

    public TransportRepository(Connection connection) {
        this.connection = DBConfiguration.getInstance().getConnection();
    }

    public void save(Double distanceTraveled,String vehicleType,int consumptionId) {
        String query = "INSERT INTO transport (distance_traveled,vehicle_type,consumption_id) VALUES (?,?,?)";

        try(PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setDouble(1, distanceTraveled);
            stm.setString(2, vehicleType);
            stm.setInt(3, consumptionId);
            stm.executeUpdate();
            System.out.println("transport consumption saved");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
