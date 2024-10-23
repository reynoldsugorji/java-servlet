package com.servlet.jdbc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EmployeeServlet.class.getName());

    private EmployeeDao employeeDao;

    @Override
    public void init() {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if ("update".equals(action)) {
            try {
                updateEmployee(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            insertEmployee(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "new":
                newEmployeeForm(req, resp);
                break;
            case "create":
                insertEmployee(req, resp);
                break;
            case "delete":
                deleteEmployee(req, resp);
                break;
            case "edit":
                showUpdateEmployeeForm(req, resp);
                break;
            case "update":
                try {
                    updateEmployee(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "details":
                getEmployeeById(req, resp);
                break;
            default:
                getEmployees(req, resp);
                break;
        }

    }

    private void getEmployees(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<EmployeeDTO> employees = employeeDao.selectAllEmployees();

        request.setAttribute("employees", employees);
        request.getRequestDispatcher("employeeList.jsp").forward(request, response);
    }

    private void newEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registerForm.jsp").forward(request, response);
    }

    private void insertEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String contact = req.getParameter("contact");

        EmployeeDTO newEmployee = new EmployeeDTO(firstName, lastName, username, password, address, contact);
        employeeDao.insertEmployeeData(newEmployee);

        resp.sendRedirect("employeeList.jsp");

    }

    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));

        String password = req.getParameter("password");
        if (password.isEmpty()) {

            EmployeeDTO existingEmployee = employeeDao.getEmployee(id);
            password = existingEmployee.getPassword();
        }

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");
        String address = req.getParameter("address");
        String contact = req.getParameter("contact");


        EmployeeDTO employeeDTO = new EmployeeDTO(id, firstName, lastName, username, password, address, contact);
        boolean isUpdated = employeeDao.updateEmployee(employeeDTO);
        if (isUpdated) {
            resp.sendRedirect("employee?action=list");

        } else {
            resp.getWriter().write("Failed to update employee.");
        }
    }

    private void showUpdateEmployeeForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        EmployeeDTO existingEmployee = employeeDao.getEmployee(id);

        req.setAttribute("employee", existingEmployee);
        req.getRequestDispatcher("updateEmployee.jsp").forward(req, resp);
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        boolean isRowDeleted = employeeDao.deleteEmployee(id);
        if (isRowDeleted) {
            resp.sendRedirect("employee");

        }
    }

    private void getEmployeeById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // Extract the employee id from the URL
        EmployeeDTO employee = employeeDao.getEmployee(id); // Retrieve employee data from the database

        request.setAttribute("employee", employee); // Add the employee data to the request
        request.getRequestDispatcher("employeeDetails.jsp").forward(request, response); // Forward the request to the JSP page
    }

}
