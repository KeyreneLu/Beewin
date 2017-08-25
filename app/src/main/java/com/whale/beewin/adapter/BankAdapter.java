package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Bank;

import java.util.List;


/**
 * 银行信息适配器
 * Created by Administrator on 2016/8/27 0027.
 */
public class BankAdapter extends BaseAdapter {

    private Context mContext;
    private List<Bank> data;
    private ViewHolder mHolder;
    int mSelect = 1;

    public BankAdapter(Context context, List<Bank> data) {
        this.mContext = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_bank1, null);
            mHolder.mIvBank = (ImageView) convertView.findViewById(R.id.iv_bank);
            mHolder.mTvBankName = (TextView) convertView.findViewById(R.id.tv_bank_name);
            mHolder.mTvBankCost = (TextView) convertView.findViewById(R.id.tv_bank_cost);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        Bank bank = data.get(position);
        mHolder.mIvBank.setImageResource(bank.getLogo());
        mHolder.mTvBankName.setText(bank.getName());
        mHolder.mTvBankCost.setText(bank.getCost());
//        选中那个，改变那个的颜色
        if (position == mSelect) {
            mHolder.mTvBankName.setTextColor(mContext.getResources().getColor(R.color.touzi_fenshu));
            mHolder.mTvBankCost.setTextColor(mContext.getResources().getColor(R.color.touzi_fenshu));
        } else {
            mHolder.mTvBankName.setTextColor(mContext.getResources().getColor(R.color.three_color));
            mHolder.mTvBankCost.setTextColor(mContext.getResources().getColor(R.color.three_color));
        }
        return convertView;
    }

    class ViewHolder {
        TextView mTvBankCost;
        ImageView mIvBank;
        TextView mTvBankName;
    }
    public void changeSelected(int pos) { //刷新方法
        if (pos != mSelect) {
            mSelect = pos;
            notifyDataSetChanged();
        }
    }
}
