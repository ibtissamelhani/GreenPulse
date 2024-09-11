package service;

import entities.*;
import repository.ConsumptionRepository;
import repository.FoodRepository;
import repository.HousingRepository;
import repository.TransportRepository;
import utils.DateChecker;

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

            consumption.setConsumptionImpact(consumption.calculerImpact());

            consumptionId = consumptionRepository.save(consumption.getStartDate(), consumption.getEndDate(), consumption.getValue(), consumption.getConsumptionImpact(), consumption.getConsumptionType(), user.get().getId());

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
        return user.get().getConsumptions().stream().mapToDouble(Consumption::calculerImpact).sum() ;
    }
//
//    public float getDailyConsumption(Long userID, LocalDate date) {
//        User user = userService.getUserById(userID);
//        if (user != null) {
//            float dailyConsumption = 0f;
//            for(Consumption consumption: user.getConsumptions()) {
//                if (!date.isBefore(consumption.getStartDate()) && !date.isAfter(consumption.getEndDate())) {
//                    Long numOfDays = consumption.getStartDate().until(consumption.getEndDate(), ChronoUnit.DAYS) +1 ;
//                    float result = consumption.getValue() / numOfDays;
//                    dailyConsumption += result;
//
//                }
//            }
//            return dailyConsumption;
//        }else {
//            System.out.println("User not found");
//            return 0f;
//        }
//    }
//
//    public float getWeeklyConsumption(Long userID, LocalDate weekStartDay) {
//
//        User user = userService.getUserById(userID);
//        if (user != null) {
//
//            float weeklyConsumption = 0f;
//            LocalDate weekEndDay = weekStartDay.plusDays(6);
//
//            for (Consumption consumption : user.getConsumptions()) {
//
//                if (!weekEndDay.isBefore(consumption.getStartDate()) && !weekStartDay.isAfter(consumption.getEndDate())) {
//                    Long numOfDays = consumption.getStartDate().until(consumption.getEndDate(), ChronoUnit.DAYS) + 1;
//                    float dailyValue = consumption.getValue() / numOfDays;
//
//                    // determiner les jours commun entre la semaine et periode
//                    LocalDate sharedDateStart;
//                    LocalDate sharedDateEnd;
//
//                    if (consumption.getStartDate().isBefore(weekStartDay)) {
//                        sharedDateStart = weekStartDay;
//                    } else {
//                        sharedDateStart = consumption.getStartDate();
//                    }
//
//                    if (consumption.getEndDate().isAfter(weekEndDay)) {
//                        sharedDateEnd = weekEndDay;
//                    } else {
//                        sharedDateEnd = consumption.getEndDate();
//                    }
//
//                    long sharedDays;
//                    if (sharedDateStart.isBefore(sharedDateEnd) || sharedDateStart.isEqual(sharedDateEnd)) {
//                        sharedDays = sharedDateStart.until(sharedDateEnd, ChronoUnit.DAYS) + 1;
//                    } else {
//                        sharedDays = 0;
//                    }
//
//                    weeklyConsumption += dailyValue * sharedDays;
//                }
//            }
//            return weeklyConsumption;
//        } else {
//            System.out.println("User not found");
//            return 0f;
//        }
//    }
//
//    public float getMonthlyConsumption(Long userID, LocalDate monthStart) {
//        User user = userService.getUserById(userID);
//        if (user != null) {
//            float monthlyConsumption = 0f;
//            LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());
//
//            for (Consumption consumption : user.getConsumptions()) {
//                if (!monthEnd.isBefore(consumption.getStartDate()) && !monthStart.isAfter(consumption.getEndDate())) {
//                    Long numOfDays = consumption.getStartDate().until(consumption.getEndDate(), ChronoUnit.DAYS) + 1;
//                    Float dailyValue = consumption.getValue() / numOfDays;
//                    LocalDate sharedDateStart;
//                    LocalDate sharedDateEnd;
//
//                    if (consumption.getStartDate().isBefore(monthStart)) {
//                        sharedDateStart = monthStart;
//                    } else {
//                        sharedDateStart = consumption.getStartDate();
//                    }
//
//                    if (consumption.getEndDate().isAfter(monthEnd)) {
//                        sharedDateEnd = monthEnd;
//                    } else {
//                        sharedDateEnd = consumption.getEndDate();
//                    }
//
//                    Long sharedDays = sharedDateStart.until(sharedDateEnd, ChronoUnit.DAYS) + 1;
//
//                    monthlyConsumption += dailyValue * sharedDays;
//                }
//            }
//            return monthlyConsumption;
//
//        } else {
//            System.out.println("User not found");
//            return 0f;
//        }
//    }
//
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
