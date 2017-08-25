package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Earning;

import java.util.List;

public class EarningAdapter extends BaseAdapter {
    private Context context;
    private List<Earning.MlistBean> data;
    public EarningAdapter(Context context, List<Earning.MlistBean> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        MyBaby myViews = null;
        if (convertView == null) {
            myViews = new MyBaby();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_earning, null);
            myViews.textview01 = (TextView) convertView.findViewById(R.id.textview_ljsy_item_time);
            myViews.textview02 = (TextView) convertView.findViewById(R.id.textview_ljsy_item_zrsy);
            // 锁定视图
            convertView.setTag(myViews);
        } else {
            // 解锁视图
            myViews = (MyBaby) convertView.getTag();
        }
        Earning.MlistBean bean = data.get(position);
        myViews.textview01.setText(bean.getAdd_time() + "");
        myViews.textview02.setText(bean.getImoney() + "元");
        return convertView;
    }

    private class MyBaby {
        private TextView textview01, textview02;
    }

}
