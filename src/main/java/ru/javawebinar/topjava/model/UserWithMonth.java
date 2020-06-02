package ru.javawebinar.topjava.model;

public class UserWithMonth {


  private int id;
  private String name;
  private int month;

  @Override
  public String toString() {
    return "UserWithMonth{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", month=" + month +
        '}';
  }

  public UserWithMonth(int id, String name, int month) {
    this.id = id;
    this.name = name;
    this.month = month;
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

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public UserWithMonth() {
  }
}





