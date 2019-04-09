package com.changf.baolvmd.util;

import android.content.Context;

import com.mula.base.tools.L;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtil {

    public static List<String> getFromAssets(Context context, String fileName){
        List<String> list = new ArrayList<>();
        if ("goods.txt".equals(fileName)) {
            try {
                BufferedReader bufReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(fileName)));
                String line;
                while((line = bufReader.readLine()) != null){
                    if(!"".equals(line)){
                        list.add(line);
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
