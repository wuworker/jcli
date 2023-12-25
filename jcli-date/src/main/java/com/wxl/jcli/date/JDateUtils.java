package com.wxl.jcli.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;

/**
 * Create by wuxingle on 2020/08/14
 * 日期相关工具类
 */
public class JDateUtils {

    private static DateTimeFormatter[] dateTimeFormatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss"),
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"),
            DateTimeFormatter.ISO_INSTANT,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.RFC_1123_DATE_TIME,
            DateTimeFormatter.ISO_ZONED_DATE_TIME
    };

    private static DateTimeFormatter[] dateFormatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy年MM月dd日"),
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ISO_DATE,
            DateTimeFormatter.ISO_ORDINAL_DATE,
            DateTimeFormatter.ISO_WEEK_DATE
    };

    private static DateTimeFormatter[] timeFormatters = {
            DateTimeFormatter.ofPattern("HH:mm:ss"),
            DateTimeFormatter.ofPattern("HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("HHmmss"),
            DateTimeFormatter.ofPattern("HHmmssSSS"),
            DateTimeFormatter.ISO_TIME
    };


    /**
     * 输入的日期是否为时间戳格式
     *
     * @param date
     * @return
     */
    public static boolean isTimestamp(String date) {
        try {
            long ts = Long.parseLong(date);
            return ts >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 解析为时间戳
     *
     * @param date
     * @return
     */
    public static Instant parseToInstant(String date) {
        if (isTimestamp(date)) {
            return Instant.ofEpochMilli(Long.parseLong(date));
        }
        return null;
    }

    /**
     * timestamp转localDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime toLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, JDateConstant.DEFAULT_ZONE);
    }

    /**
     * localDateTime转timestamp
     *
     * @param dateTime
     * @return
     */
    public static long toTimestamp(LocalDateTime dateTime) {
        Instant instant = dateTime.atZone(JDateConstant.DEFAULT_ZONE).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * 检测输入的格式
     *
     * @param date
     * @return
     */
    public static DateTimeFormatter detectFormatter(String date) {
        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
            try {
                LocalDateTime.parse(date, dateTimeFormatter);
                return dateTimeFormatter;
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
        for (DateTimeFormatter dateFormatter : dateFormatters) {
            try {
                LocalDate.parse(date, dateFormatter);
                return dateFormatter;
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
        for (DateTimeFormatter timeFormatter : timeFormatters) {
            try {
                LocalTime.parse(date, timeFormatter);
                return timeFormatter;
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
        return null;
    }

    /**
     * 解析为DateTime
     */
    public static LocalDateTime parseDateTime(String date) {
        if (isTimestamp(date)) {
            Instant instant = Instant.ofEpochMilli(Long.parseLong(date));
            return LocalDateTime.ofInstant(instant, JDateConstant.DEFAULT_ZONE);
        }
        DateTimeFormatter formatter = detectFormatter(date);
        if (formatter == null) {
            return null;
        }

        return parseDateTime(formatter, date);
    }

    /**
     * 解析为DateTime
     */
    public static LocalDateTime parseDateTime(String fmt, String date) {
        try {
            return parseDateTime(DateTimeFormatter.ofPattern(fmt), date);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime parseDateTime(DateTimeFormatter fmt, String date) {
        LocalDate localDate;
        LocalTime localTime;
        try {
            TemporalAccessor temporalAccessor = fmt.parse(date);
            localDate = temporalAccessor.query(TemporalQueries.localDate());
            localTime = temporalAccessor.query(TemporalQueries.localTime());
            if (localDate == null && localTime == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        if (localDate == null) {
            localDate = LocalDate.now();
        }
        if (localTime == null) {
            localTime = LocalTime.of(0, 0, 0);
        }
        return LocalDateTime.of(localDate, localTime);
    }
}

