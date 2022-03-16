package com.fun7.feature.customer.support;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureCustomerSupportTest {

    FeatureCustomerSupport customerSupport = new FeatureCustomerSupport();

    Date getTimeFromStr(String strTime) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        return dateFormat.parse(strTime);
    }

    Date getDateFromStr(String strDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM");
            return dateFormat.parse(strDate);
        }catch (Exception e){
            System.out.println("Exception: Converting %s".format(strDate));
        }
        return null;
    }

    @Test
    void false_if_out_of_time(){
        try {
            assertFalse(customerSupport.inTime(getTimeFromStr("8:00")));
            assertFalse(customerSupport.inTime(getTimeFromStr("8:59")));
            assertFalse(customerSupport.inTime(getTimeFromStr("15:01")));
            assertFalse(customerSupport.inTime(getTimeFromStr("16:00")));
        }catch (Exception e){
        }
    }

    @Test
    void true_if_in_time(){
        try {
            assertTrue(customerSupport.inTime(getTimeFromStr("9:00")));
            assertTrue(customerSupport.inTime(getTimeFromStr("10:00")));
            assertTrue(customerSupport.inTime(getTimeFromStr("15:00")));
        }catch (Exception e){
        }
    }

    @Test
    void true_if_holiday(){
        FeatureCustomerSupport.FIXED_HOLIDAYS.stream().forEach(
                e -> {
                    assertDateIsFixedHoliday(e);
                }
            );
    }

    private void assertDateIsFixedHoliday(String e) {
        Date date = getDateFromStr(e);
        assertTrue( customerSupport.isFixedHoliday(date) );
    }

    @Test
    void false_if_not_holiday(){
        Date date = getDateFromStr("04.01");
        assertFalse( customerSupport.isFixedHoliday(date) );
    }

    @Test
    void true_if_correct_week_day(){
        customerSupport.SUPPORT_WEEK_DAYS.stream().forEach(
            e -> assertTrue(customerSupport.onAvailableWeekDay(e))
        );
    }

    @Test
    void false_if_incorrect_week_day(){
        assertFalse(customerSupport.onAvailableWeekDay(7));
        assertFalse(customerSupport.onAvailableWeekDay(8));
    }
}
