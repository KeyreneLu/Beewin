package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Income;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class RewardAdapter extends BaseAdapter {
    private Context mContext;
    private List<Income.MlistBean> datas;
    Income.MlistBean mReward;
    public RewardAdapter(Context context, List<Income.MlistBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyBaby myViews = null;
        mReward = datas.get(position);
        if (convertView == null) {
            myViews = new MyBaby();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_found_wodehuabi, null);
            myViews.mTvTimeWeek = (TextView) convertView.findViewById(R.id.tv_time_week);
            myViews.mTvTime = (TextView) convertView.findViewById(R.id.tv_time);
            myViews.mTvCoinNumber = (TextView) convertView.findViewById(R.id.tv_coin_number);
            myViews.mTvCoinWay = (TextView) convertView.findViewById(R.id.tv_coin_way);
            myViews.mIvType = (ImageView) convertView.findViewById(R.id.iv_type);
            // 锁定视图
            convertView.setTag(myViews);
        } else {
            // 解锁视图
            myViews = (MyBaby) convertView.getTag();
        }
        myViews.mTvTimeWeek.setText(mReward.getFtime().split("</span>")[0].replace("<span>",""));
        myViews.mTvTime.setText(mReward.getFtime().split("</span>")[1].replace("<span>",""));
        myViews.mTvCoinNumber.setText(mReward.getScore());
        myViews.mTvCoinWay.setText(mReward.getDescripthion());
        myViews.mIvType.setImageResource(R.drawable.rmjj);
        return convertView;
    }

    private class MyBaby {
        private TextView mTvTimeWeek, mTvTime, mTvCoinNumber,mTvCoinWay;
        private ImageView mIvType;
    }
}
