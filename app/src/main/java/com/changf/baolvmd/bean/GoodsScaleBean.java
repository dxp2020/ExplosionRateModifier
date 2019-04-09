package com.changf.baolvmd.bean;

public class GoodsScaleBean{
    private int baseNum;
    private String goodsName;

    public int getBaseNum() {
        return baseNum;
    }

    public void setBaseNum(int baseNum) {
        this.baseNum = baseNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public GoodsScaleBean(int baseNum, String goodsName) {
        this.baseNum = baseNum;
        this.goodsName = goodsName;
    }

    public GoodsScaleBean() {
    }
}
