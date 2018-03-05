package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.storage.MapStorageMeal;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final Storage<Meal> STORAGE = new MapStorageMeal();

    @Override
    public void init() throws ServletException {
        super.init();
        STORAGE.addAll(MealsUtil.getList());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");;
        String id = request.getParameter("id");

        if (id != null && "edit".equals(action)) {
            Meal meal = STORAGE.get(Long.parseLong(id));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("/mealedit.jsp").forward(request, response);
            return;
        } else if(id == null && "edit".equals(action)) {
            request.setAttribute("meal", Meal.EMPTY);
            request.getRequestDispatcher("/mealedit.jsp").forward(request, response);
            return;
        } else if(id != null && "delete".equals(action)){
            STORAGE.delete(Long.parseLong(request.getParameter("id")));
        }

        List<MealWithExceed> meals = MealsUtil.getWithExceeded(STORAGE.getList(), MealsUtil.CALORIES_LIMIT);
        request.setAttribute("meals", meals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Save meal");
        request.setCharacterEncoding("UTF-8");

        LocalDateTime localDate = LocalDateTime.parse(request.getParameter("date_time"));
        String idString = request.getParameter("id");
        String description = request.getParameter("description");
        Integer calories = request.getParameter("calories") == null
                ? 0
                : Integer.parseInt(request.getParameter("calories"));

        if (idString == null || idString.equals("")) {
            Meal meal = new Meal(localDate, description, calories);
            STORAGE.save(meal);
        } else {
            Long id = Long.parseLong(idString);
            Meal meal = new Meal(localDate, description, calories, id);
            STORAGE.update(meal);
        }

        response.sendRedirect("meals");
    }
}
