/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.karbon;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
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
public class GetPlayerSummaries {

    private static String host = "http://api.steampowered.com";
    private static String path = "/ISteamUser/GetPlayerSummaries/v0002/";
    private static String key = "?key=06326BE3F53F72F8C6EF31C158FBACD7";

    /**
     * 
     * @param user
     * @param steamId
     * @return 
     */
    public static SteamAccount retrieve(SteamAccount user, String steamId) {
        try {
            
            //setup the steamId
            String id = "&steamids=" + steamId;

            //get the map of player info from the steamAPI
            List<Map> playerMaps = getPlayerMaps(id);

            //set the info from the map to the player
            for (Map map : playerMaps) {
                user = getUserInfo(user, map);
            }

        } catch (IOException ex) {
            Logger.getLogger(GetOwnedGames.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    /**
     * 
     * @param steamId
     * @return 
     */
    public static SteamAccount retrieve(String steamId) {
        SteamAccount user = new SteamAccount();

        try {
            //setup the steamId
            String id = "&steamids=" + steamId;
            
            //get the map of player info from the steamAPI
            List<Map> playerMaps = getPlayerMaps(id);

            //set the info from the map to the player
            for (Map map : playerMaps) {
                user = getUserInfo(user, map);
            }

        } catch (IOException ex) {
            Logger.getLogger(GetOwnedGames.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    /**
     *
     * @param steamIds
     * @return
     */
    public static List<SteamAccount> retrieve(List<String> steamIds) {
        List<SteamAccount> users = new ArrayList<>();

        try {

            //setup the steamIds
            String listIds = "";
            for (String steamId : steamIds) {
                listIds += steamId + ",";
            }
            String ids = "&steamids=" + listIds;

            //get the map of players info from the steamAPI
            List<Map> playerMaps = getPlayerMaps(ids);
            
            //set the info from the maps to the players
            for (Map map : playerMaps) {
                SteamAccount user = new SteamAccount();
                user = getUserInfo(user, map);
                users.add(user);
            }

        } catch (IOException ex) {
            Logger.getLogger(GetOwnedGames.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    /**
     *
     * @param user
     * @param map
     * @return
     */
    private static SteamAccount getUserInfo(SteamAccount user, Map map) {
        //get the userId
        String userid = (String) map.get("steamid");
        user.setSteamId(userid);

        //get the perosona name
        String personaname = (String) map.get("personaname");
        user.setPersonaname(personaname);

        //get the real name (this is private and may not be returned
        String realname = (String) map.get("realname");
        if (realname != null) {
            user.setRealname(realname);
        }

        //get the avatar image url
        String imgURL = (String) map.get("avatarfull");
        user.setAvatar(imgURL);

        //get the steam profile url
        String profileURL = (String) map.get("profileurl");
        user.setProfileURL(profileURL);
        return user;
    }

    /**
     *
     * @param ids
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    private static List<Map> getPlayerMaps(String ids)
            throws MalformedURLException, IOException {
        
        //build the url
        String urlString = host + path + key + ids;
        URL url = new URL(urlString);

        //fetch the JSON from the API and turn it into a map
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> JSONMap = mapper.readValue(url, Map.class);

        //descend into the response to get to the real data
        Map response = (Map) JSONMap.get("response");

        //get a list of maps containing the real data!
        List<Map> playerMaps = (List<Map>) response.get("players");
        return playerMaps;
    }

}
