/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.karbon;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jolley
 */
public class SteamAccount {

    private String steamId;
    private List<Game> games;
    private List<SteamAccount> friends;
    private String avatar;
    private String personaname;
    private String realname;
    private String profileURL;
    private List<Game> gamesToPlay;
    private List<Game> gamesToBuy;

    public List<Game> getGamesToPlay() {
        return gamesToPlay;
    }

    public void setGamesToPlay(List<Game> gamesToPlay) {
        this.gamesToPlay = gamesToPlay;
    }

    public List<Game> getGamesToBuy() {
        return gamesToBuy;
    }

    public void setGamesToBuy(List<Game> gamesToBuy) {
        this.gamesToBuy = gamesToBuy;
    }

    
    
    public void getOwnedGames() {
        games = GetOwnedGames.retrieve(steamId);
    }

    public List<Game> whatToPlay() {
        List<Game> gamesToPlay = new ArrayList<>();
        return gamesToPlay;

    }

    public List<Game> whatToPlay(List<SteamAccount> friends) {
        List<Game> gamesToPlay = new ArrayList<>();
        return gamesToPlay;

    }
    
    public List<Game> whatToBuy() {
        List<Game> gamesToBuy = new ArrayList<>();
        return gamesToBuy;
    }

    public List<Game> whatToBuy(List<SteamAccount> friends) {
        List<Game> gamesToBuy = new ArrayList<>();
        return gamesToBuy;
    }
    
    public void setupFriends() {
        friends = GetFriendsList.retrieve(steamId);
    }

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<SteamAccount> getFriends() {
        return friends;
    }

    public void setFriends(List<SteamAccount> friends) {
        this.friends = friends;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPersonaname() {
        return personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

}
