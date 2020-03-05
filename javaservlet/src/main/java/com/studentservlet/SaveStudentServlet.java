package com.studentservlet;

import com.student.Student;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

public class SaveStudentServlet extends HttpServlet {
    private List<Student> list = new ArrayList();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String rollNo = req.getParameter("rollno");
        String name = req.getParameter("name");
        String university = req.getParameter("university");
        Student student = new Student();
        student.setRollNo(rollNo);
        student.setName(name);
        student.setUniversity(university);
        list.add(student);
        ServletContext context =  getServletContext();
        context.setAttribute("studentList",list);
        PrintWriter writer = resp.getWriter();
        Iterator iterator = list.iterator();

        while(iterator.hasNext()) {

            writer.print((Student)iterator.next());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

    }
}
