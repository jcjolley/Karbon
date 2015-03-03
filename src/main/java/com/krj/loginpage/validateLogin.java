/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.loginpage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jolley
 */
@WebServlet(name = "validateLogin", urlPatterns = {"/validateLogin"})
public class validateLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            File file;
            file = new File("credentials.txt");
            Map credentials = new HashMap();


            if (!file.exists())
            {
                file.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(file));

                out.write("Jolley test");
                out.newLine();
                out.write("Admin password");
                out.close();
            }

            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null)
            {
                String[] parts = line.split(" ");
                credentials.put(parts[0], parts[1]);
            }


            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (credentials.get(username).equals(password))
            {
                request.getSession().setAttribute("username", username);
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("loginError", "Invalid Login, please try again");
                request.getRequestDispatcher("login.jsp").forward(request, response);   
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            request.setAttribute("exceptionMsg" , "We threw the exception: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
