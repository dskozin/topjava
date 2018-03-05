package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.*;

public class MapStorageMeal extends AbstractStorage<Meal> {

    private volatile static Long idCount = 0L;
    private final Map<Long, Meal> storage = new HashMap<>();

    @Override
    Meal getFromStorage(Long mealID) {
        if(!storage.containsKey(mealID))
            return null;

        return storage.get(mealID);
    }

    @Override
    boolean deleteFromStorage(Long mealID) {
        if(!storage.containsKey(mealID))
            return false;

        storage.remove(mealID);
        return true;
    }

    @Override
    boolean updateInStorage(Meal meal) {
        if(!storage.containsKey(meal.getId()))
            return false;

        storage.put(meal.getId(), meal);
        return true;
    }

    @Override
    boolean saveToStorage(Meal meal) {
        meal.setId(MapStorageMeal.getNewId());
        storage.put(meal.getId(), meal);
        return true;
    }

    @Override
    List<Meal> getStorageList() {
        List<Meal> meals = new ArrayList<>(storage.values());
        meals.sort((o1, o2) -> o1.getDateTime().isAfter(o2.getDateTime()) ? 1 : -1);
        return meals;
    }

    @Override
    void storageAddAll(List<Meal> list) {
        list.forEach(this::saveToStorage);
    }


    private synchronized static Long getNewId() {
        idCount++;
        return idCount;
    }
}
