package ui;

import entities.Consumption;
import entities.User;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class AccountUI {

    private final UserService userService;
    private  final Scanner scanner;
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";

    public AccountUI(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void createAccount(){
        System.out.println(" Create New Account ");
        System.out.print("Enter Your Name: ");
        String name = scanner.next();
        int age = -1;
        while(age<=0 || age>100){
            System.out.print("Enter Your Age: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                if (age <= 0 || age > 100) {
                    System.out.println("Please enter a valid age between 1 and 100.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
        userService.createUser(name, age);
    }

    public void modifyAccount(){
        System.out.println("\n Modify Account");
        System.out.print("Enter user id: ");
        long id = scanner.nextLong();
        User currentUser = userService.getUserById(id);
        if (currentUser != null){
            System.out.print("\nid: " + currentUser.getId() + "  name: " + currentUser.getName() + " age: " + currentUser.getAge() + " \n");
            System.out.print("Enter new Name : ");
            String name = scanner.next();
            int age = -1;
            while(age<=0 || age>100){
                System.out.print("Enter new Age: ");
                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    if (age <= 0 || age > 100) {
                        System.out.println("Please enter a valid age between 1 and 100.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                }
            }
            userService.updateUser(id,name,age);
        }else {
            System.out.println("User not found");
        }
    }

    public void deleteAccount(){
        System.out.print("Enter user ID: ");
        long id = scanner.nextLong();
        userService.deleteUser(id);
    }

    public void showAllAccounts(){
        System.out.println(RED+"******************************  List of All Accounts ***********************************"+RESET);
        List<User> users = userService.getAllUsers();
        if(!users.isEmpty()){
            for (User user : users){
                System.out.print("id: " + user.getId() + "  name: " + user.getName() + " age: " + user.getAge() + "\n");
                if (user.getconsumptions() != null){
                    for (Consumption consumption: user.getconsumptions()){
                        System.out.println("consumption between " + consumption.getStartDate() +" and "+consumption.getEndDate() + " is " + consumption.getValue());
                    }
                }
            }
        }else {
            System.out.println("\n Users not found \n");
        }
        System.out.println(RED+"****************************************************************************************"+RESET);

    }

}
