package com.servlet.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    protected Connection getConnection() {
        Connection connection;
        try {
            //loads sql jdbc class at runtime
            Class.forName("com.mysql.cj.jdbc.Driver");

            String jdbcUrl = "jdbc:mysql://localhost:3306/employee_schema?useSSL=false&allowPublicKeyRetrieval=true";
            String jdbcUsername = "root";
            String jdbcPassword = "Password12";

            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void insertEmployeeData(EmployeeDTO employeeDTO) {
        final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee " +
                "(first_name, last_name, username, password, address, contact)" +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
            preparedStatement.setString(1, employeeDTO.getFirstName());
            preparedStatement.setString(2, employeeDTO.getLastName());
            preparedStatement.setString(3, employeeDTO.getUsername());
            preparedStatement.setString(4, employeeDTO.getPassword());
            preparedStatement.setString(5, employeeDTO.getAddress());
            preparedStatement.setString(6, employeeDTO.getContact());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EmployeeDTO> selectAllEmployees() {
        final String GET_ALL_EMPLOYEES_SQL = "SELECT * FROM employee;";
        List<EmployeeDTO> employees = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEES_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String username = resultSet.getString("username");
                String address = resultSet.getString("address");
                String contact = resultSet.getString("contact");

                employees.add(new EmployeeDTO(id, firstName, lastName, username, address, contact));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public EmployeeDTO getEmployee(int id) {
        final String GET_EMPLOYEE_SQL = "SELECT username, first_name, last_name, address, contact FROM employee WHERE id = ?;";
        EmployeeDTO employeeDTO = null;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String contact = resultSet.getString("contact");

                employeeDTO = new EmployeeDTO(id, firstName, lastName, username, address, contact);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeDTO;
    }

    public boolean updateEmployee(EmployeeDTO employee) throws SQLException {

        boolean isRowUpdated;
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, username = ?, password = ?, address = ?, contact = ? WHERE id = ?;";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getUsername());
            stmt.setString(4, employee.getPassword());
            stmt.setString(5, employee.getAddress());
            stmt.setString(6, employee.getContact());
            stmt.setInt(7, employee.getId());

            isRowUpdated = stmt.executeUpdate() > 0;
        }
        return isRowUpdated;
    }


    public boolean deleteEmployee(int id) {
        boolean isRowDeleted;
        final String DELETE_EMPLOYEE_SQL = "DELETE FROM employee WHERE id = ?;";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);
            preparedStatement.setInt(1, id);
            isRowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isRowDeleted;
    }
}
