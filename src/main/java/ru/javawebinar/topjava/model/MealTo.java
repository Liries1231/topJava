package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class MealTo {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private int id;

    public MealTo(LocalDateTime dateTime, String description, int calories,boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.id = id;
    }

    public MealTo(Meal userMeal, boolean excess) {
        this.dateTime = userMeal.getDateTime();
        this.description = userMeal.getDescription();
        this.calories = userMeal.getCalories();
        this.excess = excess;
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

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
            "dateTime=" + dateTime +
            ", description='" + description + '\'' +
            ", calories=" + calories +
            ", excess=" + excess +
            '}';
    }
}