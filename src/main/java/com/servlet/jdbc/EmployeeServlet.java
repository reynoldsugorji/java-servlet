package com.servlet.jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {

    private EmployeeDao employeeDao;
    @Override
    public void init() throws ServletException {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String contact = req.getParameter("contact");

        EmployeeDTO newEmployee = new EmployeeDTO(firstName, lastName, username, password, address, contact);
        employeeDao.insertEmployeeData(newEmployee);

        resp.sendRedirect("employeeDetails.html");
    }
}
