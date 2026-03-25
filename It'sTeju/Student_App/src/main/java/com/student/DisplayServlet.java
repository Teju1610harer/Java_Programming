package com.student;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class DisplayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // ✅ getParameter reads directly from HTML form
        String prn     = request.getParameter("prn");
        String name    = request.getParameter("name");
        String dob     = request.getParameter("dob");
        String gender  = request.getParameter("gender");
        String[] subjects = request.getParameterValues("subjects"); // ✅ for checkboxes

        out.println("<html><head>");
        out.println("<style>");
        out.println("body { font-family: Arial; background:#f2f2f2; display:flex; justify-content:center; align-items:center; height:100vh; margin:0; }");
        out.println(".box { width:400px; background:white; padding:20px; border-radius:10px; box-shadow:0 0 10px gray; }");
        out.println("table { width:100%; border-collapse:collapse; }");
        out.println("td { padding:8px; border:1px solid #ddd; }");
        out.println("h2 { text-align:center; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='box'>");
        out.println("<h2>Student Details</h2>");
        out.println("<table>");
        out.println("<tr><td>PRN</td><td>" + prn + "</td></tr>");
        out.println("<tr><td>Name</td><td>" + name + "</td></tr>");
        out.println("<tr><td>DOB</td><td>" + dob + "</td></tr>");
        out.println("<tr><td>Gender</td><td>" + gender + "</td></tr>");
        out.println("<tr><td>Subjects</td><td>");
        if (subjects != null) {
            for (String sub : subjects) {
                out.println(sub + "<br>");
            }
        }
        out.println("</td></tr>");
        out.println("</table>");
        out.println("</div>");
        out.println("</body></html>");
    }
}