package service;

import entities.Consumption;
import entities.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ConsumptionService {

    private UserService userService = new UserService();

    public void addConsumptionToUser(Long userId, LocalDate startDate, LocalDate endDate, Float value) {

        User user = userService.getUserById(userId);
        if (user != null) {

            Consumption consumption = new Consumption(startDate,endDate,value);

            user.addConsumption(consumption);

            System.out.println("Consumption added successfully for user: " + user.getName());
        } else {
            System.out.println("User not found with ID: " + userId);
        }
    }


    public float getDailyConsumption(Long userID, LocalDate date) {
        User user = userService.getUserById(userID);
        if (user != null) {
            Float dailyConsumption = 0f;
            for(Consumption consumption: user.getConsommations()) {
                if (!date.isBefore(consumption.getStartDate()) && !date.isAfter(consumption.getEndDate())) {
                    Long numOfDays = consumption.getStartDate().until(consumption.getEndDate(), ChronoUnit.DAYS) +1 ;
                    Float result = consumption.getValue() / numOfDays;
                    dailyConsumption += result;

                }
            }
            return dailyConsumption;
        }else {
            System.out.println("User not found");
            return 0f;
        }
    }
}
