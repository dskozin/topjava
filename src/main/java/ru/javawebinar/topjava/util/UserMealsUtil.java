package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        for (UserMealWithExceed uML : getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000)){
            System.out.println(uML.getCalories() + " " + uML.getDateTime() + " " + uML.isExceed());
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        //создать хэшлист, у которого ключом будет дата, значением - сколько каллорий
        HashMap<LocalDate,Integer> isExceed = new HashMap<>();
        for(UserMeal userMeal : mealList){
            LocalDate thisUMD = userMeal.getDateTime().toLocalDate();
            isExceed.put(thisUMD, isExceed.containsKey(thisUMD) ? isExceed.get(thisUMD) + userMeal.getCalories()
                                                                : userMeal.getCalories());
        }


        //создать список еды
        List<UserMealWithExceed> reList = new ArrayList<UserMealWithExceed>();

        //перебрать массив если даты ок, добавить в список.
        for(UserMeal uMeal:  mealList){
            if (uMeal.getDateTime().toLocalTime().isAfter(startTime)
                    && uMeal.getDateTime().toLocalTime().isBefore(endTime)){

                Boolean isEx = isExceed.get(uMeal.getDateTime().toLocalDate()) > caloriesPerDay;
                UserMealWithExceed uMealWE = new UserMealWithExceed(uMeal.getDateTime(),
                                                                    uMeal.getDescription(),
                                                                    uMeal.getCalories(),
                                                                    isEx);
                reList.add(uMealWE);

            }
        }

        return reList;
    }

//    Реализовать метод UserMealsUtil.getFilteredWithExceeded:
//            -  должны возвращаться только записи между startTime и endTime
//            -  поле UserMealWithExceed.exceed должно показывать,
//                  превышает ли сумма калорий за весь день параметра метода caloriesPerDay
//
//    Т.е UserMealWithExceed - это запись одной еды, но поле exceeded будет одинаково для всех записей за этот день.
//
//            - Проверте результат выполнения ДЗ (можно проверить логику в http://topjava.herokuapp.com , список еды)
//            - Оцените Time complexity вашего алгоритма, если он O(N*N)- попробуйте сделать O(N).
}
