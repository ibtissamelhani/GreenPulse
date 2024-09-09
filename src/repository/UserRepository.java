package repository;

import database.DBConfiguration;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {


    private final Connection connection;

    public UserRepository(Connection connection) {
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


    public void delete(String cin) {
        String query = "delete from users where cin = ?";
        try(PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, cin);
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
            }

        }catch (SQLException e) {
            System.out.println("Error fetching user: " + e.getMessage());
        }
        return Optional.ofNullable(user);
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
}
