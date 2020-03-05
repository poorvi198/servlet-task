package com.studentfilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RollNoFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig=filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String rollno = httpRequest.getParameter("rollno");

        String regex = "RN[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rollno);
        if(matcher.matches())
        {
            filterConfig.getServletContext().log("Valid roll no.");
            filterChain.doFilter(servletRequest,servletResponse);
            //post preocessing

        }
        else
        {
            PrintWriter write = servletResponse.getWriter();
            filterConfig.getServletContext().log("Valid roll no.");
            write.println("Invalid rollno");
        }
    }

    @Override
    public void destroy() {

    }
}
