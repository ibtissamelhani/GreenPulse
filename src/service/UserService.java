package service;

import entities.Consumption;
import entities.User;

import java.util.*;

public class UserService {

    private Map<Long, User> users = new HashMap<>();
    private Random rand = new Random();

    public void createUser( String name , int age) {
        User user = new User();
        Long id = 1 + (long) (rand.nextDouble() * (100));
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        users.put(id, user);
        System.out.println("\n account created successfully ");
        System.out.println("\n+--------------------+--------------------+--------------------+");
        System.out.printf("| %-18s | %-18s | %-18s |\n", "ID", "Name", "Age");
        System.out.println("+--------------------+--------------------+--------------------+");
        System.out.printf("| %-18s | %-18s | %-18s |\n", user.getId(), user.getName(), user.getAge());
        System.out.println("+--------------------+--------------------+--------------------+\n");

    }

    public void deleteUser(Long id) {
            User deletedUser = users.remove(id);
            if (deletedUser != null) {
                System.out.println("User: " + deletedUser.getId() + " name: " + deletedUser.getName() + " is deleted successfully \n");
            } else {
                System.out.println("User not found, the ID " + id + " does not exist");
            }
        }

    public void updateUser(Long id, String name , int age) {
        User updatedUser = getUserById(id);
        if (updatedUser != null) {
            updatedUser.setName(name);
            updatedUser.setAge(age);
            System.out.println("User updated successfully , New informations :");
            System.out.println("\n+--------------------+--------------------+--------------------+");
            System.out.printf("| %-18s | %-18s | %-18s |\n", "ID", "Name", "Age");
            System.out.println("+--------------------+--------------------+--------------------+");
            System.out.printf("| %-18s | %-18s | %-18s |\n", updatedUser.getId(), updatedUser.getName(), updatedUser.getAge());
            System.out.println("+--------------------+--------------------+--------------------+\n");

        }else{
            System.out.println("User not found ");
        }
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUserById(long id) {
        return users.get(id);
     }

    public void addConsumption(User user, Consumption consumption) {
        if (user.getconsumptions() == null) {
            user.setconsumptions(new ArrayList<>());
        }
            user.getconsumptions().add(consumption);
            System.out.println("\nConsumption added successfully\n");
        System.out.println("\n+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("| %-18s | %-18s | %-18s | %-18s |\n", "User", "Start Date", "End Date", "Value");
        System.out.println("+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("| %-18s | %-18s | %-18s | %-18s |\n", user.getName(), consumption.getStartDate(), consumption.getEndDate(), consumption.getValue());
        System.out.println("+--------------------+--------------------+--------------------+--------------------+\n");

    }


}
