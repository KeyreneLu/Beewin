package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Coin;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4 0004.
 */

public class CoinAdapter extends BaseAdapter {
    private Context context;
    private List<Coin.SlistBean> data;
    Coin.SlistBean Coin;

    public CoinAdapter(Context context, List<Coin.SlistBean> data) {
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
        Coin = data.get(position);
        if (convertView == null) {
            myViews = new MyBaby();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_coin, null);
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
        myViews.mTvTimeWeek.setText(Coin.getFtime().split("</span>")[0].replace("<span>",""));
        myViews.mTvTime.setText(Coin.getFtime().split("</span>")[1].replace("<span>",""));
        if (Coin.getAction().equals("1")){
            myViews.mIvType.setImageResource(R.mipmap.hd_c);
        }else if (Coin.getAction().equals("2")){
            myViews.mIvType.setImageResource(R.mipmap.hd_j);
        }else if (Coin.getAction().equals("22")){
            myViews.mIvType.setImageResource(R.mipmap.hd_d);
        }else if (Coin.getAction().equals("51")){
            myViews.mIvType.setImageResource(R.mipmap.hd_gb);
        }else if (Coin.getAction().equals("52")){
            myViews.mIvType.setImageResource(R.mipmap.hd_gb);
        }else if (Coin.getAction().equals("61")||Coin.getAction().equals("81")||Coin.getAction().equals("82")){
            myViews.mIvType.setImageResource(R.mipmap.hd_gc);
        }else if (Coin.getAction().equals("83")){
            myViews.mIvType.setImageResource(R.mipmap.hd_gb);
        }else if (Coin.getAction().equals("111")){
            myViews.mIvType.setImageResource(R.mipmap.hd_gc);
        }else if (Coin.getAction().equals("112")){
            myViews.mIvType.setImageResource(R.mipmap.hq);
        } else {
            myViews.mIvType.setImageResource(R.mipmap.hd_fy);
        }
        myViews.mTvCoinNumber.setText(Coin.getScore());
        myViews.mTvCoinWay.setText(Coin.getDescripthion());
        return convertView;
    }

    private class MyBaby {
        private TextView mTvTimeWeek, mTvTime, mTvCoinNumber,mTvCoinWay;
        private ImageView mIvType;
    }

}
