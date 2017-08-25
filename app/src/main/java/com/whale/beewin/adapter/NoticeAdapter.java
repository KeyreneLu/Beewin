package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Notice;

import java.util.List;

/**
 * 公告适配器
 * Created by Administrator on 2017/3/28 0028.
 */

public class NoticeAdapter extends BaseAdapter {
    private List<Notice.SlistBean> datas;
    private Context mContext;
    private ViewHolder mHolder;

    public NoticeAdapter(Context context,List<Notice.SlistBean> datas) {
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Notice.SlistBean bean = datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_notice, null);
            mHolder.mTvNoticeName = (TextView) convertView.findViewById(R.id.tv_notice_name);
            mHolder.mTvNoticeTime = (TextView) convertView.findViewById(R.id.tv_notice_time);
            mHolder.mIvNoticeState = (ImageView) convertView.findViewById(R.id.iv_notice_state);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.mTvNoticeName.setText("【"+bean.getMtype()+"】");
        mHolder.mTvNoticeTime.setText(bean.getAdd_time());
        if (bean.getStatus().equals("0")){
            mHolder.mIvNoticeState.setImageResource(R.drawable.gonggao_nokan);
        }else {
            mHolder.mIvNoticeState.setImageResource(R.drawable.gonggao_kan);
        }
        return convertView;
    }

    class ViewHolder {
        TextView mTvNoticeName,mTvNoticeTime;
        ImageView mIvNoticeState;
    }
}
