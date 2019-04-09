package com.changf.baolvmd.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsBean implements Serializable {
    private GoodsType goodsType;
    private List<String> goods;
    private List<String> monster;
    private String fileName;

    public GoodsBean(GoodsType goodsType, List<String> goods) {
        this.goodsType = goodsType;
        this.goods = goods;
    }

    public GoodsBean(GoodsType goodsType, List<String> goods, List<String> monster) {
        this.goodsType = goodsType;
        this.goods = goods;
        this.monster = monster;
    }

    public GoodsBean(GoodsType goodsType, List<String> goods, List<String> monster, String fileName) {
        this.goodsType = goodsType;
        this.goods = goods;
        this.monster = monster;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public List<String> getGoods() {
        if (goods!=null) {
            return goods;
        }else{
            return new ArrayList<>();
        }
    }

    public List<String> getMonster() {
        if (monster!=null) {
            return monster;
        }else{
            return new ArrayList<>();
        }
    }

}
