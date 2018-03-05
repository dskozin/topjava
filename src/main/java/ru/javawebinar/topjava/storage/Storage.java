package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage<V> {

    public void save(V meal);

    public V get(Long mealId);

    public void delete(Long mealId);

    public void update(V meal);

    public List<V> getList();

    public void addAll(List<V> list);
}
