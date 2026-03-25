package com.session;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Servlet1 extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String name = req.getParameter("Username");
        String dcn = req.getParameter("DCN");
        String se = req.getParameter("SE");
        String wt = req.getParameter("WT");

        // 🔥 Create Session
        HttpSession session = req.getSession();

        session.setAttribute("Username", name);
        session.setAttribute("DCN", dcn);
        session.setAttribute("SE", se);
        session.setAttribute("WT", wt);

        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");

        out.println("<style>");
        out.println("body{font-family:Arial;background:#f5f5f5;}");
        out.println(".box{width:400px;margin:80px auto;padding:25px;border:2px solid black;background:white;}");
        out.println("input{width:100%;padding:8px;margin-top:5px;margin-bottom:15px;border:1px solid black;}");
        out.println("button{width:100%;padding:10px;background:black;color:white;border:none;}");
        out.println("</style>");

        out.println("</head><body>");

        out.println("<div class='box'>");
        out.println("<h2 style='text-align:center;'>Enter Java Marks</h2>");

        out.println("<form action='Servlet2' method='post'>");

        out.println("<label>Java Marks</label>");
        out.println("<input type='number' name='Java' required>");

        out.println("<button type='submit'>Calculate</button>");

        out.println("</form>");
        out.println("</div>");

        out.println("</body></html>");
    }
}