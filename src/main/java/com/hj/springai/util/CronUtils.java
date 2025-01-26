package com.hj.springai.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CronUtils {
    public static void main(String[] args) {
        // 输入的字符串日期
        String dateString = "2023-01-01 12:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 解析字符串日期为LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

        // 转换为Cron表达式
        String cronExpression = convertToCron(dateTime);
        System.out.println("Cron Expression: " + cronExpression);
    }

    private static String convertToCron(LocalDateTime dateTime) {
        return String.format("%02d %02d %02d %02d %02d ?",
                dateTime.getSecond(),
                dateTime.getMinute(),
                dateTime.getHour(),
                dateTime.getDayOfMonth(),
                dateTime.getMonthValue());
    }
}
