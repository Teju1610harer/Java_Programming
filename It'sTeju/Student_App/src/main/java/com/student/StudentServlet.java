package com.student;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Get data from form (IMPORTANT CHANGE)
        String prn = request.getParameter("prn");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String[] subjects = request.getParameterValues("subjects");

        // ✅ Validation
        if (subjects == null || subjects.length != 3) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<h3 style='color:red;'>Select exactly 3 subjects!</h3>");
            out.println("<a href='index.html'>Go Back</a>");
            return;
        }

        // ✅ Pass data
        request.setAttribute("prn", prn);
        request.setAttribute("name", name);
        request.setAttribute("dob", dob);
        request.setAttribute("gender", gender);
        request.setAttribute("subjects", subjects);

        // ✅ Forward
        RequestDispatcher rd = request.getRequestDispatcher("DisplayServlet");
        rd.forward(request, response);
    }
}