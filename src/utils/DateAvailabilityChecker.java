package utils;

import java.time.LocalDate;
import java.util.List;

public class DateAvailabilityChecker {

    public static boolean isDateAvailable(LocalDate startDate, LocalDate endDate, List<LocalDate> range) {
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)){
            if(range.contains(date)){
                return false;
            }
        }
        return true;
    }
}
