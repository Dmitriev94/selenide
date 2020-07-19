package ru.netology;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CurrentLocalDate {
    private static final String DATE__FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE__FORMAT);

    public String localDateTime() {

        LocalDateTime localDateTime = LocalDateTime.now().plusDays(3);
        Date currentDateTimePlusThree = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());


        return dateFormat.format(localDateTime);
    }


}
