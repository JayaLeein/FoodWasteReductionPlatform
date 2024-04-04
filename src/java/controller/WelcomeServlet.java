package controller;



import model.UserRegistration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author aaronthomp
 */
@WebServlet(name = "WelcomeServlet", urlPatterns = {"/WelcomeServlet"})
public class WelcomeServlet extends HttpServlet {
    UserRegistration userRegistration = new UserRegistration();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        response.sendRedirect(userRegistration.welcomePageRedirect(action));
        // idk how we want to handle exception/errors
    }
}