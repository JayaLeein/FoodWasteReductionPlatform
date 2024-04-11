package controller;

import businesslayer.ShowRetailersAtLocationBusinessLogic;
import dataaccesslayer.DataSource;
import dataaccesslayer.ItemDAOImpl;
import transferobjects.RetailerInventoryDTO;
import transferobjects.ItemDTO; // Assuming you have an ItemDTO class

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FetchInventoryServlet", urlPatterns = {"/FetchInventoryServlet"})
public class FetchInventoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int retailerId = Integer.parseInt(request.getParameter("retailerId"));
        ServletContext context = getServletContext();
        ShowRetailersAtLocationBusinessLogic logic = new ShowRetailersAtLocationBusinessLogic();

        try {
            Map<Integer, RetailerInventoryDTO> inventoryById = logic.getInventory(retailerId, context);
            Map<String, RetailerInventoryDTO> inventoryItemsMap = new HashMap<>();
            ArrayList<Integer> list = new ArrayList<>();
            DataSource dataSource = new DataSource(context);
            try (Connection connection = dataSource.getConnection()) {
                ItemDAOImpl itemDAO = new ItemDAOImpl(connection);
                inventoryById.forEach((itemId, inventoryDTO) -> {
                    ItemDTO item = itemDAO.getItemById(itemId);
                    int retailerInventoryid = inventoryDTO.getId();
                    list.add(retailerInventoryid);
                    inventoryItemsMap.put(item.getName()+","+inventoryDTO.getBatch(), inventoryDTO);
                });
            }

            request.setAttribute("inventoryItemsMap", inventoryItemsMap);

        } catch (SQLException e) {
            e.printStackTrace(); // Consider more graceful error handling
        }

        request.getRequestDispatcher("views/inventoryDisplay.jsp").forward(request, response);
    }
}