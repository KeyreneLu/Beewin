package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.MyCash;
import com.whale.beewin.utils.BgImageViewAware;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class CardAdapter extends BaseAdapter {
    private Context mContext;
    private List<MyCash.MlistBean> datas;
    private ViewHolder mHolder;
    int mSelect = -1;
    public CardAdapter(Context context, List<MyCash.MlistBean> datas) {
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
        MyCash.MlistBean bean = datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cash, null);
            mHolder.mCashMoney = (TextView) convertView.findViewById(R.id.cash_money);
            mHolder.mTvTime = (TextView) convertView.findViewById(R.id.tv_time);
            mHolder.mTvDeadLine = (TextView) convertView.findViewById(R.id.tv_deadline);
            mHolder.mTvCardType = (TextView) convertView.findViewById(R.id.tv_card_type);
            mHolder.mRlCardPic = (ImageView) convertView.findViewById(R.id.rl_card_pic);
            mHolder.mIvChoose = (ImageView) convertView.findViewById(R.id.iv_choose);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(bean.getMimg(), new BgImageViewAware(mHolder.mRlCardPic));
        mHolder.mTvTime.setText(bean.getEnd_time());
        mHolder.mCashMoney.setText(bean.getDescripthion());
        if (bean.getDays().equals("永久")) {
            mHolder.mTvDeadLine.setText(bean.getDays());
        }else {
            mHolder.mTvDeadLine.setText(bean.getDays());
        }
        if (mSelect == position){
            mHolder.mIvChoose.setVisibility(View.VISIBLE);
        }else {
            mHolder.mIvChoose.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView mCashMoney,mTvTime,mTvDeadLine,mTvCardType;
        ImageView mRlCardPic,mIvChoose;
    }

    public void changeSelected(int pos) { //刷新方法
        if (pos != mSelect) {
            mSelect = pos;
            notifyDataSetChanged();
        }
    }


}
