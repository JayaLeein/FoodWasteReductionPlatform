<%-- 
    Document   : Customer_registration
    Created on : Mar 27, 2024, 8:12:59 PM
    Author     : Andres Porras
--%>

<%@ page import="dataaccesslayer.DataSource" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.ServletContext" %>

<%
    // Instantiate DataSource
    ServletContext context = pageContext.getServletContext();
    DataSource dataSource = new DataSource(context);

// Establish database connection
    Connection connection = null;
    try {
        connection = dataSource.getConnection();

        // Query database
        String query = "SELECT DISTINCT location FROM users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Customer Registration Form</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
    </head>
    <body class = "registration">

    <div class="logoblack-container">
        <a href="${pageContext.request.contextPath}">
        <img src="${pageContext.request.contextPath}/image/logo_black.png" alt="Logo"></a>
    </div>

    <div class="container">
    <h1>Customer Registration Form</h1>
            <form action="${pageContext.request.contextPath}/ValidateRegistrationServlet" method="post">
    <div class="hidden">
        <label for="role" hidden>Role:</label>
        <input type="text" id="role" value="Retailer" hidden>
    </div>
                <label for="name">*Name :</label><br>
                <input type="text" id="name" name="name" required><br>
                <label for="email">*Email :</label><br>
                <input type="email" id="email" name="email" required><br>
                <label for="password">*Password :</label><br>
                <input type="password" id="password" name="password" required><br>
                <label for="phone">*Phone :</label><br>
                <input type="text" id="phone" name="phone" required><br>
                <label for="dropdown">Select your nearest store :</label>
                <br>
                <select id="dropdown" name="selectedValue">

                        <option value="Nepean">Nepean</option>
                        <option value="Kanata">Kanata</option>
                        <option value="Barrhaven">Barrhaven</option>
                        <option value="Downtown">Downtown</option>

                </select><br><br>
                <label for="preference">Select your prefered category</label><br>
                <select id="preference" name="preference">
                    <option value="Fruit">Fruit</option>
                    <option value="Vegetable">Vegetable</option>
                    <option value="Meat">Meat</option>
                    <option value="Seafood">Seafood</option>
                    <option value="Dairy & Eggs">Dairy & Eggs</option>
                </select><br>
                <input type="checkbox" id="subscribeToPhone" name="subscribeToPhone" value="true">
                <label for="subscribeToPhone">Get notifications by Phone</label><br>
                <input type="checkbox" id="subscribeToMail" name="subscribeToMail" value="true">
                <label for="subscribeToMail">Get notifications by Mail</label><br>

                <div style="text-align: right; background-color: white;">
                <input type="submit" value="Register" >
                </div>
            </form>
         </div>
    </body>
</html>
<%
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close database connection
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
%>