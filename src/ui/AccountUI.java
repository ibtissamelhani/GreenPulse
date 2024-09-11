package ui;

import entities.Consumption;
import entities.User;
import service.UserService;
import utils.DateChecker;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AccountUI {

    private final UserService userService;
    private  final Scanner scanner;
    final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";


    public AccountUI(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void createAccount(){
        System.out.println(" Create New Account ");

        System.out.print("Enter Your cin: ");
        String cin = scanner.next();

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
        User savedUser = userService.createUser(cin,name,age);
        if (savedUser !=null) {
            System.out.println("\n account created successfully ");
            System.out.println("\n+--------------------+--------------------+--------------------+");
            System.out.printf("| %-18s | %-18s | %-18s |\n","CIN", "Name", "Age");
            System.out.println("+--------------------+--------------------+--------------------+");
            System.out.printf("| %-18s | %-18s | %-18s |\n", savedUser.getCin(), savedUser.getName(), savedUser.getAge());
            System.out.println("+--------------------+--------------------+--------------------+\n");
        };
    }

    public void modifyAccount(){
        System.out.println("\n Modify Account");
        System.out.print("Enter user CIN: ");
        String cin = scanner.next();

        userService.getUserByCin(cin).ifPresent(user -> {
            System.out.print("\n id: " + user.getId() + "  name: " + user.getName() + " age: " + user.getAge() + " \n");
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
            userService.updateUser(cin,name,age);
        });

    }

    public void deleteAccount(){
        System.out.print("Enter user CIN to delete: ");
        String cin = scanner.next();
        userService.deleteUser(cin);
        System.out.println("\n account deleted successfully ");
    }

    public void showAllAccounts(){
        System.out.println(RED+"******************************  List of All Accounts ***********************************"+RESET);
        List<User> users = userService.getAllUsers();
        if(!users.isEmpty()){
            System.out.println("\n+--------------------+--------------------+--------------------+-------------------+");
            System.out.printf("| %-18s | %-18s | %-18s |%-18s |\n","ID", "CIN", "Name", "Age");
            System.out.println("+--------------------+--------------------+--------------------+-------------------+");
            for (User user : users){
                System.out.printf("| %-18s | %-18s | %-18s |%-18s |\n", user.getId(), user.getCin(), user.getName(), user.getAge());
                System.out.println("+--------------------+--------------------+--------------------+-------------------+");
            }
        }else {
            System.out.println("\n Users not found \n");
        }
        System.out.println(RED+"****************************************************************************************"+RESET);

    }

    public void showAllUsersWithConsumption(){
        System.out.println(RED+"******************************  List of All Accounts ***********************************"+RESET);
        List<User> users = userService.getUsersWithConsumptions();
        if(!users.isEmpty()){
            for (User user : users){
                System.out.printf(GREEN+"\n| %-18s | %-18s | %-18s | %-18s |\n", user.getId(), user.getCin(), user.getName(), user.getAge() +RESET);
                System.out.println("+--------------------+--------------------+--------------------+-------------------+-------------------+-------------------+");
                System.out.printf("| %-18s | %-18s | %-18s | %-18s | %-18s | %-18s |\n","ID", "start_date", "end_date", "value","impact","type");
                System.out.println("+--------------------+--------------------+--------------------+-------------------+-------------------+-------------------+");

                for(Consumption consumption: user.getConsumptions()){
                    System.out.printf("| %-18s | %-18s | %-18s | %-18s | %-18s | %-18s |\n",consumption.getId(), consumption.getStartDate(), consumption.getEndDate(), consumption.getValue(), consumption.getConsumptionImpact(), consumption.getConsumptionType());
                    System.out.println("+--------------------+--------------------+--------------------+-------------------+-------------------+-------------------+");
                }
            }
        }else {
            System.out.println("\n Users not found \n");
        }
        System.out.println(RED+"****************************************************************************************"+RESET);

    }

    public void ShowAllUsersWithSpeConsumption(){
        System.out.println(RED+"******************************  Users with a total consumption greater than 3000 KgCO2eq  ***********************************"+RESET);
        List<User> users = userService.getUsers();
        if(!users.isEmpty()){
            System.out.println("\n+--------------------+--------------------+--------------------+-------------------+");
            System.out.printf("| %-18s | %-18s | %-18s |%-18s |\n","ID", "CIN", "Name", "Age");
            System.out.println("+--------------------+--------------------+--------------------+-------------------+");
            for (User user : users){
                System.out.printf("| %-18s | %-18s | %-18s |%-18s |\n", user.getId(), user.getCin(), user.getName(), user.getAge());
                System.out.println("+--------------------+--------------------+--------------------+-------------------+");
            }
        }else {
            System.out.println("\n Users not found \n");
        }
        System.out.println(RED+"****************************************************************************************"+RESET);

    }

    public void showInactiveUsers(){
        LocalDate startDate;
        LocalDate endDate;
        while (true) {
            startDate = DateChecker.isDateValid(scanner,"Enter start date (format: YYYY-MM-DD) : ");

            endDate = DateChecker.isDateValid(scanner,"Enter end date (format: YYYY-MM-DD) : ");

            if (!endDate.isBefore(startDate)) {
                break;
            } else {
                System.out.println(" \n End date cannot be before the start date. Please enter valid dates.\n");
            }
        }
        System.out.println(RED+"************************************************  Inactive accounts Accounts ***********************************"+RESET);
        List<User> users = userService.getInactiveUsers(startDate, endDate);
        if(!users.isEmpty()){
            System.out.println("\n+--------------------+--------------------+--------------------+-------------------+");
            System.out.printf("| %-18s | %-18s | %-18s |%-18s |\n","ID", "CIN", "Name", "Age");
            System.out.println("+--------------------+--------------------+--------------------+-------------------+");
            for (User user : users){
                System.out.printf("| %-18s | %-18s | %-18s |%-18s |\n", user.getId(), user.getCin(), user.getName(), user.getAge());
                System.out.println("+--------------------+--------------------+--------------------+-------------------+");
            }
        }else {
            System.out.println("\n Users not found \n");
        }
        System.out.println(RED+"***********************************************************************************************************************"+RESET);

    }

}
