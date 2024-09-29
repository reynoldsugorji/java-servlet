package com.java.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {
    private List<String> feedbackList;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        String feedback = req.getParameter("feedback");
        if (feedbackList != null && !feedbackList.isEmpty()) {
            feedbackList.add(feedback);
        }
        resp.setContentType("text/html");
        writer.write("<h1>Feedback List</h1>");
        for (String list : feedbackList) {
            writer.write("<li>" + list + "</li>");
        }
    }

    @Override
    public void destroy() {
        feedbackList = null;
    }

}
