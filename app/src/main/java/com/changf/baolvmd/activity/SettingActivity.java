package com.changf.baolvmd.activity;

import android.view.View;
import android.widget.ListView;

import com.changf.baolvmd.R;
import com.changf.baolvmd.util.FileUtil;
import com.mula.base.activity.BaseActivity;
import com.mula.base.tools.display.ToastUtil;

import java.util.List;

import butterknife.BindView;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.lv_list)
    ListView lvList;
    List<String> goods;

    public void onImportFile(View view) {
        goods = FileUtil.getFromAssets(mActivity,"goods.txt");
        if(goods.size()>0){
            ToastUtil.show("导入成功："+goods.size()+"个");
        }else{
            ToastUtil.show("导入失败");
        }
    }

    public void addMonster(View view) {

    }

    public void addEquipment(View view) {

    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_setting;
    }
}
