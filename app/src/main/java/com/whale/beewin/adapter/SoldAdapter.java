package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Sold;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4 0004.
 */

public class SoldAdapter extends BaseAdapter {
    private List<Sold.MlistBean> datas;
    private Context mContext;
    private ViewHolder mHolder;

    public SoldAdapter(List<Sold.MlistBean> datas, Context context) {
        this.datas = datas;
        mContext = context;
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
        Sold.MlistBean bean = datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sold, null);
            mHolder.mTvPersonPhone = (TextView) convertView.findViewById(R.id.tv_person_phone);
            mHolder.mTvPersonDate = (TextView) convertView.findViewById(R.id.tv_person_date);
            mHolder.mTvPersonSum = (TextView) convertView.findViewById(R.id.tv_person_sum);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.mTvPersonPhone.setText(bean.getUname());
        mHolder.mTvPersonDate.setText(bean.getTime());
        mHolder.mTvPersonSum.setText(bean.getTotalprice());
        return convertView;
    }

    class ViewHolder {
        TextView mTvPersonPhone,mTvPersonDate,mTvPersonSum;
    }
}
