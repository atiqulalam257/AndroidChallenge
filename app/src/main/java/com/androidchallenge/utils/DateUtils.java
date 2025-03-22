package com.androidchallenge.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for handling date formatting and time calculations.
 * Provides methods to format dates and compute relative time differences.
 */
public class DateUtils {

    /**
     * Formats a given ISO 8601 date string (e.g., "2025-03-06T20:00:00Z")
     * into a human-readable format (e.g., "06 Mar, 2025").
     *
     * @param inputDate The date string in ISO 8601 format.
     * @return A formatted date string in "dd MMM, yyyy" format.
     */
    public static String formatDate(String inputDate) {
        // Parse the input string to ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(inputDate);

        // Define the desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");

        // Format and return the date
        return zonedDateTime.format(outputFormatter);
    }

    /**
     * Converts a given timestamp (ISO 8601 format) into a relative time representation.
     * Example: "10 hours ago", "2 days ago", "Just now".
     *
     * @param timestamp The timestamp in ISO 8601 format (e.g., "2025-03-07T11:00:59Z").
     * @return A human-readable string representing the time difference.
     */
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
