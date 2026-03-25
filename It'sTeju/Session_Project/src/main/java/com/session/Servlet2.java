package com.session;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Servlet2 extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        HttpSession session = req.getSession();

        String name = (String) session.getAttribute("Username");
        String dcn = (String) session.getAttribute("DCN");
        String se = (String) session.getAttribute("SE");
        String wt = (String) session.getAttribute("WT");

        String javaMarks = req.getParameter("Java");

        // 🔴 Validation
        if (javaMarks == null || javaMarks.trim().isEmpty()) {
            out.println("Please enter Java marks");
            return;
        }

        // 🔥 Calculation
        int total = Integer.parseInt(dcn)
                  + Integer.parseInt(se)
                  + Integer.parseInt(wt)
                  + Integer.parseInt(javaMarks);

        double percentage = total / 4.0;

        // 🎯 Grade logic
        String grade;
        if (percentage >= 75) grade = "A";
        else if (percentage >= 60) grade = "B";
        else if (percentage >= 50) grade = "C";
        else grade = "Fail";

        // 🎨 UI
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");

        out.println("<style>");
        out.println("body{font-family:Arial;background:white;}");
        out.println(".box{width:400px;margin:50px auto;padding:20px;border:2px solid black;}");
        out.println("h2{text-align:center;}");
        out.println("p{font-size:16px;margin:8px 0;}");
        out.println(".pass{color:green;font-weight:bold;}");
        out.println(".fail{color:red;font-weight:bold;}");
        out.println("button{margin-top:15px;padding:8px 15px;background:black;color:white;border:none;}");
        out.println("@media print { button{display:none;} }");
        out.println("</style>");

        out.println("</head><body>");

        out.println("<div class='box'>");

        out.println("<h2>Student Result</h2>");
        out.println("<hr>");

        out.println("<p><b>Username:</b> " + name + "</p>");
        out.println("<p>DCN Marks: " + dcn + "</p>");
        out.println("<p>SE Marks: " + se + "</p>");
        out.println("<p>WT Marks: " + wt + "</p>");
        out.println("<p>Java Marks: " + javaMarks + "</p>");

        out.println("<hr>");

        out.println("<p><b>Total:</b> " + total + "</p>");
        out.println("<p><b>Percentage:</b> " + percentage + "%</p>");

        // 🎯 Pass/Fail Color
        if (grade.equals("Fail")) {
            out.println("<p class='fail'>Grade: " + grade + "</p>");
        } else {
            out.println("<p class='pass'>Grade: " + grade + "</p>");
        }

        // 🖨 Print Button
        out.println("<button onclick='window.print()'>Print</button>");

        out.println("</div>");
        out.println("</body></html>");
    }
}