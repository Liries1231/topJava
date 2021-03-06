package ru.javawebinar.topjava;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

public class UserServlet extends HttpServlet {
  private static final Logger log = getLogger(UserServlet.class);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    log.debug("redirect to users");

//        request.getRequestDispatcher("/users.jsp").forward(request, response);
    response.sendRedirect("user.jsp");
  }
}