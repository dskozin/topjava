package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        for (UserMealWithExceed uML : getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)) {
            System.out.println(uML.getCalories() + " " + uML.getDateTime() + " " + uML.isExceed());
        }

        for (UserMealWithExceed uML : getFilteredWithExceededWithStreamAPI(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000)) {
            System.out.println(uML.getCalories() + " " + uML.getDateTime() + " " + uML.isExceed());
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //создать хэшлист, у которого ключом будет дата, значением - сколько каллорий
        Map<LocalDate, Integer> isExceed = new HashMap<>();
        mealList.forEach(userMeal -> {
            LocalDate thisUMD = userMeal.getDateTime().toLocalDate();
            isExceed.merge(thisUMD, userMeal.getCalories(), Integer::sum);
        });


        //создать список еды
        List<UserMealWithExceed> mealWithExceedArrayList = new ArrayList<UserMealWithExceed>();
        //перебрать массив если верямя ок, добавить в список.
        mealList.forEach(uMeal -> {
            if (TimeUtil.isBetween(uMeal.getDateTime().toLocalTime(), startTime, endTime)) {

                Boolean isEx = isExceed.get(uMeal.getDateTime().toLocalDate()) > caloriesPerDay;
                UserMealWithExceed uMealWE = new UserMealWithExceed(
                        uMeal.getDateTime(),
                        uMeal.getDescription(),
                        uMeal.getCalories(),
                        isEx);
                mealWithExceedArrayList.add(uMealWE);
            }
        });

        return mealWithExceedArrayList;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededWithStreamAPI(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //создать хэшлист, у которого ключом будет дата, значением - сколько каллорий
        Map<LocalDate, Integer> isExceed = mealList.stream()
                .collect(Collectors.groupingBy(
                        UserMeal::getLocalDate,
                        Collectors.summingInt(UserMeal::getCalories)
                ));

        //создать список еды
        return mealList.stream()
                .filter(e -> TimeUtil.isBetween(e.getDateTime().toLocalTime(), startTime, endTime))
                .map(e -> new UserMealWithExceed(
                        e.getDateTime(),
                        e.getDescription(),
                        e.getCalories(),
                        isExceed.get(e.getLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

}
