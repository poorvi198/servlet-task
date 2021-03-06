package com.studentfilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //String value = filterConfig.getInitParameter("name");
        String name = httpServletRequest.getParameter("name");
        String regex = "[a-zA-Z]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches())
        {
            filterConfig.getServletContext().log("Vaild name");
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else
        {
            PrintWriter write = servletResponse.getWriter();
            write.println("Invalid name");
        }

    }

    @Override
    public void destroy() {

    }
}
