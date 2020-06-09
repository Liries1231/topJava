package ru.javawebinar.topjava.repos;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import ru.javawebinar.topjava.model.Meal;

public class MealRepositoryImpl implements MealRepository<Meal> {

 private final Map<Integer, Meal> map = new ConcurrentHashMap<>();
 private final AtomicInteger newId = new AtomicInteger(1);

 public MealRepositoryImpl() {
  add(new Meal(1, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
  add(new Meal(2, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
  add(new Meal(3, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
  add(new Meal(4, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
  add(new Meal(5, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
  add(new Meal(6, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
  add(new Meal(7, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
 }



 @Override
 public void add(Meal meal) {
  int id = newId.getAndIncrement();
  meal.setId(id);
  map.put(id, meal);

 }

 @Override
 public void delete(int id) {
  map.remove(id);

 }



 @Override
 public void update(int id, Meal meal) {
  map.putIfAbsent(id, meal);

 }

 @Override
 public List getAll() {
  return new ArrayList<>(map.values());
 }

 @Override
 public Meal getById(int id) {
  return map.get(id);
 }
}


