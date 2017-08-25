package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Reckoning;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class ReckoningAdapter  extends BaseAdapter {
    private Context mContext;
    private List<Reckoning.SlistBean> datas;
    public ReckoningAdapter(Context context, List<Reckoning.SlistBean> datas) {
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
        Reckoning.SlistBean bean = datas.get(position);
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
        myViews.mTvTimeWeek.setText(bean.getFtime().split("</span>")[0].replace("<span>",""));
        myViews.mTvTime.setText(bean.getFtime().split("</span>")[1].replace("<span>",""));
//       if (bean.getFtime().contains("小时前")||bean.getFtime().contains("昨天")){
//           myViews.mTvTimeWeek.setText(bean.getFtime());
//       }else {
//           myViews.mTvTimeWeek.setText(bean.getFtime().substring(0,3));
//       }
//        myViews.mTvTime.setText(Utils.getStrTime(bean.getAdd_time()));
        myViews.mTvCoinNumber.setText(bean.getScore());
        myViews.mTvCoinWay.setText(bean.getDescripthion());
        if (bean.getAction().equals("lottery")){
            myViews.mIvType.setImageResource(R.mipmap.hd_fy);
        }else if (bean.getAction().equals("win")){
            myViews.mIvType.setImageResource(R.mipmap.w_qbsy);
        }else if (bean.getAction().equals("quan")){
            myViews.mIvType.setImageResource(R.mipmap.hd_bo);
        }else if (bean.getAction().equals("tixian")){
            myViews.mIvType.setImageResource(R.mipmap.zd_zz);
        }else if (bean.getAction().equals("gongzi")){
            myViews.mIvType.setImageResource(R.mipmap.zd_zz);
        }else if (bean.getAction().equals("csxxl")){
            myViews.mIvType.setImageResource(R.mipmap.zd_zz);
        }else if (bean.getAction().equals("inbuy")){
            myViews.mIvType.setImageResource(R.mipmap.w_qbsy);
        }else if (bean.getAction().equals("wingood")){
            myViews.mIvType.setImageResource(R.mipmap.w_qbsy);
        }else if (bean.getAction().equals("jfcharge")){
            myViews.mIvType.setImageResource(R.mipmap.hd_flower);
        }else {
            myViews.mIvType.setImageResource(R.mipmap.w_bdhk);
        }
        return convertView;
    }

    private class MyBaby {
        private TextView mTvTimeWeek, mTvTime, mTvCoinNumber,mTvCoinWay;
        private ImageView mIvType;
    }
}