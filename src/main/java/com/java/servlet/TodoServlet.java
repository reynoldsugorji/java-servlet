package com.java.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
    private static final long serialVersionUid = 1L;
    private final Map<String, String> todos = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        todos.put(title, description);
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchTitle = req.getParameter("searchTitle");
        String todoDescription = todos.get(searchTitle);

        //allows data to be saved to a req which can be passed to another servlet or jsp page
        req.setAttribute("todoDescription", todoDescription);
        req.getRequestDispatcher("todoDetails.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
