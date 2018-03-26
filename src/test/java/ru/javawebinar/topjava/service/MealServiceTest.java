package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealService mealService;

    @Test
    public void get() throws Exception {
        Meal meal = mealService.get(1, USER_ID);
        assertMatch(meal, MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void getFromAnotherUser() throws Exception {
        mealService.get(1, ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        mealService.delete(MEAL3.getId(), USER_ID);
        assertMatch(mealService.getAll(USER_ID), MEAL5, MEAL4, MEAL2, MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        mealService.delete(123, USER_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> meals = mealService.getBetweenDates(
                        LocalDate.of(2015, 05,10),
                        LocalDate.of(2015,05,10),
                        USER_ID);
        assertMatch(meals, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> meals = mealService.getBetweenDateTimes(
                LocalDateTime.of(2015, 05,10, 10, 0),
                LocalDateTime.of(2015, 05,10, 16, 0),
                USER_ID);

        assertMatch(meals, MEAL2, MEAL1);
    }

    @Test
    public void getAll() throws Exception {
        mealService.getAll(USER_ID);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void create() throws Exception {
    }

}