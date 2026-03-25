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

        // 🔴 Validation
        if(name == null || dcn == null || se == null || wt == null){
            out.println("Enter all details");
            return;
        }

        out.println("<html><head><meta charset='UTF-8'>");

        out.println("<style>");
        out.println("body{font-family:Arial;background:#f5f5f5;}");
        out.println(".box{width:400px;margin:80px auto;padding:25px;border:2px solid black;background:white;}");
        out.println("input{width:100%;padding:8px;margin:10px 0;border:1px solid black;}");
        out.println("button{width:100%;padding:10px;background:black;color:white;border:none;}");
        out.println("</style></head><body>");

        out.println("<div class='box'>");
        out.println("<h3 style='text-align:center;'>Enter Java Marks</h3>");

        out.println("<form action='Servlet2' method='post'>");

        // 🔥 Hidden fields
        out.println("<input type='hidden' name='Username' value='" + name + "'>");
        out.println("<input type='hidden' name='DCN' value='" + dcn + "'>");
        out.println("<input type='hidden' name='SE' value='" + se + "'>");
        out.println("<input type='hidden' name='WT' value='" + wt + "'>");

        out.println("<input type='number' name='Java' placeholder='Enter Java Marks' required>");
        out.println("<button type='submit'>Calculate</button>");

        out.println("</form>");
        out.println("</div></body></html>");
    }
}