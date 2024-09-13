package service;

import database.DBConfiguration;
import entities.*;
import repository.ConsumptionRepository;
import repository.FoodRepository;
import repository.HousingRepository;
import repository.TransportRepository;
import utils.DateChecker;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsumptionService {

    private final UserService userService;
    private final ConsumptionRepository consumptionRepository;
    private final FoodRepository foodRepository;
    private final HousingRepository housingRepository;
    private final TransportRepository transportRepository;
    private final Scanner scanner;

    public ConsumptionService(UserService userService, ConsumptionRepository consumptionRepository, FoodRepository foodRepository, HousingRepository housingRepository, TransportRepository transportRepository) {
        this.userService = userService;
        this.consumptionRepository = consumptionRepository;
        this.foodRepository = foodRepository;
        this.housingRepository = housingRepository;
        this.transportRepository = transportRepository;
        this.scanner = new Scanner(System.in);
    }

    public void addConsumptionToUser(String cin) {

        Float value = null;
        LocalDate endDate;
        LocalDate startDate;
        Optional<User> user = userService.getUserByCin(cin);
        if (user.isPresent()) {
            // validation des date
            while (true) {
                startDate = DateChecker.isDateValid(scanner,"Enter start date (format: YYYY-MM-DD) : ");

                endDate = DateChecker.isDateValid(scanner,"Enter end date (format: YYYY-MM-DD) : ");

                if (!endDate.isBefore(startDate)) {
                    break;
                } else {
                    System.out.println(" \n End date cannot be before the start date. Please enter valid dates.\n");
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
                    System.out.println("\n Invalid input! Please enter a valid number.\n");
                }
            }

            // Prompt for consumption type
            ConsumptionType consumptionType = null;
            while (consumptionType == null) {
                System.out.println("Enter consumption type (TRANSPORT, HOUSING, FOOD): ");
                try {
                    consumptionType = ConsumptionType.valueOf(scanner.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid consumption type! Please enter one of: TRANSPORT, HOUSING, FOOD.");
                }
            }

            Consumption consumption = null;
            int consumptionId = -1;

            switch (consumptionType) {
                case TRANSPORT:
                    System.out.print("Enter distance traveled: ");
                    double distanceTraveled = Double.parseDouble(scanner.nextLine());

                    System.out.print("Enter vehicle type (train - car): ");
                    String vehicleType = scanner.nextLine();

                    consumption = new Transport(startDate, endDate, value,consumptionType, distanceTraveled, vehicleType);
                    break;

                case HOUSING:
                    System.out.print("Enter energy consumption: ");
                    double energyConsumption = Double.parseDouble(scanner.nextLine());

                    System.out.print("Enter energy type (gas - electricity): ");
                    String energyType = scanner.nextLine();

                    consumption = new Housing(startDate, endDate, value,consumptionType, energyConsumption, energyType);
                    break;

                case FOOD:
                    System.out.print("Enter type of food (meat - vegetable): ");
                    String typeOfFood = scanner.nextLine();

                    System.out.print("Enter weight : ");
                    double weight = Double.parseDouble(scanner.nextLine());

                    consumption = new Food(startDate, endDate, value,consumptionType, typeOfFood, weight);
                    break;
            }


            consumptionId = consumptionRepository.save(consumption.getStartDate(), consumption.getEndDate(), consumption.getValue(), consumption.getConsumptionType(), user.get().getId());

            if (consumption instanceof Transport) {
                Transport transport = (Transport) consumption;
                transportRepository.save(transport.getDistanceTraveled(), transport.getVehicleType(), consumptionId);
            } else if (consumption instanceof Housing) {
                Housing housing = (Housing) consumption;
                housingRepository.save(housing.getEnergyConsumption(), housing.getEnergyType(), consumptionId);
            } else if (consumption instanceof Food) {
                Food food = (Food) consumption;
                foodRepository.save(food.getTypeOfFood(), food.getWeight(), consumptionId);
            }

            System.out.println("Consumption added successfully for user: " + user.get().getName());

        } else {
            System.out.println("User with CIN " + cin + " not found.");
        }
    }

    public Double calcTotalConsumption(String cin) {
        Optional<User> user = userService.getUserWithConsumptions(cin);
        return user.get().getConsumptions()
                .stream().mapToDouble(Consumption::calculerImpact).sum() ;
    }

    public Double calcAverageConsumption(String cin, LocalDate startDate, LocalDate endDate) {
        Optional<User> user = userService.getUserWithConsumptions(cin);
        List<LocalDate> period = DateChecker.getDatesList(startDate, endDate);

        long totalSharedDays = user.get().getConsumptions().stream()
                .filter(consumption -> !DateChecker.isDateAvailable(consumption.getStartDate(), consumption.getEndDate(), period))
                .mapToLong(consumption -> DateChecker.getSharedDays(consumption.getStartDate(), consumption.getEndDate(), startDate, endDate))
                .sum();

        return user.get().getConsumptions()
                .stream()
                .filter(consumption -> !DateChecker.isDateAvailable(consumption.getStartDate(),consumption.getEndDate(),period))
                .mapToDouble(Consumption::calculerImpact).sum() / totalSharedDays ;
    }

    public Double getDailyConsumption(String cin, LocalDate date) {
        Optional<User> user = userService.getUserWithConsumptions(cin);
        if (user.isPresent()) {
            return user.get().getConsumptions().stream()
                    .filter(consumption -> !date.isBefore(consumption.getStartDate()) && !date.isAfter(consumption.getEndDate()))
                    .mapToDouble(consumption -> {
                        long numOfDays = ChronoUnit.DAYS.between(consumption.getStartDate(), consumption.getEndDate()) + 1;
                        return consumption.calculerImpact() / numOfDays;
                    })
                    .sum();
        } else {
            System.out.println("User not found");
            return 0.;
        }
    }

    public float getWeeklyConsumption(String cin, LocalDate weekStartDay) {

        Optional<User> user = userService.getUserWithConsumptions(cin);
        if (user != null) {
            LocalDate weekEndDay = weekStartDay.plusDays(6);

            return user.get().getConsumptions()
                      .stream()
                      .filter(consumption -> !weekEndDay.isBefore(consumption.getStartDate()) && !weekStartDay.isAfter(consumption.getEndDate()))
                      .map(consumption -> {
                          long numOfDays = ChronoUnit.DAYS.between(consumption.getStartDate(), consumption.getEndDate()) + 1;
                          float dailyValue = (float) consumption.getValue() / numOfDays;

                          long sharedDays = DateChecker.getSharedDays(consumption.getStartDate(), consumption.getEndDate(), weekStartDay, weekEndDay);

                          return dailyValue * sharedDays;
                      }).reduce(0f, Float::sum);

        } else {
            System.out.println("User not found");
            return 0f;
        }
    }

    public float getMonthlyConsumption(String cin, LocalDate monthStart) {
        Optional<User> user = userService.getUserWithConsumptions(cin);
        if (user != null) {
            LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

            return  user.get().getConsumptions()
                    .stream()
                    .filter(consumption -> !monthEnd.isBefore(consumption.getStartDate()) && !monthStart.isAfter(consumption.getEndDate()))
                    .map(consumption -> {
                        long numOfDays = ChronoUnit.DAYS.between(consumption.getStartDate(), consumption.getEndDate()) + 1;
                        float dailyValue = (float) consumption.getValue() / numOfDays;

                        long sharedDays = DateChecker.getSharedDays(consumption.getStartDate(), consumption.getEndDate(), monthStart, monthEnd);

                        return dailyValue * sharedDays;
                    }).reduce(0f, Float::sum);

        } else {
            System.out.println("User not found");
            return 0f;
        }
    }

}
