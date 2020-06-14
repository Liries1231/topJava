package ru.javawebinar.topjava.repository;

import java.util.Collection;
import java.util.List;
import ru.javawebinar.topjava.model.Meal;

public interface MealRepository<T> {

  void add(T t);



  void delete(int id);

  void update(int id, T t);


  List<T> getAll();

  T getById(int id);
  T check(T t);
  Collection<Meal> getAll(int currentUserId);


}
