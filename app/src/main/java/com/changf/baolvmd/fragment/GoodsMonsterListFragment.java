package com.changf.baolvmd.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.changf.baolvmd.R;
import com.changf.baolvmd.bean.GoodsBean;
import com.changf.baolvmd.bean.GoodsType;
import com.changf.baolvmd.util.FileUtil;
import com.mula.base.adapter.DataAdapter;
import com.mula.base.fragment.BaseFragment;
import com.mula.base.tools.display.ToastUtil;
import com.mula.base.tools.jump.IFragmentParams;
import com.mula.base.tools.jump.Static;
import com.mula.base.view.MulaTitleBar;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.changf.baolvmd.bean.GoodsType.GOODS;
import static com.changf.baolvmd.bean.GoodsType.GOODS_SCALE;
import static com.changf.baolvmd.bean.GoodsType.MONSTER;

public class GoodsMonsterListFragment extends BaseFragment {

    @BindView(R.id.mtb_title_bar)
    MulaTitleBar mtbTitleBar;
    @BindView(R.id.lv_list)
    ListView lvList;
    private List<String> goods;
    private List<String> monster;
    private List<String> goodsScaleBeanList;
    private GoodsType goodsType;
    private GoodsAdapter goodsAdapter;
    private GoodsBean goodsBean;

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            goodsBean = (GoodsBean) bundle.getSerializable("goodsBean");
            goodsType = goodsBean.getGoodsType();
            goods = goodsBean.getGoods();
            monster = goodsBean.getMonster();
        }
        switch (goodsType) {
            case GOODS:
                mtbTitleBar.setTitle("物品列表");
                break;
            case MONSTER:
                mtbTitleBar.setTitle("怪物列表");
                break;
            case GOODS_SCALE:
                mtbTitleBar.setTitle("爆率修改");
                mtbTitleBar.setRightText("保存");
                break;
        }
        goodsAdapter = new GoodsAdapter(goodsType);
        switch (goodsType) {
            case GOODS:
                goodsAdapter.dataSource.addAll(goods);
                break;
            case MONSTER:
                goodsAdapter.dataSource.addAll(monster);
                break;
            case GOODS_SCALE:
                parseFileContent(goodsBean.getFileName());
                break;
        }
        lvList.setAdapter(goodsAdapter);
    }

    private void parseFileContent(String fileName) {
        goodsScaleBeanList = FileUtil.getFromAssets(mActivity, "MonItems" + File.separator + fileName);
        goodsAdapter.dataSource.addAll(goodsScaleBeanList);
    }

    @OnClick({R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                switch (goodsType) {
                    case GOODS_SCALE:
                        ToastUtil.show("保存成功!");
                        break;
                }
                break;
            default:
                super.onClick(view);
                break;
        }
    }

    private class GoodsAdapter extends DataAdapter<String> {
        private GoodsType goodsType;

        GoodsAdapter(GoodsType goodsType) {
            this.goodsType = goodsType;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.layout_goods_item, null);
                assert convertView != null;
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(dataSource.get(position).replace(".txt", ""));
            if (goodsType == GOODS) {
                holder.btnOne.setVisibility(View.GONE);
                holder.btnTwo.setVisibility(View.GONE);
                holder.btnThree.setVisibility(View.GONE);
            } else if (goodsType == MONSTER) {
                holder.btnOne.setVisibility(View.GONE);
                holder.btnTwo.setVisibility(View.GONE);
                holder.btnThree.setVisibility(View.VISIBLE);
                holder.btnThree.setText("修改爆率");
                holder.btnThree.setOnClickListener(v -> {
                    Static.jumpToFragment(mActivity, GoodsMonsterListFragment.class, new IFragmentParams(new GoodsBean(GOODS_SCALE, goods, monster, monster.get(position))));
                });
            } else if (goodsType == GOODS_SCALE) {
                holder.btnOne.setVisibility(View.VISIBLE);
                holder.btnTwo.setVisibility(View.VISIBLE);
                holder.btnThree.setVisibility(View.VISIBLE);
                holder.btnOne.setText("删除");
                holder.btnTwo.setText("-");
                holder.btnThree.setText("+");
                holder.btnOne.setOnClickListener(v -> {
                    dataSource.remove(position);
                    notifyDataSetChanged();
                });
                holder.btnTwo.setOnClickListener(v -> {
                    String line = dataSource.get(position);
                    String scaleOrain = line.substring(0, line.indexOf(" "));
                    String goodsOrain = line.substring(line.indexOf(" "), line.length()).trim();
                    int baseNum = Integer.parseInt(scaleOrain.substring(scaleOrain.indexOf("/") + 1, scaleOrain.length()));
                    baseNum++;
                    line = "1/" + baseNum + " " + goodsOrain;
                    dataSource.set(position, line);
                    notifyDataSetChanged();
                });
                holder.btnThree.setOnClickListener(v -> {
                    String line = dataSource.get(position);
                    String scaleOrain = line.substring(0, line.indexOf(" "));
                    String goodsOrain = line.substring(line.indexOf(" "), line.length()).trim();
                    int baseNum = Integer.parseInt(scaleOrain.substring(scaleOrain.indexOf("/") + 1, scaleOrain.length()));
                    baseNum--;
                    line = "1/" + baseNum + " " + goodsOrain;
                    dataSource.set(position, line);
                    notifyDataSetChanged();
                });
            }
            return convertView;
        }

    }

    private class ViewHolder {
        TextView tvName;
        TextView btnOne;
        TextView btnTwo;
        TextView btnThree;

        public ViewHolder(View view) {
            tvName = view.findViewById(R.id.tv_goods_name);
            btnOne = view.findViewById(R.id.btn_one);
            btnTwo = view.findViewById(R.id.btn_two);
            btnThree = view.findViewById(R.id.btn_three);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_goods_monster;
    }

    public static GoodsMonsterListFragment newInstance(IFragmentParams<GoodsBean> params) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("goodsBean", params.params);
        GoodsMonsterListFragment fragment = new GoodsMonsterListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
