package ru.javawebinar.topjava.repos;

import java.util.List;

public interface MealRepository<T> {

  void add(T t);


  void delete(int id);

  void update(int id, T t);


  List<T> getAll();

  T getById(int id);

}
