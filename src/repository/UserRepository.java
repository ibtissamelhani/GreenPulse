package repository;

import database.DBConfiguration;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {

    private List<User> users;


    public User save(User user) {
        String query = "insert into users (name,age) values(?,?)";

        try(Connection connection = DBConfiguration.getConnection();
        PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, user.getName());
            stm.setInt(2, user.getAge());
            stm.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public User update(User user) {
        String query = "update users set name=?, age=? where id=?";
        try(Connection connection = DBConfiguration.getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setString(1, user.getName());
            stm.setInt(2, user.getAge());
            stm.setLong(3, user.getId());
            stm.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void delete(Long id) {
        String query = "delete from users where id = ?";
        try(Connection connection = DBConfiguration.getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User findById(long id) {
        String query = "select * from users where id=?";
        User user = new User();
        try(Connection connection = DBConfiguration.getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {

            stm.setLong(1, id);
            ResultSet result = stm.executeQuery();
            if (result.next()) {
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setAge(result.getInt("age"));
            }

        }catch (SQLException e) {
            System.out.println("Error fetching client: " + e.getMessage());
        }
        return user;
    }
    public List<User> findAll() {
        String query = "select * from users";
        try(Connection connection = DBConfiguration.getConnection();
            PreparedStatement stm = connection.prepareStatement(query)) {
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
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
