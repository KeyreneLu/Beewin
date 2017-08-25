package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.ScatterRecord;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class ScatterAdapter extends BaseAdapter {
    private Context mContext;
    private List<ScatterRecord.MlistBean> datas;
    private ViewHolder mHolder;

    public ScatterAdapter(Context context, List<ScatterRecord.MlistBean> datas) {
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
        final ScatterRecord.MlistBean scatter = datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_scatter, null);
            mHolder.mTvDcatterDate = (TextView) convertView.findViewById(R.id.tv_scatter_date);
            mHolder.mTvScatterSum = (TextView) convertView.findViewById(R.id.tv_scatter_sum);
            mHolder.mTvScatterTime = (TextView) convertView.findViewById(R.id.tv_scatter_time);
            mHolder.mIvRight = (ImageView) convertView.findViewById(R.id.iv_right);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.mTvDcatterDate.setText(scatter.getAdd_time());
        mHolder.mTvScatterTime.setText(scatter.getShichang()+"个月");
        mHolder.mTvScatterSum.setText(scatter.getTamount());
        if (!scatter.getIsover().equals("dealison")){
            mHolder.mTvScatterSum.setTextColor(mContext.getResources().getColor(R.color.nine_color));
            mHolder.mTvDcatterDate.setTextColor(mContext.getResources().getColor(R.color.nine_color));
            mHolder.mTvScatterTime.setTextColor(mContext.getResources().getColor(R.color.nine_color));
        }else {
            mHolder.mTvScatterSum.setTextColor(mContext.getResources().getColor(R.color.touzi_fenshu));
            mHolder.mTvDcatterDate.setTextColor(mContext.getResources().getColor(R.color.e_red));
            mHolder.mTvScatterTime.setTextColor(mContext.getResources().getColor(R.color.touzi_fenshu));
        }
        return convertView;
    }

    class ViewHolder {
        TextView mTvDcatterDate,mTvScatterSum,mTvScatterTime;
        ImageView mIvRight;
    }


}
