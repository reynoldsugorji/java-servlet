package com.java.servletJson;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user  = new User();
        user.setId(100L);
        user.setFirstName("Reynolds");
        user.setLastName("Snow");
        user.setCreatedAt(new Date());
        user.setCreatedBy("Admin");
        user.setEmailId("reynolds@gmail.com");
        user.setUpdatedAt(new Date());
        user.setUpdatedBy("Admin");

        String userJsonObject = gson.toJson(user);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(userJsonObject);
    }
}
