package com.studentservlet;

import com.student.Student;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GetStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context =  getServletContext();
        boolean isStudentFind=false;

        List list = (List)context.getAttribute("studentList");
        PrintWriter writer = resp.getWriter();


        String rollNo = req.getParameter("rollno");
        Iterator<Student> iterator = list.iterator();
        while(iterator.hasNext())
        {
            Student student = iterator.next();
            if(student.getRollNo().equals(rollNo))
            {
                req.setAttribute("Student",student);
                isStudentFind = true;
                getServletContext().log("Student found: "+student);
                break;
            }
        }

        if(!isStudentFind)
        {
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<p>No student found</p>");
            writer.println("</body>");
            writer.println("</html>");
            getServletContext().log("Student not found: ");
        }

    }
}
