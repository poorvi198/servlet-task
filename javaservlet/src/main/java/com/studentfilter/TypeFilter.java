package com.studentfilter;

import com.convertor.JSONConvertor;
import com.convertor.XMLConvertor;
import com.student.Student;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Writer;

public class TypeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        filterChain.doFilter(servletRequest,servletResponse);
        //post processing
        Writer writer = httpServletResponse.getWriter();
        Student student = (Student) httpServletRequest.getAttribute("Student");
        if(httpServletRequest.getHeader("Accept").equals("application/json")){
            httpServletResponse.setContentType("application/json");
            String studentJson = new JSONConvertor().convert(student);
           writer.write(studentJson);
        }
        else
        {
            httpServletResponse.setContentType("application/xml");
            String studentXml = new XMLConvertor().convert(student);
            writer.write(studentXml);
        }
    }



    @Override
    public void destroy() {

    }
}
