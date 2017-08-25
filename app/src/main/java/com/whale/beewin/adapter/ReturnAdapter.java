package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Return;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class ReturnAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Return.MlistBean> datas;
    private ViewHolder mHolder;
    public ReturnAdapter(Context context, ArrayList<Return.MlistBean> datas) {
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
        final Return.MlistBean mReturn= datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_return, null);
            mHolder.mTvReturnTime = (TextView) convertView.findViewById(R.id.tv_return_time);
            mHolder.mTvReturnSum = (TextView) convertView.findViewById(R.id.tv_return_sum);
            mHolder.mTvReturnGain = (TextView) convertView.findViewById(R.id.tv_return_gain);
            mHolder.mRlReturen = (LinearLayout) convertView.findViewById(R.id.rl_return);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.mTvReturnTime.setText(mReturn.getFeed_time().substring(0,10));
        mHolder.mTvReturnSum.setText(mReturn.getTamount());
       mHolder.mTvReturnGain.setText(mReturn.getGamount());
        if (mReturn.getFtype().equals("2")){
            mHolder.mRlReturen.setBackgroundResource(R.drawable.rect_green1);
        }else if (mReturn.getFtype().equals("0")){
            mHolder.mRlReturen.setBackgroundResource(R.drawable.rect_gray1);
        }else {
            mHolder.mRlReturen.setBackgroundResource(R.drawable.rect_red1);
        }
        return convertView;
    }

    class ViewHolder {
        TextView mTvReturnTime,mTvReturnSum,mTvReturnGain;
        LinearLayout mRlReturen;
    }
}
