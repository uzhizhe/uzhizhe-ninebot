package com.uzhizhe.ninebot.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Desc jdk 8.0 时间工具类
 * @Author qingjiang.li
 * @Email qingjiang.li@ninebot.com
 * @Date 2019-11-09
 */
public final class LDTUtil {

    //DateTimeFormatter
    private static DateTimeFormatter dateTimeFormatterDef = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter dateTimeFormatterMilli = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static DateTimeFormatter dateFormatterDef = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter timeFormatterDef = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static ZoneId ZoneIdDef = ZoneId.systemDefault();


    private LDTUtil() {
    }


    /**
     * 格式化指定字符串时间到LocalDateTime
     *
     * @param dateStr dateStr
     * @param format  format
     * @return
     */
    public static LocalDateTime stringToDate(String dateStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return stringToDate(dateStr, dateTimeFormatter);
    }

    public static LocalDateTime stringToDate(String dateStr, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.parse(dateStr, dateTimeFormatter);
    }

    public static LocalDateTime stringToDate(String dateStr) {
        return stringToDate(dateStr, dateTimeFormatterDef);
    }

    public static String dateToString(LocalDateTime localDateTime) {
        return dateToString(localDateTime, dateTimeFormatterDef);
    }

    public static String dateToString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return dateToString(localDateTime, dateTimeFormatter);
    }

    public static String dateToString(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    public static String dateToString(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return dateToString(localDateTime, dateTimeFormatterDef);
    }

    public static String dateToString(Date date, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return dateToString(date, dateTimeFormatter);
    }

    public static String dateToString(Date date, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        return dateToString(localDateTime, dateTimeFormatter);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    public static Date LocalDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return Date.from(instant);
    }

    public static long dateToSecond(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneIdDef).toInstant();
        return instant.getEpochSecond();
    }

    public static long dateToMilli(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneIdDef).toInstant();
        return instant.toEpochMilli();
    }

    public static LocalDateTime milliToDate(long milli) {
        Instant instant = Instant.ofEpochMilli(milli);
        return LocalDateTime.ofInstant(instant, ZoneIdDef);
    }

    public static LocalDateTime secondToDate(long second) {
        Instant instant = Instant.ofEpochSecond(second);
        return LocalDateTime.ofInstant(instant, ZoneIdDef);
    }

    public static long getCurrentMilli() {
        return Instant.now().toEpochMilli();
    }

    public static long getCurrentSecond() {
        return Instant.now().getEpochSecond();
    }

    public static void main(String[] args) {
        Date date = new Date();
        String s = dateToString(date);
        System.out.println(s);

        LocalDateTime localDateTime = stringToDate(s);
        String s1 = dateToString(localDateTime);
        System.out.println(s1);

    }


}
