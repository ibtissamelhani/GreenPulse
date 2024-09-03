package ui;

import service.ConsumptionService;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsumptionUI {

    private final ConsumptionService consumptionService;

    public ConsumptionUI(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
        this.scanner = new Scanner(System.in);
    }

    private final Scanner scanner;


    public void AddNewConsumption(){

        System.out.println("Add new Consumption \n");

        System.out.print("Enter user Id: ");
        long userId = scanner.nextLong();
        scanner.nextLine();
        consumptionService.addConsumptionToUser(userId);
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

    public void weeklyConsumption() {
        System.out.println("Weekly Consumption");
        System.out.print("Enter userId: ");
        Long userId = scanner.nextLong();
        System.out.print("Enter the date of the week (format: YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.next());
        float result = consumptionService.getWeeklyConsumption(userId, date);
        System.out.println("Weekly Consumption: " + result);
    }

    public void monthlyConsumption() {
        System.out.println("Monthly Consumption");
        System.out.print("Enter userId: ");
        Long userId = scanner.nextLong();
        System.out.print("Enter the date of the month (format: YYYY-MM-DD): ");
        LocalDate monthStart = LocalDate.parse(scanner.next());
        float result = consumptionService.getMonthlyConsumption(userId, monthStart);
        System.out.println("Monthly Consumption: " + result);
    }
}
