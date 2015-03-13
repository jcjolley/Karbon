/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.karbon;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author jolley
 */
@WebServlet(name = "steamAPIConnector", urlPatterns = {"/steamAPIConnector"})
public class steamAPIConnector extends HttpServlet {

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
        try {
            
            String userId = request.getParameter("steamAPIKey");
            
            //this builds our GET request for us
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("api.steampowered.com")
                    .setPath("/ISteamUser/GetPlayerSummaries/v0002/")
                    .setParameter("key", "06326BE3F53F72F8C6EF31C158FBACD7")
                    .setParameter("steamids", userId)
                    .build();
//          
            //The Web Service expects actual '+' signs instead of %2B
            String url = uri.toString();
            url = url.replace("%2B", "+");

            //This makes the git request for us
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            //this is the object we get back from the request
            String results = EntityUtils.toString(entity1);
            EntityUtils.consume(entity1);
            response1.close();
            
            
            JsonReader jsonReader = Json.createReader(new StringReader(results));
         
            //get JsonObject from JsonReader
            JsonObject jsonObject = jsonReader.readObject();

            //we can close IO resource and JsonReader now
            
            jsonReader.close();
            JsonObject APIResponse = jsonObject.getJsonObject("response");
            JsonArray jsonArray = APIResponse.getJsonArray("players");    
            JsonObject player = jsonArray.getJsonObject(0);
            
            String playerName = player.getString("personaname");
            String profileURL = player.getString("profileurl");
            String avatar =     player.getString("avatarfull");
            
            System.out.println(playerName);
            System.out.println(profileURL);
            System.out.println(avatar);
            
            request.getSession().setAttribute("playerName", playerName);
            request.getSession().setAttribute("profileURL", profileURL);
            request.getSession().setAttribute("avatar",     avatar);
            request.getSession().setAttribute("steamAPIResults", results);
            
            List<Game> games = GetOwnedGames.retrieve(userId);
            request.setAttribute("games", games);
            
            request.getRequestDispatcher("apiTest.jsp").forward(request, response);
            

        } catch (Exception ex) {
            ex.printStackTrace();

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
