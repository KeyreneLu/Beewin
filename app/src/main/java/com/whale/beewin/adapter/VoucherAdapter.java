package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Cash;
import com.whale.beewin.utils.BgImageViewAware;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class VoucherAdapter extends BaseAdapter {
    private Context mContext;
    List<Cash.MlistBean> datas;
    private ViewHolder mHolder;

    public VoucherAdapter(Context context, List<Cash.MlistBean> datas) {
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
        final Cash.MlistBean voucher = datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_voucher, null);
            mHolder.mCashMoney = (TextView) convertView.findViewById(R.id.cash_money);
            mHolder.mTvTime = (TextView) convertView.findViewById(R.id.tv_time);
            mHolder.mTvDeadLine = (TextView) convertView.findViewById(R.id.tv_deadline);
            mHolder.mTvCardType = (TextView) convertView.findViewById(R.id.tv_card_type);
            mHolder.mRlCardPic = (ImageView) convertView.findViewById(R.id.rl_card_pic);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(voucher.getFile_url(), new BgImageViewAware(mHolder.mRlCardPic));
//        Glide.with(mContext).load(voucher.getFile_url()).into(mHolder.mRlCardPic);
        mHolder.mTvTime.setText(voucher.getBscore());
        mHolder.mTvDeadLine.setText(voucher.getDays());
       mHolder.mTvCardType.setText(voucher.getTitle().substring(voucher.getTitle().length()-3,voucher.getTitle().length()));
       mHolder.mCashMoney.setText(voucher.getTitle().substring(0,voucher.getTitle().length()-3));
        return convertView;
    }

    class ViewHolder {
        TextView mCashMoney,mTvTime,mTvDeadLine,mTvCardType;
        ImageView mRlCardPic;
    }
}
