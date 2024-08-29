package service;

import entities.Consommation;
import entities.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class UserService {

    Scanner scanner = new Scanner(System.in);
    HashMap<Long, User> users = new HashMap<>();

    public void createUser() {
        System.out.println("Create a new user");

        System.out.printf("Enter your ID : ");
        Long id = scanner.nextLong();
        System.out.printf("Enter your name : ");
        String name = scanner.nextLine();
        System.out.printf("Enter your age : ");
//        while (!) {
//
//            age = scanner.nextInt();
//        }
        int age = scanner.nextInt();
        User user = new User(id, name, age);
        users.put(id, user);
        System.out.println("user created successfully ");
    }

    public void deleteUser() {

    }

    public void updateUser() {

    }

    public void addNewConsommation() {
        System.out.println("Add new consommation");

        System.out.printf("Enter start date (format: YYYY-MM-DD) : ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.printf("Enter end date (format: YYYY-MM-DD) : ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.printf("Enter value : ");
        Float value = scanner.nextFloat();

        Consommation
    }
}
