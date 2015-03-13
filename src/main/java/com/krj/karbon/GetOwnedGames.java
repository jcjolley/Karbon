/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.karbon;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jolley
 */
public class GetOwnedGames {
    
    public static List<Game> retrieve(String steamId)
    {
        String urlString = "";
        List<Game> games = new ArrayList();
        try {
            String host = "http://api.steampowered.com/";
            String path = "IPlayerService/GetOwnedGames/v0001/";
            String key = "?key=06326BE3F53F72F8C6EF31C158FBACD7";
            String id = "&steamid=" + steamId;
            String include = "&include_appinfo=1";
            String format = "&format=json";
            urlString = host + path + key + id + include + format;
           
            URL url = new URL(urlString);
            
            //This fails.
            //TODO: do it manually and see what went wrong
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> JSONMap = mapper.readValue(url, Map.class);
    
            Map response = (Map)JSONMap.get("response");
      
            List<Map> gameMaps = (List<Map>)response.get("games");
            for (Map map : gameMaps)
            {
                Game game = new Game();
                int appid = (int)map.get("appid");
                
                game.setAppid(String.valueOf(appid));
                
                String name = (String)map.get("name");
                game.setName(name);
                
                String imgURL = ((String)map.get("img_icon_url"));
                imgURL = "http://media.steampowered.com/steamcommunity/public/images/apps/"
                         + game.getAppid() + imgURL + ".jpg";
                game.setImg_icon_url(imgURL);
                
                
                imgURL = (String)map.get("img_logo_url");
                imgURL = "http://media.steampowered.com/steamcommunity/public/images/apps/"
                         + game.getAppid() + "/" + imgURL + ".jpg";
                game.setImg_logo_url(imgURL);
                games.add(game);
            }
        
        } catch (IOException ex) {
            Logger.getLogger(GetOwnedGames.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return games;
    }
}