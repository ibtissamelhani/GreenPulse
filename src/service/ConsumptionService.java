package service;

import entities.Consumption;
import entities.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ConsumptionService {

    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);

    public void addConsumptionToUser(Long userId) {

        Float value = null;
        LocalDate endDate;
        LocalDate startDate;
        User user = userService.getUserById(userId);
        if (user != null) {

            // validation des date
            while (true) {
                System.out.print("Enter start date (format: YYYY-MM-DD) : ");
                startDate = LocalDate.parse(scanner.nextLine());

                System.out.print("Enter end date (format: YYYY-MM-DD) : ");
                endDate = LocalDate.parse(scanner.nextLine());

                if (!endDate.isBefore(startDate)) {
                    break; // Exit the loop if the end date is valid
                } else {
                    System.out.println("End date cannot be before the start date. Please enter valid dates.");
                }
            }

            // validation value
            while (value == null) {
                try {
                    System.out.print("Enter value: ");
                    value = Float.parseFloat(scanner.nextLine());
                    if (value <= 0) {
                        System.out.println("Value must be a positive number. Please enter a valid value.");
                        value = null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            Consumption consumption = new Consumption(startDate, endDate, value);

            user.addConsumption(consumption);

        }else {
            System.out.println("User not found");
        }

    }

    public float getDailyConsumption(Long userID, LocalDate date) {
        User user = userService.getUserById(userID);
        if (user != null) {
            float dailyConsumption = 0f;
            for(Consumption consumption: user.getConsommations()) {
                if (!date.isBefore(consumption.getStartDate()) && !date.isAfter(consumption.getEndDate())) {
                    Long numOfDays = consumption.getStartDate().until(consumption.getEndDate(), ChronoUnit.DAYS) +1 ;
                    float result = consumption.getValue() / numOfDays;
                    dailyConsumption += result;

                }
            }
            return dailyConsumption;
        }else {
            System.out.println("User not found");
            return 0f;
        }
    }

    public float getWeeklyConsumption(Long userID, LocalDate weekStartDay) {

        User user = userService.getUserById(userID);
        if (user != null) {

            float weeklyConsumption = 0f;
            LocalDate weekEndDay = weekStartDay.plusDays(6);

            for (Consumption consumption : user.getConsommations()) {

                if (!weekEndDay.isBefore(consumption.getStartDate()) && !weekStartDay.isAfter(consumption.getEndDate())) {
                    Long numOfDays = consumption.getStartDate().until(consumption.getEndDate(), ChronoUnit.DAYS) + 1;
                    float dailyValue = consumption.getValue() / numOfDays;

                    // determiner les jours commun entre la semaine et periode
                    LocalDate sharedDateStart;
                    LocalDate sharedDateEnd;

                    if (consumption.getStartDate().isBefore(weekStartDay)) {
                        sharedDateStart = weekStartDay;
                    } else {
                        sharedDateStart = consumption.getStartDate();
                    }

                    // Determine the effective end date of the overlap
                    if (consumption.getEndDate().isAfter(weekEndDay)) {
                        sharedDateEnd = weekEndDay;
                    } else {
                        sharedDateEnd = consumption.getEndDate();
                    }

                    long sharedDays;
                    if (sharedDateStart.isBefore(sharedDateEnd) || sharedDateStart.isEqual(sharedDateEnd)) {
                        sharedDays = sharedDateStart.until(sharedDateEnd, ChronoUnit.DAYS) + 1;
                    } else {
                        sharedDays = 0;
                    }

                    weeklyConsumption += dailyValue * sharedDays;
                }
            }
            return weeklyConsumption;
        } else {
            System.out.println("User not found");
            return 0f;
        }
    }

}
