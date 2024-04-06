package model;


import transferobjects.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInValidation {
    public String logInPageRedirect(String action, String role) {
        if (action.equals("Sign in")) {
            return switch (role) {
                case "Customer" -> "views/CustomerView.jsp";
                case "Retailer" -> "views/RetailerView.jsp";
                case "Charity" -> "views/CharityView.jsp";
                default -> "views/SignInError.jsp";
            };
        } else if (action.equals("Sign up")) {
            return "views/register/role_selection.jsp";
        } else {
            throw new IllegalArgumentException("Error: Unexpected outcome");
            // if we have a different action, we can redirect to an error page
        }
    }

    public UserDTO getUserRoleAndId(String username, String password, Connection con) {
        String sql = "SELECT id, role FROM users WHERE name = ? AND password = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if any rows were returned
                if (rs.next()) {
                    UserDTO user = new UserDTO();
                    user.setId(rs.getInt("id"));
                    user.setRole(rs.getString("role"));
                    return user;
                } else {
                    // No user found with provided credentials
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}







