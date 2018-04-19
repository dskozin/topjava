package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id and m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    Meal save(Meal meal);

    @Transactional
    @Modifying
    @Query("UPDATE Meal m SET m.dateTime = :#{#newMeal.dateTime}, m.calories= :#{#newMeal.calories}, m.description=:#{#newMeal.description} where m.id=:#{#newMeal.id} and m.user.id=:userId")
    int update(@Param("newMeal") Meal newMeal, @Param("userId") int userId);

    Optional<Meal> findByIdAndUser_id(Integer id, int userId);

    List<Meal> findAllByUser_Id(int id,Sort sort);

    List<Meal> findAllByUser_IdAndDateTimeBetween(int id, LocalDateTime startDate, LocalDateTime endDate, Sort sort);
}
