package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

public class Meal {

    public static final Meal EMPTY = new Meal();

    private Long id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private Meal(){
        this.dateTime = null;
        this.description = "";
        this.calories = 0;
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(LocalDateTime dateTime, String description, int calories, Long id) {
        this(dateTime, description, calories);
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
