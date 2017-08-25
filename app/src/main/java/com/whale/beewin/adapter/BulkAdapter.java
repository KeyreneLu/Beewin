package com.whale.beewin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.activity.me.BulkDetailActivity;
import com.whale.beewin.bean.Bulk;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class BulkAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Bulk.MlistBean> datas;
    private ViewHolder mHolder;

    public BulkAdapter(Context context, ArrayList<Bulk.MlistBean> datas) {
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
        final Bulk.MlistBean  bulk = datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bulk, null);
            mHolder.mTvBulkName = (TextView) convertView.findViewById(R.id.tv_bulk_name);
            mHolder.mTvBulkStatus = (TextView) convertView.findViewById(R.id.tv_bulk_status);
            mHolder.mTvBulkPrecent = (TextView) convertView.findViewById(R.id.tv_bulk_precent);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.mTvBulkName.setText("借贷编号:"+bulk.getDeal_sn());
        mHolder.mTvBulkStatus.setText(bulk.getBtips());
        mHolder.mTvBulkPrecent.setText(bulk.getYearper()+"%");
        if (bulk.getDstatus().equals("2")){
            mHolder.mTvBulkStatus.setTextColor(mContext.getResources().getColor(R.color.four_d));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BulkDetailActivity.class);
                    intent.putExtra("id",bulk.getId());
                    mContext.startActivity(intent);
                }
            });
        }else {
            mHolder.mTvBulkStatus.setTextColor(mContext.getResources().getColor(R.color.touzi_fenshu));
        }
        return convertView;
    }

    class ViewHolder {
        TextView mTvBulkName,mTvBulkStatus,mTvBulkPrecent;
    }

}
