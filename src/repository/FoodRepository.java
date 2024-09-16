package repository;

import database.DBConfiguration;
import entities.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FoodRepository {

    private final Connection connection ;

    public FoodRepository() {
        this.connection = DBConfiguration.getInstance().getConnection();
    }

    public void save (String typeOfFood, Double weight, int consumptionId) {
        String query = "INSERT INTO foods (type_of_food,weight,consumption_id) VALUES (?,?,?)";

        try(PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, typeOfFood);
            stm.setDouble(2, weight);
            stm.setInt(3, consumptionId);
            stm.executeUpdate();
            System.out.println("food consumption saved");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
