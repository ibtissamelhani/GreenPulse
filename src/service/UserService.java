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
        System.out.print("id: " + user.getId() + "  name: " + user.getName() + " age: " + user.getAge() + " \n\n");
    }

    public void deleteUser(Long id) {
            User deletedUser = users.remove(id);
            if (deletedUser != null) {
                System.out.println("User: " + deletedUser.getId() + " name: " + deletedUser.getName() + " is deleted successfully");
            } else {
                System.out.println("User not found, the ID " + id + " does not exist");
            }
        }

    public void updateUser(Long id, String name , int age) {
        User updatedUser = getUserById(id);
        if (updatedUser != null) {
            updatedUser.setName(name);
            updatedUser.setAge(age);
            System.out.println("User updated successfully, id: " + updatedUser.getId() + "  name: " + updatedUser.getName() + " age: " + updatedUser.getAge() + " \n\n");

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

    public float calcTotalConsumption(Long userId) {
        User user = getUserById(userId);
        if (user != null) {
            Float totalConsumption = 0.0f;
            for(Consumption consumption: user.getConsommations()){
                totalConsumption += consumption.getValue();
            }
            return totalConsumption;
        }else {
            System.out.println("User not found with ID: " + userId);
            return 0.0f;
        }
    }


}
