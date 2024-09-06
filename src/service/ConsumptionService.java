package service;

import entities.Consumption;
import entities.User;
import utils.DateChecker;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsumptionService {

    private final UserService userService;
    private final Scanner scanner;

    public ConsumptionService(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

//    public void addConsumptionToUser(long userId) {
//
//        Float value = null;
//        LocalDate endDate;
//        LocalDate startDate;
//        User user = userService.getUserById(userId);
//        if (user != null) {
//
//            // validation des date
//            while (true) {
//                startDate = DateChecker.isDateValid(scanner,"Enter start date (format: YYYY-MM-DD) : ");
//
//                endDate = DateChecker.isDateValid(scanner,"Enter end date (format: YYYY-MM-DD) : ");
//
//                if (!endDate.isBefore(startDate)) {
//                    break;
//                } else {
//                    System.out.println(" \n End date cannot be before the start date. Please enter valid dates.\n");
//                }
//            }
//
//            // validation value
//            while (value == null) {
//                try {
//                    System.out.print("Enter value: ");
//                    value = Float.parseFloat(scanner.nextLine());
//                    if (value <= 0) {
//                        System.out.println("Value must be a positive number. Please enter a valid value.");
//                        value = null;
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("\n Invalid input! Please enter a valid number.\n");
//                }
//            }
//
//            List<LocalDate> datesList = user.getconsumptions() != null ? getDatesList(user.getconsumptions()) : new ArrayList<>();
//            boolean result = DateChecker.isDateAvailable(startDate, endDate, datesList);
//
//            if (result) {
//                Consumption consumption = new Consumption(startDate, endDate, value);
//
//                userService.addConsumption(user,consumption);
//            } else {
//                System.out.println("Date Already exist ");
//            }
//
//        }else {
//            System.out.println("User not found");
//        }
//
//    }

    public float calcTotalConsumption(long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            Float totalConsumption = 0.0f;
            for(Consumption consumption: user.getconsumptions()){
                totalConsumption += consumption.getValue();
            }
            return totalConsumption;
        }else {
            System.out.println("User not found with ID: " + userId);
            return 0.0f;
        }
    }

    public float getDailyConsumption(Long userID, LocalDate date) {
        User user = userService.getUserById(userID);
        if (user != null) {
            float dailyConsumption = 0f;
            for(Consumption consumption: user.getconsumptions()) {
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

            for (Consumption consumption : user.getconsumptions()) {

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

    public float getMonthlyConsumption(Long userID, LocalDate monthStart) {
        User user = userService.getUserById(userID);
        if (user != null) {
            float monthlyConsumption = 0f;
            LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

            for (Consumption consumption : user.getconsumptions()) {
                if (!monthEnd.isBefore(consumption.getStartDate()) && !monthStart.isAfter(consumption.getEndDate())) {
                    Long numOfDays = consumption.getStartDate().until(consumption.getEndDate(), ChronoUnit.DAYS) + 1;
                    Float dailyValue = consumption.getValue() / numOfDays;
                    LocalDate sharedDateStart;
                    LocalDate sharedDateEnd;

                    if (consumption.getStartDate().isBefore(monthStart)) {
                        sharedDateStart = monthStart;
                    } else {
                        sharedDateStart = consumption.getStartDate();
                    }

                    if (consumption.getEndDate().isAfter(monthEnd)) {
                        sharedDateEnd = monthEnd;
                    } else {
                        sharedDateEnd = consumption.getEndDate();
                    }

                    Long sharedDays = sharedDateStart.until(sharedDateEnd, ChronoUnit.DAYS) + 1;

                    monthlyConsumption += dailyValue * sharedDays;
                }
            }
            return monthlyConsumption;

        } else {
            System.out.println("User not found");
            return 0f;
        }
    }

    public List<LocalDate> getDatesList(List<Consumption> consumptions) {
        List<LocalDate> dates = new ArrayList<>();
        for(Consumption consumption: consumptions) {
            for(LocalDate date = consumption.getStartDate(); !date.isAfter(consumption.getEndDate()); date = date.plusDays(1)) {
                dates.add(date);
            }
        }
        return dates;
    }

}
