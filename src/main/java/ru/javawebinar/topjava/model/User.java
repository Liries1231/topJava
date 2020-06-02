package ru.javawebinar.topjava.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class User {
  private int id;
  private String name;
  private int age;


  public User() {
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        '}';

  }


  //List<UserMealWithExcess> list = new ArrayList<>();
 //   for (UserMeal us :meals) {
//    LocalTime dayMeal = us.getDateTime().toLocalTime();
  //  if (dayMeal.isAfter(startTime) && dayMeal.isBefore(endTime)){ //фильтр по дате
 //     caloriesPerDay += us.getCalories();//считаю калории

//      UserMealWithExcess userMealWithExcess = new UserMealWithExcess(us.getDateTime(),us.getDescription(),us.getCalories(),true);
 //     list.add(userMealWithExcess);
 //     System.out.println(list);
 //     System.out.println(caloriesPerDay);





  public User(int id, String name, int age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
