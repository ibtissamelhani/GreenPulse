package ui;

import entities.User;
import service.ConsumptionService;
import service.UserService;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsumptionUI {

    private final ConsumptionService consumptionService = new ConsumptionService();
    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);


    public void AddNewConsumption(){

        System.out.println("Add new Consumption \n");

        System.out.print("Enter user Id: ");
        long userId = scanner.nextLong();
        User user = userService.getUserById(userId);
        if (user != null){
            System.out.print("Enter start date (format: YYYY-MM-DD) : ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter end date (format: YYYY-MM-DD) : ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter value : ");
            Float value = scanner.nextFloat();
            scanner.nextLine();

            consumptionService.addConsumptionToUser(userId, startDate, endDate, value);
        }else {
            System.out.println("User not found");
        }
    }

    public void dailyConsumption() {
        System.out.println("Daily Consumption");

        System.out.print("Enter userId: ");
        Long userId = scanner.nextLong();

        System.out.print("Enter the date of the day (format: YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.next());

        float result = consumptionService.getDailyConsumption(userId, date);
        System.out.println("Daily Consumption: " + result);
    }
}
