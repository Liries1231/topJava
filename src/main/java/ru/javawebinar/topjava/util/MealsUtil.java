package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.MealTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> meals = Arrays.asList(
                new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500 ),
                new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );


        List<MealTo> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<MealTo> filteredByCycles(
        List<Meal> meals,
        LocalTime startTime,
        LocalTime endTime,
        int caloriesPerDay) {

        // first step, counting calories by day
        Map<LocalDate, Integer> statistics = new HashMap<>();

        for (Meal userMeal : meals) {
            LocalDate dayMeal = userMeal.getDateTime().toLocalDate();
            Integer calories = statistics.get(dayMeal);
            if (calories != null) {
                statistics.put(dayMeal, calories + userMeal.getCalories());
            } else {
                statistics.put(dayMeal, userMeal.getCalories());
            }
        }

        // second step, make snapshot
        List<MealTo> allUserExcess = new ArrayList<>();

        for (Meal userMeal : meals) {
            Integer calories = statistics.get(userMeal.getDateTime().toLocalDate());

            boolean dohrenaSbel;
            if (calories > caloriesPerDay) {
                dohrenaSbel = true;
            } else {
                dohrenaSbel = false;
            }
            MealTo result = new MealTo(userMeal, dohrenaSbel);
            allUserExcess.add(result);
        }

        // third step, find eat between start and end time, from filter
        List<MealTo> MAIN_RESULT = new ArrayList<>();

        for (MealTo userExcess : allUserExcess) {
            LocalTime currentTime = userExcess.getDateTime().toLocalTime();
            if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
                MAIN_RESULT.add(userExcess);
            }
        }

        return MAIN_RESULT;
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals,
        LocalTime startTime,
        LocalTime endTime,
        int caloriesPerDay) {

        Map<LocalDate, Integer> map =
            meals.stream()
                .collect(
                    Collectors.groupingBy(
                        Meal::getLocalDate,
                        Collectors.summingInt(Meal::getCalories)
                    )
                );

        return meals.stream()
            .filter(userMeal -> {
                LocalTime currentTime = userMeal.getDateTime().toLocalTime();
                return currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
            })
            .map(userMeal -> {
                    Integer calories = map.get(userMeal.getDateTime().toLocalDate());
                    return new MealTo(userMeal, calories > caloriesPerDay);
                }
            )
            .collect(Collectors.toList());
    }



}
