package com.example.weilong.gankio.dao;

import java.io.Serializable;

/**
 * Created by weilong on 2017/1/8.
 */

public class User implements Serializable{
    private String name;
    private String url;
    private boolean isLongin;

    public User(String name,String url){
        this.name=name;
        this.url=url;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLongin() {
        return isLongin;
    }

    public void setLongin(boolean longin) {
        isLongin = longin;
    }
}
