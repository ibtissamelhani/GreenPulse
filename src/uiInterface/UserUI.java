package uiInterface;

import entities.User;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserUI {

    private UserService userService;
    private final Scanner scanner = new Scanner(System.in);


    public void createAccount(){
        System.out.println(" Create New Account ");
        System.out.print("Enter Your Name: ");
        String name = scanner.next();
        System.out.print("Enter Your Age: ");
        int age = scanner.nextInt();
        userService.createUser(name, age);
    }

    public void modifyAccount(){
        System.out.println("Modify Account");
        System.out.print("Enter user id: ");
        long id = scanner.nextLong();
        if (userService.getUserById(id) != null){
            System.out.print("Enter Your Name: ");
            String name = scanner.next();
            System.out.print("Enter Your Age: ");
            int age = scanner.nextInt();
            userService.updateUser(id,name,age);
        }else {
            System.out.println("User not found");
        }
    }

    public void deleteAccount(){
        System.out.print("Enter user id: ");
        long id = scanner.nextLong();
        userService.deleteUser(id);
    }

    public void showAllAccounts(){
        System.out.println("List of All Accounts");
        List<User> users = userService.getAllUsers();
        for (User user : users){
            System.out.print("id: " + user.getId() + "  name: " + user.getName() + " age: " + user.getAge() + "\n");
        }
    }
    public void AddNewConsommation(){
        System.out.println("Add new consommation");

        System.out.print("Enter user Id: ");
        Long userId = scanner.nextLong();
        userService.addConsommationToUser(userId);
    }
}
