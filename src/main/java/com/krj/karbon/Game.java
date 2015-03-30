/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.karbon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jolley
 */
public class Game {

    private int appid;
    private String name;
    private String playtime_2weeks;
    private String playtime_forever;
    private String img_icon_url;
    private String img_logo_url;
    private List<Boolean> platforms;
    private List<String> categories;
    private int instances;

    public Game() {
        instances = 0;
        playtime_2weeks = "-1";
        name = "";
        platforms = new ArrayList<Boolean>();
        categories = new ArrayList<String>();
    }

    public void addCategory(String category) {
        categories.add(category);
    }
    
    public void addPlatform(Boolean bool) {
        platforms.add(bool);
    }

    public int getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaytime_2weeks() {
        return playtime_2weeks;
    }

    public void setPlaytime_2weeks(String playtime_2weeks) {
        this.playtime_2weeks = playtime_2weeks;
    }

    public String getPlaytime_forever() {
        return playtime_forever;
    }

    public void setPlaytime_forever(String playtime_forever) {
        this.playtime_forever = playtime_forever;
    }

    public String getImg_icon_url() {
        return img_icon_url;
    }

    public void setImg_icon_url(String img_icon_url) {
        this.img_icon_url = img_icon_url;
    }

    public String getImg_logo_url() {
        return img_logo_url;
    }

    public void setImg_logo_url(String img_logo_url) {
        this.img_logo_url = img_logo_url;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.appid);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (!Objects.equals(this.appid, other.appid)) {
            return false;
        }
        return true;
    }

}
