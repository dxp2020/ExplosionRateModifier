package com.changf.baolvmd.util;

import android.content.Context;

import com.changf.baolvmd.bean.GoodsScaleBean;
import com.mula.base.tools.L;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtil {

    public static List<GoodsScaleBean> getGoodsScaleFromAssets(Context context, String fileName) {
        List<GoodsScaleBean> list = new ArrayList<>();
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(fileName)));
            String line;
            while((line = bufReader.readLine()) != null){
                if(!"".equals(line)){
                    GoodsScaleBean bean = new GoodsScaleBean();
                    String scaleOrain = line.substring(0,line.indexOf(" "));
                    String goodsOrain = line.substring(line.indexOf(" "),line.length());
                    bean.setBaseNum(Integer.parseInt(scaleOrain.substring(scaleOrain.indexOf("/")+1,scaleOrain.length())));
                    bean.setGoodsName(goodsOrain.trim());
                    list.add(bean);
                }
            }
        } catch (Exception e) {
            L.e(e);
        }
        return list;
    }

    public static List<String> getFromAssets(Context context, String fileName){
        List<String> list = new ArrayList<>();
        if(fileName==null){
            return list;
        }
        if (fileName.contains(".txt")) {
            try {
                BufferedReader bufReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(fileName),"GBK"));
                String line;
                while((line = bufReader.readLine()) != null){
                    if(!"".equals(line)){
                        list.add(new String(line.getBytes(),"UTF-8"));
                    }
                }
            } catch (Exception e) {
                L.e(e);
            }
        } else {
            try {
                String[]listFiles=context.getAssets().list(fileName);
                Collections.addAll(list, listFiles);
            } catch (Exception e) {
                L.e(e);
            }
        }
        return list;
    }

}
