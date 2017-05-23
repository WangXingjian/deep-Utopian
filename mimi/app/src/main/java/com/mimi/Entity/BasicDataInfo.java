package com.mimi.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wangxingjian on 2017/5/23.
 */

public class BasicDataInfo {
    @SerializedName("name")
    private String name;
//    @SerializedName("markets")
//    private List<MarketsInfo> markets;
    @SerializedName("id")
    private String id;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @SerializedName("desc")
    private String desc;

    //for quick select menu
    private Boolean deleteMode;
    private Boolean selected;

//    public BasicDataInfo(String name, List<MarketsInfo> markets, String id) {
//        this.name = name;
//        this.markets = markets;
//        this.id = id;
//    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<MarketsInfo> getMarkets() {
//        return markets;
//    }
//
//    public void setMarkets(List<MarketsInfo> markets) {
//        this.markets = markets;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDeleteMode() {
        return deleteMode;
    }

    public void setDeleteMode(Boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
