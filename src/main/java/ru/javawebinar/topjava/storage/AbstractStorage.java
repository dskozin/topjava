package ru.javawebinar.topjava.storage;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractStorage<V> implements Storage<V>{
    private static final Logger log = getLogger(Storage.class);

    abstract V getFromStorage(Long mealID);
    abstract boolean deleteFromStorage(Long mealID);
    abstract boolean updateInStorage(V meal);
    abstract boolean saveToStorage(V meal);
    abstract List getStorageList();
    abstract void storageAddAll(List<V> list);

    @Override
    public void save(V meal) {
        if(!saveToStorage(meal))
            throw new RuntimeException();
    }

    @Override
    public V get(Long mealId){
        V meal;
        if ((meal = getFromStorage(mealId)) == null)
            throw new RuntimeException();

        return meal;
    }

    @Override
    public void delete(Long mealId) {
        if (!deleteFromStorage(mealId))
            throw new RuntimeException();
    }

    @Override
    public void update(V meal) {
        if(!updateInStorage(meal))
            throw new RuntimeException();
    }

    @Override
    public List<V> getList() {
        return getStorageList();
    }

    @Override
    public void addAll(List<V> list) {
        storageAddAll(list);
    }
}
