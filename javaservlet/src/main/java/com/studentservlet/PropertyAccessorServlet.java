package com.studentservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class PropertyAccessorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties prop = (Properties) getServletConfig().getServletContext().getAttribute("properties");
        getServletContext().log(prop.getProperty("x"));
        getServletContext().log(prop.getProperty("y"));
        resp.getWriter().write(prop.getProperty("x")+" "+prop.getProperty("y"));
    }
}
