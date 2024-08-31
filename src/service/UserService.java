package service;

import entities.Consumption;
import entities.User;

import java.time.LocalDate;
import java.util.*;

public class UserService {

    Scanner scanner = new Scanner(System.in);
    private Map<Long, User> users = new HashMap<>();
    private Random rand = new Random();

    public void createUser( String name , int age) {
        User user = new User();
        Long id = 1 + (long) (rand.nextDouble() * (1000));
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        users.put(id, user);
        System.out.println("account created successfully ");
        System.out.print("id: " + user.getId() + "  name: " + user.getName() + " age: " + user.getAge() + "\n");
    }

    public void deleteUser(Long id) {
            User deletedUser = users.remove(id);
            if (deletedUser != null) {
                System.out.println("User deleted successfully: " + deletedUser.getId() + " : " + deletedUser.getName());
            } else {
                System.out.println("User not found: " + id);
            }
        }

    public void updateUser(Long id, String name , int age) {
        User updatedUser = getUserById(id);
        if (updatedUser != null) {
            updatedUser.setName(name);
            updatedUser.setAge(age);
            System.out.println("User updated successfully: " + updatedUser.getName());
        }else{
            System.out.println("User not found: " + id);
        }
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUserById(long id) {
        return users.get(id);
     }

    public void addConsumptionToUser(Long userId) {

        User user = getUserById(userId);
        if (user != null) {

            Long id = 1 + (long) (rand.nextDouble() * (1000));
            System.out.print("Enter start date (format: YYYY-MM-DD) : ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter end date (format: YYYY-MM-DD) : ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter value : ");
            Float value = scanner.nextFloat();

            Consumption consumption = new Consumption(id, startDate, endDate, value);
            //add Consumption to user's Consumption list
            user.addConsumption(consumption);

            System.out.println("Consumption added successfully for user: " + user.getName());
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }

}
