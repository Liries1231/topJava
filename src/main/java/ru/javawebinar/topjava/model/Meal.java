package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Meal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;
    private int id;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Meal( int id, LocalDateTime dateTime, String description, int calories) {
        this.id  = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;

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
    public LocalDate getLocalDate(){
        return dateTime.toLocalDate();
    }
}
