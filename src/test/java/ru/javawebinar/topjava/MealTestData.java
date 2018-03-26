package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final Meal MEAL1 = new Meal(1, LocalDateTime.of(2015,05,10,10,0), "Затврак", 1000);
    public static final Meal MEAL2 = new Meal(2, LocalDateTime.of(2015, 05,10,15,0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(3, LocalDateTime.of(2015, 05, 10, 19,0), "Ужин", 800);
    public static final Meal MEAL4 = new Meal(4, LocalDateTime.of(2015, 05, 11, 9,30), "Завтрак", 1500);
    public static final Meal MEAL5 = new Meal(5, LocalDateTime.of(2015, 05,11, 13, 15), "Обед", 1800);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
