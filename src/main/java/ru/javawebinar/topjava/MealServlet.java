
package ru.javawebinar.topjava;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final String LIST_MEALS = "/meals.jsp";
    private static final String MEALS = "meals";
    private static final int CALORIES = 2000;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private MealRepository<Meal> dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = new MealRepositoryImpl();
        super.init(config);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        int id = 0;
        String strId = request.getParameter("id");
        if (strId != null && !strId.isEmpty()) {
            id = Integer.parseInt(strId);
        }
        String description = request.getParameter("description");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("dateTime"), dateTimeFormatter);
        int calories = Integer.parseInt(request.getParameter("calories"));

        if (id == 0) {
            dao.add(new Meal(0, date, description, calories));
            log.debug("add new meal");
        } else {
            dao.update(id, new Meal(id, date, description, calories));
            log.debug("update meal with id = {}", id);
        }

        response.sendRedirect(MEALS);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action;
        if (request.getParameter("action") != null){
            action = request.getParameter("action");

        }
        else {
            action = "";
        }
        switch (action.toLowerCase()) {
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                log.debug("deleted = {}", id);
                response.sendRedirect(LIST_MEALS);
                break;
            }
            case "edit": {
                int mealId = Integer.parseInt(request.getParameter("id"));
                Meal meal = dao.getById(mealId);
                log.debug("edit meal with id = {}", mealId);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                break;

            }
            case "insert": {
                log.debug("forward to insert/edit");
                request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                break;
            }

            default: {
                request.setAttribute(MEALS, getAllMealsTo());
                log.debug("forward to meals");
                request.getRequestDispatcher(LIST_MEALS).forward(request, response);
            }
        }



        log.debug("redirect to meals");
        request.getRequestDispatcher("/meals.jsp")
            .forward(request, response);
    }
    private List<MealTo> getAllMealsTo() {
        return MealsUtil.filteredByStreams(dao.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES);
    }
}