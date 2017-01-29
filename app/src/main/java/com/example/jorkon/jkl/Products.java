package com.example.jorkon.jkl;

/**
 * Created by jorkon on 1/23/2017.
 */

public class Products {
    private String pName;
    private String pColor;
    private String pPicUrl;
    private String pDescription;

    public Products() {

    }

    public String getColor() {
        return pColor;
    }

    public void setColor(String color) {
        this.pColor = color;
    }

    public String getPicUrl() {
        return pPicUrl;
    }

    public void setPicUrl(String picUrl) {
        this.pPicUrl = picUrl;
    }

    public String getDescription() {
        return pDescription;
    }

    public void setDescription(String description) {
        this.pDescription = description;
    }

    public Products(String name, String color, String picUrl, String description) {
        pName = name;
        pColor = color;
        pPicUrl = picUrl;
        pDescription = description;
    }

    public String getName() {
        return pName;
    }

    public void setName(String name) {
        pName = name;
    }
}
