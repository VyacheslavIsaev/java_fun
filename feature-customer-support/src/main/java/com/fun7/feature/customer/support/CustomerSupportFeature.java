package com.fun7.feature.customer.support;

import com.fun7.feature.impl.FeatureUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CustomerSupportFeature extends FeatureUtils {

    // TODO: Move configuration to yml file.
    public static final String SUPPORT_ZONE = "Europe/Ljubljana";
    public static final String SUPPORT_OPEN_TIME  = "09:00";
    public static final String SUPPORT_CLOSE_TIME = "15:00";
    public static final List<Integer> SUPPORT_WEEK_DAYS = new ArrayList<>(
            Arrays.asList( 1, 2, 3, 4, 5 ) );
    public static final List<String>  FIXED_HOLIDAYS    = new ArrayList<>(
            Arrays.asList(
        "01.01",
        "02.01",
        "08.02",
        "27.04",
        "01.05",
        "02.05",
        "08.06",
        "25.06",
        "17.08",
        "15.09",
        "23.09",
        "25.10",
        "01.11",
        "23.11",
        "26.12",
        "15.08",
        "31.10",
        "25.12")
    );

    @Override
    public boolean enabled(String userId, String cc, String timezone) {
        LocalDateTime currentDate = LocalDateTime.now(DateTimeZone.forID(SUPPORT_ZONE));
        Date date = currentDate.toDate();

        return !isHoliday(date) && onAvailableWeekDay(currentDate.getDayOfWeek()) && inTime(date);
    }

    public boolean inTime(Date date){
        DateFormat df = new SimpleDateFormat("HH:mm");

        df.setTimeZone(TimeZone.getTimeZone(SUPPORT_ZONE));
        String currentTime = df.format(date);

        return currentTime.compareTo(SUPPORT_OPEN_TIME) >=0
                && currentTime.compareTo(SUPPORT_CLOSE_TIME)<=0;
    }

    public boolean onAvailableWeekDay(int weekDay){
        return SUPPORT_WEEK_DAYS.contains(weekDay);
    }

    public boolean isHoliday(Date date){
        return isFixedHoliday(date) || isFloatingHoliday(date);
    }

    public boolean isFixedHoliday(Date date){
        DateFormat df = new SimpleDateFormat("dd.MM");

        df.setTimeZone(TimeZone.getTimeZone(SUPPORT_ZONE));
        String currentDate = df.format(date);

        return FIXED_HOLIDAYS.contains(currentDate);
    }

    public boolean isFloatingHoliday(Date date){
        return false;
    }
}
