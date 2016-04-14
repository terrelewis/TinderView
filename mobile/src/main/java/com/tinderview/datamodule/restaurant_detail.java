package com.tinderview.datamodule;

/**
 * Created by terrelewis on 22/1/16.
 */
public class restaurant_detail {

    String restname;
    String imgurl;
    String rating;
    String cuisine;

    public String getAvgtwocost() {
        return avgtwocost;
    }

    public void setAvgtwocost(String avgtwocost) {
        this.avgtwocost = avgtwocost;
    }

    String avgtwocost;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getRestname() {

        return restname;
    }
    public String getRestrating() {

        return rating;
    }
    public String getRestcuisine() {

        return cuisine;
    }
    public void setRestname(String restname) {
        this.restname = restname;
    }
    public void setRestrating(String restrating) {
        this.rating = restrating;
    }
    public void setRestcuisine(String restcuisine) {
        this.cuisine = restcuisine;
    }
}
