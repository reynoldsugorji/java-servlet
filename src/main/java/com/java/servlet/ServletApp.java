package com.java.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ServletApp extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("Lifecycle initialised!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write("<h1>My first Servlet App!</h1>");
    }

    @Override
    public void destroy() {
        System.out.println("Lifecycle Servlet destroyed");
    }
}
