package com.androidchallenge.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String formatDate(String inputDate) {
        // Parse the input string to ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDate);

        // Define the desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");

        // Format and return the date
        return zonedDateTime.format(outputFormatter);
    }

    public static String getTimeAgo(String timestamp) {
        // Parse the given timestamp (UTC)
        Instant pastTime = Instant.parse(timestamp);

        // Get the current time in UTC
        Instant now = Instant.now();

        // Calculate the duration between the two times
        Duration duration = Duration.between(pastTime, now);

        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) {
            return days + (days == 1 ? " day ago" : " days ago");
        } else if (hours > 0) {
            return hours + (hours == 1 ? " hour ago" : " hours ago");
        } else if (minutes > 0) {
            return minutes + (minutes == 1 ? " minute ago" : " minutes ago");
        } else {
            return "Just now";
        }
    }
}
