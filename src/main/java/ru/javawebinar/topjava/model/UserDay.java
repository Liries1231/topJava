package ru.javawebinar.topjava.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDay {

  public static void main(String[] args) {
    User user = new User(1, "kirill", 10);
    User user2 = new User(12, "kirill2", 11);
    User user3 = new User(13, "kirill3", 12);
    List<User> users = List.of(user, user2, user3);
    List<User> users1 = filterStream(users);
    System.out.println(users1.toString());





  }


  public static List<UserWithMonth> monthConvert(List<User> users) {
    List<UserWithMonth> list = new ArrayList<UserWithMonth>();

    for (User user : users) {
      int month = user.getAge() * 12;
      UserWithMonth userWithMonth = new UserWithMonth(user.getId(), user.getName(), month);
      list.add(userWithMonth);

    }
    return list;


  }

  public static List<UserWithMonth> monthStream(List<User> users) {
    return users.stream()
        .map(user -> {
          System.out.println("до трансформации =" + user.getAge());

          int month = user.getAge() * 12;
          System.out.println("после трансформации =" + month);
          UserWithMonth userWithMonth = new UserWithMonth(user.getId(), user.getName(), month);
          System.out.println("РЕЗУЛЬТАТ =" + userWithMonth);
          return userWithMonth;
        })
        .collect(Collectors.toList());


  }
  public static List<User> filterStream(List<User> users) {
    return users.stream()
        .filter(user -> user.getAge() != 12)


        .collect(Collectors.toList());


  }

}
