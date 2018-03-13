package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            getOrCreateMapForUser().put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return getOrCreateMapForUser().computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        Map<Integer, Meal> mealMap = getOrCreateMapForUser();
        if (mealMap.containsKey(id)) {
            mealMap.remove(id);
            return true;
        }

        return false;
    }

    @Override
    public Meal get(int id) {
        return getOrCreateMapForUser().getOrDefault(id, null);
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> meals = new ArrayList<>(getOrCreateMapForUser().values());
        meals.sort(Comparator.comparing(Meal::getDateTime).reversed());
        return meals;
    }

    private Map<Integer, Meal> getOrCreateMapForUser() {
        Integer userId = AuthorizedUser.id();
        if (!repository.containsKey(userId))
            repository.put(userId, new HashMap<>());

        return repository.get(userId);
    }
}

