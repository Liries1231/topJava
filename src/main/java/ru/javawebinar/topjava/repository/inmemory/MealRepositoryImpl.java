package ru.javawebinar.topjava.repository.inmemory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import ru.javawebinar.topjava.SecurityUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

public class MealRepositoryImpl implements MealRepository<Meal> {

  private final Map<Integer, Meal> map = new ConcurrentHashMap<>();
  private final AtomicInteger newId = new AtomicInteger(1);

  {
    MealsUtil.meals.forEach(this::add);
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
    map.put(id, meal);

  }

  @Override
  public List getAll() {
    return new ArrayList<>(map.values());
  }

  @Override
  public Meal getById(int id) {
    return map.get(id);
  }

  @Override
  public Meal check(Meal meal) {
    if (!(SecurityUtil.authUserId() == meal.getUserId())) {

      return null;
    }
    if (meal.getUserId() == 0) {
      return null;
    }

    return meal;
  }

  @Override
  public Collection<Meal> getAll(int currentUserId) {

    return null;
  }
}


