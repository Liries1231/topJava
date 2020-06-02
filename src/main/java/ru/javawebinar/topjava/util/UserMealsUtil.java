package ru.javawebinar.topjava.util;

import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

public class UserMealsUtil {

  public static void main(String[] args) {
    List<UserMeal> meals = Arrays.asList(
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 40),

        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
        new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    );

    LocalTime from = LocalTime.of(7, 0);
    LocalTime to = LocalTime.of(12, 0);
    int limit = 2000;
    List<UserMealWithExcess> cycles = filteredByCycles(meals, from, to, limit);

    List<UserMealWithExcess> streams = filteredByStreams(meals, from, to, limit);

    cycles.forEach(System.out::println);

    streams.forEach(System.out::println);

  }

  public static List<UserMealWithExcess> filteredByCycles(
      List<UserMeal> meals,
      LocalTime startTime,
      LocalTime endTime,
      int caloriesPerDay) {

    // first step, counting calories by day
    Map<LocalDate, Integer> statistics = new HashMap<>();

    for (UserMeal userMeal : meals) {
      LocalDate dayMeal = userMeal.getDateTime().toLocalDate();
      Integer calories = statistics.get(dayMeal);
      if (calories != null) {
        statistics.put(dayMeal, calories + userMeal.getCalories());
      } else {
        statistics.put(dayMeal, userMeal.getCalories());
      }
    }

    // second step, make snapshot
    List<UserMealWithExcess> allUserExcess = new ArrayList<>();

    for (UserMeal userMeal : meals) {
      Integer calories = statistics.get(userMeal.getDateTime().toLocalDate());

      boolean dohrenaSbel;
      if (calories > caloriesPerDay) {
        dohrenaSbel = true;
      } else {
        dohrenaSbel = false;
      }
      UserMealWithExcess result = new UserMealWithExcess(userMeal, dohrenaSbel);
      allUserExcess.add(result);
    }

    // third step, find eat between start and end time, from filter
    List<UserMealWithExcess> MAIN_RESULT = new ArrayList<>();

    for (UserMealWithExcess userExcess : allUserExcess) {
      LocalTime currentTime = userExcess.getDateTime().toLocalTime();
      if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
        MAIN_RESULT.add(userExcess);
      }
    }

    return MAIN_RESULT;
  }

  public static List<UserMealWithExcess> filteredByStreams(
      List<UserMeal> meals,
      LocalTime startTime,
      LocalTime endTime,
      int caloriesPerDay) {

    Map<LocalDate, Integer> map =
        meals.stream()
            .collect(
                Collectors.groupingBy(
                    UserMeal::getLocalDate,
                    Collectors.summingInt(UserMeal::getCalories)
                )
            );

    return meals.stream()
        .filter(userMeal -> {
          LocalTime currentTime = userMeal.getDateTime().toLocalTime();
          return currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
        })
        .map(userMeal -> {
              Integer calories = map.get(userMeal.getDateTime().toLocalDate());
              return new UserMealWithExcess(userMeal, calories > caloriesPerDay);
            }
        )
        .collect(Collectors.toList());
  }

}
