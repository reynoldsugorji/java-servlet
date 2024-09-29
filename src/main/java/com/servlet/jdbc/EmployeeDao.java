package com.servlet.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    protected Connection getConnection(){
        Connection connection = null;
        try {
            //loads sql jdbc class at runtime
            Class.forName("com.mysql.cj.jdbc.Driver");

            String jdbcUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
            String jdbcUsername = "admin";
            String jdbcPassword = "password";

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

        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPLOYEES_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
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

        try{
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

                 employeeDTO = new EmployeeDTO(id, firstName, lastName, username, address, contact );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeDTO;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employee) {
        final String UPDATE_EMPLOYEE_SQL = "UPDATE employee SET username=?, first_name=?, last_name=?, address=?, contact=? " +
                "WHERE id = ?;";
        EmployeeDTO updatedEmployee = null;
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setString(5, employee.getContact());
            preparedStatement.setInt(6, employee.getId());
           int affectedRows = preparedStatement.executeUpdate();

           if(affectedRows > 0) {
               updatedEmployee = employee;
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedEmployee;
    }
}
