package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Invest;
import com.whale.beewin.view.CircleProgressView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class InvestAdapter extends BaseAdapter {
    private Context context;
    private List<Invest.MlistBean> datas;
    private ViewHolder mHolder;

    public InvestAdapter(Context context, List<Invest.MlistBean> datas) {
        this.context = context;
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
        Invest.MlistBean bean = datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_invest, null);
            mHolder.mTvInvestName = (TextView) convertView.findViewById(R.id.tv_invest_name);
            mHolder.mTvInvestNumber = (TextView) convertView.findViewById(R.id.tv_invest_number);
            mHolder.mTvInvestPrecent = (TextView) convertView.findViewById(R.id.tv_invest_precemt);
            mHolder.mTvInvestTime = (TextView) convertView.findViewById(R.id.tv_invest_time);
            mHolder.mTvInvestDate = (TextView) convertView.findViewById(R.id.tv_invest_date);
            mHolder.mBtnItemHot = (Button) convertView.findViewById(R.id.btn_item_hot);
            mHolder.mBtnInvestNewhand = (Button) convertView.findViewById(R.id.btn_invest_newhand);
            mHolder.mBtnInvestOut = (Button) convertView.findViewById(R.id.btn_invest_out);
           mHolder.mProgressView = (CircleProgressView) convertView.findViewById(R.id.progressbar_invest);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.mTvInvestName.setText(bean.getName());
        mHolder.mTvInvestNumber.setText(bean.getQishu());
        mHolder.mTvInvestPrecent.setText(bean.getYearper() + "%");
        mHolder.mTvInvestTime.setText(bean.getTotaltime());
        mHolder.mTvInvestDate.setText(bean.getQtype());
        mHolder.mProgressView.setProgress(Double.parseDouble(bean.getSaled()));
        if (Double.parseDouble(bean.getSaled()) == 100.00) {
            mHolder.mProgressView.setTitle("已售罄");
            mHolder.mBtnItemHot.setVisibility(View.GONE);
        } else if (Double.parseDouble(bean.getSaled()) < 100.00) {
            mHolder.mProgressView.setTitle("立即加入");
            mHolder.mBtnInvestOut.setVisibility(View.GONE);
            String textview = mHolder.mTvInvestName.getText().toString();
            if (textview.contains("新手标")) {
                mHolder.mBtnInvestNewhand.setVisibility(View.VISIBLE);
            } else {
                mHolder.mBtnItemHot.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }

    class ViewHolder {
        TextView mTvInvestName,mTvInvestNumber,mTvInvestPrecent,mTvInvestTime,mTvInvestDate;
        Button mBtnItemHot,mBtnInvestOut,mBtnInvestNewhand;
        CircleProgressView mProgressView;
    }
}
