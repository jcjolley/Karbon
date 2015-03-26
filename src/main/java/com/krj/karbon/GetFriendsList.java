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
public class GetFriendsList {

    public static List<SteamAccount> retrieve(String steamId) {
        String urlString = "";
        List<SteamAccount> friends = new ArrayList();
        try {
            //http://api.steampowered.com/ISteamUser/GetFriendList/v0001/?key=06326BE3F53F72F8C6EF31C158FBACD7&steamid=76561197976892493&relationship=all
            String host = "http://api.steampowered.com/";
            String path = "ISteamUser/GetFriendList/v0001/";
            String key = "?key=06326BE3F53F72F8C6EF31C158FBACD7";
            String id = "&steamid=" + steamId;
            String relationship = "&relationship=all";
            urlString = host + path + key + id + relationship;

            URL url = new URL(urlString);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> JSONMap = mapper.readValue(url, Map.class);

            Map response = (Map) JSONMap.get("friendslist");

            if (response != null) {
                List<Map> friendMaps = (List<Map>) response.get("friends");
                if (friendMaps != null) {
                    for (Map map : friendMaps) {

                        String steamid = (String) map.get("steamid");
                        SteamAccount friend = GetPlayerSummaries.retrieve(steamid);

                        friend.getOwnedGames();
                        friends.add(friend);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GetOwnedGames.class.getName()).log(Level.SEVERE, null, ex);
        }

        return friends;
    }
}
