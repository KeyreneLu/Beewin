package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.bean.Inform;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class InformAdapter extends BaseAdapter {
    private List<Inform> datas;
    private Context mContext;
    private ViewHolder mHolder;

    public InformAdapter (Context context,List<Inform> datas) {
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
        Inform bean = datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_inform, null);
            mHolder.mTvInFormMonth = (TextView) convertView.findViewById(R.id.tv_inform_month);
            mHolder.mTvInFormCorpus = (TextView) convertView.findViewById(R.id.tv_inform_corpus);
            mHolder.mTvInFormInterest = (TextView) convertView.findViewById(R.id.tv_inform_interest);
            mHolder.mTvInFormCorpusSum = (TextView) convertView.findViewById(R.id.tv_inform_corpusSum);
            mHolder.mTvInFormInterestSum = (TextView) convertView.findViewById(R.id.tv_inform_interestSum);
            mHolder.mTvInFormExtra = (TextView) convertView.findViewById(R.id.tv_inform_extra);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        mHolder.mTvInFormMonth.setText(bean.getMonth());
        mHolder.mTvInFormCorpus.setText(bean.getCorpus());
        mHolder.mTvInFormInterest.setText(bean.getInterest());
        mHolder.mTvInFormCorpusSum.setText(bean.getCorpusSum());
        mHolder.mTvInFormInterestSum.setText(bean.getInterestSum());
        mHolder.mTvInFormExtra.setText(bean.getExtra());
        return convertView;
    }

    class ViewHolder {
        TextView mTvInFormMonth,mTvInFormCorpus,mTvInFormInterest,mTvInFormCorpusSum,mTvInFormInterestSum,mTvInFormExtra;
    }
}
