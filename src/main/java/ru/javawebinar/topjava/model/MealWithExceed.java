package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealWithExceed {

    private final Long id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public Long getId() {
        return id;
    }

    public String getFormattedDateTime(){
        return dateTime.format(TimeUtil.FORMATTER);
    }

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed, Long id) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}