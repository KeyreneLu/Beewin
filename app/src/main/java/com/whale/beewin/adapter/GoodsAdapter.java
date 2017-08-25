package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.InvestRecord;

import java.util.List;

public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private List<InvestRecord.GoodsBean> data;
    public GoodsAdapter(Context context, List<InvestRecord.GoodsBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        MyBaby myViews = null;
        if (convertView == null) {
            myViews = new MyBaby();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_me, null);
            myViews.textview01 = (TextView) convertView.findViewById(R.id.textview_me_fengying);
            myViews.textview02 = (TextView) convertView.findViewById(R.id.textview_number_me);
            myViews.textview03 = (TextView) convertView.findViewById(R.id.textview_me_bfb);
            myViews.textview04 = (TextView) convertView.findViewById(R.id.textview_me_qixiantime);
            myViews.textview05 = (TextView) convertView.findViewById(R.id.textview_me_yue);
            myViews.textview06 = (TextView) convertView.findViewById(R.id.textview_me_xxhb);
            myViews.textview07 = (TextView) convertView.findViewById(R.id.textview_me_jysj);
            myViews.textview08 = (TextView) convertView.findViewById(R.id.textview_me_dqsj);
            myViews.textview09 = (TextView) convertView.findViewById(R.id.textview_me_gmje);
            myViews.textview010 = (TextView) convertView.findViewById(R.id.textview_me_yuqisy);
            myViews.imageview_chiyou = (ImageView) convertView.findViewById(R.id.imageview_me_cy);
            // 锁定视图
            convertView.setTag(myViews);
        } else {
            // 解锁视图
            myViews = (MyBaby) convertView.getTag();
        }

        InvestRecord.GoodsBean bean = data.get(position);
        myViews.textview01.setText(bean.getName() + "");
        myViews.textview02.setText(bean.getQishu() + "");
        myViews.textview03.setText(bean.getYearper() + "");
        if (bean.getTotaltime().contains("天")){
            myViews.textview04.setText(bean.getTotaltime().split("天")[0]);
        }else {
            myViews.textview04.setText(bean.getTotaltime() + "");
        }
        myViews.textview05.setText(bean.getQtype() + "");
        myViews.textview06.setText(bean.getPaytype() + "");
        myViews.textview07.setText(bean.getBtime() + "");
        myViews.textview08.setText(bean.getEtime() + "");
        myViews.textview09.setText(bean.getGprice() + "");
        myViews.textview010.setText(bean.getMprice() + "");
        if (bean.getOstatus().equals("1")) {
            myViews.imageview_chiyou.setImageResource(R.mipmap.w_yhk);
        } else {
            myViews.imageview_chiyou.setImageResource(R.mipmap.w_cyz);
        }
        return convertView;
    }


    private class MyBaby {
        private TextView textview01, textview02, textview03, textview04, textview05, textview06, textview07, textview08, textview09, textview010;
        private ImageView imageview_chiyou;
    }


}
