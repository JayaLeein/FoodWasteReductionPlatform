package controller;

import dataaccesslayer.DataSource;
import model.RetailerInventoryWorker;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This servlet is used to determine the surplus choice of a retailer
 */
@WebServlet(name = "SurplusChoiceServlet", urlPatterns = {"/SurplusChoiceServlet"})
public class SurplusChoiceServlet extends HttpServlet {
    DataSource dataSource;
    RetailerInventoryWorker worker = new RetailerInventoryWorker();

    /**
     * Initializes the servlet. used throughout most servlets to instantiate the datasource
     * @throws ServletException if there is a servlet error
     */
    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext context = getServletContext();
        dataSource = new DataSource(context);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int itemId = (int) request.getSession().getAttribute("itemId");
        //based on what button is clicked we will set the flags in the database for sale or donation
        try (Connection connection = dataSource.getConnection()) {
            switch (action) {
                case "Sale" -> worker.updateInventoryFlags(connection, itemId, true, false);
                case "Donation" -> worker.updateInventoryFlags(connection, itemId, false, true);
                case "Both" -> worker.updateInventoryFlags(connection, itemId, true, true);
            }
        } catch(SQLException e) {
            throw new ServletException(e);
        } // send the user back to the RetailerView page
        response.sendRedirect("views/RetailerView.jsp");
    }
}
