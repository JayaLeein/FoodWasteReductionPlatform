    <%--
        Document   : role_selection
        Created on : Mar 27, 2024, 8:16:34 PM
        Author     : camil
    --%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Select User Type</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="http://localhost:8080/FoodWasteReductionPlatform/style/style.css" type="text/css" rel="stylesheet">
    </head>

    <body>

        <div class="logoblack-container">
            <a href="http://localhost:8080/FoodWasteReductionPlatform/">
            <img src="http://localhost:8080/FoodWasteReductionPlatform/image/logo_black.png" alt="Logo"></a>
        </div>

    <div class = "roleselection">
    <form action="${pageContext.request.contextPath}/SelectUserTypeServlet" method="POST">
        <label for="userType">Select the type of user</label><br>
        <select name="userType" id="userType" class="select-dropdown">
            <option value="Customer">Customer</option>
            <option value="Retailer">Retailer</option>
            <option value="Charity">Charity/Food Bank</option>
        </select>
        <br>
        <input type="submit" value="Submit">
    </form>
    </div>
    </body>
    </html>

