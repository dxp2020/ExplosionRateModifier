package com.changf.baolvmd.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.changf.baolvmd.R;
import com.changf.baolvmd.util.FileUtil;
import com.mula.base.activity.BaseActivity;
import com.mula.base.tools.display.ToastUtil;
import com.mula.base.view.MulaTitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.tv_goods_content)
    TextView tvGoodsContent;
    @BindView(R.id.btn_check_goods)
    Button btnCheckGoods;
    @BindView(R.id.ll_goods)
    LinearLayout llGoods;
    @BindView(R.id.tv_monster_content)
    TextView tvMonsterContent;
    @BindView(R.id.btn_check_monster)
    Button btnCheckMonster;
    @BindView(R.id.ll_monster)
    LinearLayout llMonster;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.lv_list)
    ListView lvList;
    List<String> goods;
    List<String> monster;
    @BindView(R.id.mtb_title_bar)
    MulaTitleBar mtbTitleBar;

    public void onImportFile(View view) {
        goods = FileUtil.getFromAssets(mActivity, "goods.txt");
        monster = FileUtil.getFromAssets(mActivity, "MonItems");
        if (goods.size() > 0 || monster.size() > 0) {
            ToastUtil.show("导入成功");
        } else {
            ToastUtil.show("导入失败");
        }
        if (goods.size() > 0) {
            tvGoodsContent.setText("共添加物品" + goods.size() + "件");
            llGoods.setVisibility(View.VISIBLE);
        }
        if (monster.size() > 0) {
            tvMonsterContent.setText("共添加怪物" + monster.size() + "个");
            llMonster.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initView() {
        mtbTitleBar.setTitle("爆率修改器");
        mtbTitleBar.getBackImage().setVisibility(View.GONE);
        lvList.setEmptyView(llContent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_setting;
    }

}
