package utils;

import entities.Consumption;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DateChecker {

    public static boolean isDateAvailable(LocalDate startDate, LocalDate endDate, List<LocalDate> range) {
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)){
            if(range.contains(date)){
                return false;
            }
        }
        return true;
    }

    public static LocalDate isDateValid(Scanner scanner, String text){
        while (true) {
            try {
                System.out.print(text);
                LocalDate date = LocalDate.parse(scanner.nextLine());
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format or value! Please enter a valid date in the format YYYY-MM-DD.");
            }
        }
    }

    public static List<LocalDate> getDatesList(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = endDate.plusDays(1);
        return startDate.datesUntil(currentDate).collect(Collectors.toList());
    }
}
