/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.karbon;

import java.io.Serializable;

/**
 *
 * @author jolley
 */
public class Game {
    private String appid;
    private String name;
    private String playtime_2weeks;
    private String playtime_forever;
    private String img_icon_url;
    private String img_logo_url;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
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

}
