package repository;

import database.DBConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HousingRepository {

    private final Connection connection;

    public HousingRepository(Connection connection) {
        this.connection = DBConfiguration.getInstance().getConnection();
    }

    public void save (Double energyConsumption, String energyType, int consumptionId) {
        String query = "INSERT INTO housing (energy_consumption,energy_type,consumption_id) VALUES (?,?,?)";

        try(PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setDouble(1, energyConsumption);
            stm.setString(2, energyType);
            stm.setInt(3, consumptionId);
            stm.executeUpdate();
            System.out.println("housing consumption saved");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
