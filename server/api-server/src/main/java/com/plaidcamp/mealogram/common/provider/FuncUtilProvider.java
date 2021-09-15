package com.plaidcamp.mealogram.common.provider;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Component
public class FuncUtilProvider {

    public String DateToString(LocalDateTime time) {
        return DateToString(time, 0);
    }

    public String DateToString(LocalDateTime time, int duration) {
        time = time.plusDays(duration);
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return time.format(form);
    }

    public Date AddDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    public Date AddHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hour);
        return c.getTime();
    }
}
