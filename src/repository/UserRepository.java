package repository;

import database.DBConfiguration;
import entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class UserRepository {


    private final Connection connection;

    public UserRepository() {
        this.connection = DBConfiguration.getInstance().getConnection();
    }


    public User save(User user) {
        String query = "insert into users (cin,name,age) values(?,?,?)";

        try(PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, user.getCin());
            stm.setString(2, user.getName());
            stm.setInt(3, user.getAge());
            stm.executeUpdate();
            System.out.println("user saved");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public User update(User user) {
        String query = "update users set cin=?, name=?, age=? where cin=?";
        try(PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, user.getCin());
            stm.setString(2, user.getName());
            stm.setInt(3, user.getAge());
            stm.setString(4, user.getCin());
            stm.executeUpdate();
            System.out.println("user updated");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void delete(Optional<User> user) {

        String query = "delete from users where cin = ?";
        try(PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, user.get().getCin());
            stm.executeUpdate();
            System.out.println("user deleted");
        }catch (SQLException e) {
            System.out.println("Error while deleting user: "+ e.getMessage());
        }
    }

    public Optional<User> findByCin(String cin) {
        String query = "select * from users where cin=?";
        User user = new User();
        try(PreparedStatement stm = connection.prepareStatement(query)) {

            stm.setString(1, cin);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                user.setId(result.getInt("id"));
                user.setCin(result.getString("cin"));
                user.setName(result.getString("name"));
                user.setAge(result.getInt("age"));
                return Optional.of(user);
            }

        }catch (SQLException e) {
            System.out.println("Error fetching user: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "select * from users";
        try(PreparedStatement stm = connection.prepareStatement(query)) {
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setCin(result.getString("cin"));
                user.setName(result.getString("name"));
                user.setAge(result.getInt("age"));
                users.add(user);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public List<User> getAllUsersWithConsumptions() {
        Map<Integer, User> userMap = new HashMap<>();
        String query =  "SELECT u.id as user_id, u.cin, u.name, u.age, " +
                "c.id as consumption_id, c.start_date, c.end_date, " +
                "c.value, c.consumption_type, " +
                "t.distance_traveled, t.vehicle_type, " +
                "h.energy_consumption, h.energy_type, " +
                "f.type_of_food, f.weight " +
                "FROM users u " +
                "LEFT JOIN consumptions c ON u.id = c.user_id " +
                "LEFT JOIN transports t ON c.id = t.consumption_id " +
                "LEFT JOIN housings h ON c.id = h.consumption_id " +
                "LEFT JOIN foods f ON c.id = f.consumption_id;";

        try (PreparedStatement stm = connection.prepareStatement(query)) {
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                int userId = result.getInt("user_id");

                User user = userMap.get(userId);
                if (user == null) {
                    user = new User();
                    user.setId(userId);
                    user.setCin(result.getString("cin"));
                    user.setName(result.getString("name"));
                    user.setAge(result.getInt("age"));
                    user.setConsumptions(new ArrayList<>());
                    userMap.put(userId, user);
                }

                int consumptionId = result.getInt("consumption_id");
                if (consumptionId != 0) {

                    Consumption consumption = null;
                    Float value = result.getFloat("value");
                    LocalDate startDate = result.getDate("start_date").toLocalDate();
                    LocalDate endDate = result.getDate("end_date").toLocalDate();
                    ConsumptionType consumptionType = ConsumptionType.valueOf(result.getString("consumption_type"));

                    switch (consumptionType) {
                        case TRANSPORT:
                            Double distanceTraveled = result.getDouble("distance_traveled");
                            String vehicleType = result.getString("vehicle_type");
                            consumption = new Transport(startDate,endDate,value,consumptionType,distanceTraveled,vehicleType);
                            break;
                        case HOUSING:
                            Double energyConsumption = result.getDouble("energy_consumption");
                            String energyType = result.getString("energy_type");
                            consumption = new Housing(startDate,endDate,value,consumptionType,energyConsumption,energyType);
                            break;
                        case FOOD:
                            String typeOfFood = result.getString("type_of_food");
                            Double weight = result.getDouble("weight");
                            consumption= new Food(startDate,endDate,value,consumptionType,typeOfFood,weight);
                            break;
                    }

                    if (consumption != null) {
                        user.getConsumptions().add(consumption);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<>(userMap.values());
    }

}
