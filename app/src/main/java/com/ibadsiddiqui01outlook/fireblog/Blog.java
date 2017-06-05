package com.ibadsiddiqui01outlook.fireblog;

/**
 * Created by IBAD SIDDIQUI on 6/5/2017.
 */

public class Blog {

    private String Desc;
    private String Title;
    private String image;


    public Blog(){}
    public Blog(String title, String desc, String image) {
        Desc = desc;
        Title = title;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

}
