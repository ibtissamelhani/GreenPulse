package ui;

import service.ConsumptionService;
import utils.DateChecker;

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

        System.out.print("Enter user cin: ");
        String cin = scanner.next();
        scanner.nextLine();
        consumptionService.addConsumptionToUser(cin);
    }

    public void ShowTotalConsumption(){
        System.out.println("\n Total Consumption \n");
        System.out.print("Enter user CIN: ");
        String cin = scanner.next();
        Double result = consumptionService.calcTotalConsumption(cin);
        System.out.println("\nTotal carbon consumption of user " + cin + "is : " + result +"  KgCO2eq");
    }

    public void calcAverageConsumption(){
        System.out.println("\n Average Consumption \n");
        LocalDate startDate;
        LocalDate endDate;
        System.out.print("Enter user CIN: ");
        String cin = scanner.next();
        while (true) {
            startDate = DateChecker.isDateValid(scanner,"Enter start date (format: YYYY-MM-DD) : ");

            endDate = DateChecker.isDateValid(scanner,"Enter end date (format: YYYY-MM-DD) : ");

            if (!endDate.isBefore(startDate)) {
                break;
            } else {
                System.out.println(" \n End date cannot be before the start date. Please enter valid dates.\n");
            }
        }
        Double result = consumptionService.calcAverageConsumption(cin, startDate, endDate);
        System.out.println("\n Average consumption of user : " + cin + " in period between  : " +startDate +" & "+endDate+ " is : " + result +"  KgCO2eq");
    }

    public void dailyConsumption() {
        System.out.println(" \n Daily Consumption \n");

        System.out.print("Enter user CIN: ");
        String cin = scanner.next();

        System.out.print("Enter the date of the day (format: YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.next());

        Double result = consumptionService.getDailyConsumption(cin, date);
        System.out.println("Daily Consumption: " + result +"  KgCO2eq");
    }

    public void weeklyConsumption() {
        System.out.println("Weekly Consumption");

        System.out.print("Enter user CIN: ");
        String cin = scanner.next();

        System.out.print("Enter the date of the week (format: YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.next());

        float result = consumptionService.getWeeklyConsumption(cin, date);
        System.out.println("Weekly Consumption: " + result +"  KgCO2eq");
    }

    public void monthlyConsumption() {
        System.out.println("Monthly Consumption");

        System.out.print("Enter user CIN: ");
        String cin = scanner.next();

        System.out.print("Enter the date of the month (format: YYYY-MM-DD): ");
        LocalDate monthStart = LocalDate.parse(scanner.next());

        float result = consumptionService.getMonthlyConsumption(cin, monthStart);
        System.out.println("Monthly Consumption: " + result +"  KgCO2eq");
    }
}
