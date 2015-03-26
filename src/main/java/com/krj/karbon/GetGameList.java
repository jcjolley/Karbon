/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.karbon;

import com.google.gson.Gson;
import commentSystem.PostComparator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "GetGameList", urlPatterns = {"/GetGameList"})
public class GetGameList extends HttpServlet {

    private List<Game> gamesToBuy(SteamAccount user, List<SteamAccount> chosenFriends, String recent) {
        List<Game> games = new ArrayList<>();
        int count = 0;
        for (SteamAccount friend : chosenFriends) {
            for (Game game : friend.getGames()) {
                if (recent != null && recent.equals("1")) {
                    if (!user.getGames().contains(game) && Integer.parseInt(game.getPlaytime_2weeks()) > 1) {
                        if (games.contains(game)) {
                            int index = games.indexOf(game);
                            Game g = games.get(index);
                            g.setInstances(g.getInstances() + 1);
                            games.set(index, g);
                        } else {
                            game.setInstances(1);
                            games.add(game);
                        }
                    }
                } else {
                    if (!user.getGames().contains(game)) {
                        if (games.contains(game)) {
                            int index = games.indexOf(game);
                            Game g = games.get(index);
                            g.setInstances(g.getInstances() + 1);
                            games.set(index, g);
                        } else {
                            game.setInstances(1);
                            games.add(game);
                        }
                    }
                }
            }
        }

        Collections.sort(games, new GameComparator());
        return games;
    }

    private List<Game> gamesToPlay(SteamAccount user, List<SteamAccount> chosenFriends, String recent) {
        List<Game> games = new ArrayList<>();
        int count = 0;
        for (SteamAccount friend : chosenFriends) {
            for (Game game : friend.getGames()) {
                if (recent != null && recent.equals("1")) {
                    if (user.getGames().contains(game) && Integer.parseInt(game.getPlaytime_2weeks()) > 1) {
                        if (games.contains(game)) {
                            int index = games.indexOf(game);
                            Game g = games.get(index);
                            g.setInstances(g.getInstances() + 1);
                            games.set(index, g);
                        } else {
                            game.setInstances(1);
                            games.add(game);
                        }
                    }
                } else {
                    if (user.getGames().contains(game)) {
                        if (games.contains(game)) {
                            int index = games.indexOf(game);
                            Game g = games.get(index);
                            g.setInstances(g.getInstances() + 1);
                            games.set(index, g);
                        } else {
                            game.setInstances(1);
                            games.add(game);
                        }
                    }
                }
            }
        }

        Collections.sort(games, new GameComparator());
        return games;
    }

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

        //get the friendlist
        SteamAccount user = (SteamAccount) request.getSession().getAttribute("user");
        List<SteamAccount> allFriends = null;
        if (user != null) {
            allFriends = user.getFriends();
        }
        List<Game> gamesToBuy = gamesToBuy(user, allFriends,"");
        List<Game> gamesToPlay = gamesToPlay(user, allFriends,"");
        
        user.setGamesToBuy(gamesToBuy);
        user.setGamesToPlay(gamesToPlay);
        Gson gson = new Gson();
        String userString = gson.toJson(user);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println(userString);
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
