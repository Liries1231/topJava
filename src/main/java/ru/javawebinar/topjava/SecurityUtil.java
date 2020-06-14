package ru.javawebinar.topjava;

public class SecurityUtil {

    private static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static int authUserId() {
        return 1;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}
