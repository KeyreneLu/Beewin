package com.whale.beewin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whale.beewin.R;
import com.whale.beewin.bean.Game;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class GameAdapter extends BaseAdapter {
    private Context mContext;
    private List<Game.HlistBean> datas;
    private ViewHolder mHolder;

    public GameAdapter(Context context, List<Game.HlistBean> datas) {
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
        Game.HlistBean bean = datas.get(position);
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_game, null);
            mHolder.mIvFindTag = (ImageView) convertView.findViewById(R.id.iv_find_tag);
            mHolder.mIvFindTag1 = (ImageView) convertView.findViewById(R.id.iv_find_tag1);
            mHolder.mIvFindTag2 = (ImageView) convertView.findViewById(R.id.iv_find_tag2);
            mHolder.mIvFindTag3 = (ImageView) convertView.findViewById(R.id.iv_find_tag3);
            mHolder.mIvFindTag4 = (ImageView) convertView.findViewById(R.id.iv_find_tag4);
            mHolder.mIvFindTag5 = (ImageView) convertView.findViewById(R.id.iv_find_tag5);
            mHolder.mTvGameTitle = (TextView) convertView.findViewById(R.id.tv_game_title);
            mHolder.mIvGamePic = (ImageView) convertView.findViewById(R.id.iv_game_pic);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
            if (bean.getC7().equals("1")){
                mHolder.mIvFindTag1.setVisibility(View.VISIBLE);
                mHolder.mIvFindTag1.setImageResource(R.mipmap.fx_hb);
            }else {
                mHolder.mIvFindTag1.setVisibility(View.GONE);
            }

            if (bean.getC8().equals("1")){
                mHolder.mIvFindTag2.setVisibility(View.VISIBLE);
                mHolder.mIvFindTag2.setImageResource(R.mipmap.fx_jb);
            }else {
                mHolder.mIvFindTag2.setVisibility(View.GONE);
            }

            if (bean.getC9().equals("1")){
                mHolder.mIvFindTag3.setVisibility(View.VISIBLE);
                mHolder.mIvFindTag3.setImageResource(R.mipmap.fx_lw);
            }else {
                mHolder.mIvFindTag3.setVisibility(View.GONE);
            }
            if (bean.getC10().equals("1")){
                mHolder.mIvFindTag4.setVisibility(View.VISIBLE);
                mHolder.mIvFindTag4.setImageResource(R.mipmap.fx_kq);
            }else {
                mHolder.mIvFindTag4.setVisibility(View.GONE);
            }
        }

        Glide.with(mContext)
                .load(bean.getHimg())
                .into(mHolder.mIvGamePic);

        mHolder.mTvGameTitle.setText(bean.getHtitle());
        if (bean.getC4().equals("1")){
            mHolder.mIvFindTag.setImageResource(R.mipmap.fx_qt);
        }else if (bean.getC1().equals("1")){
            mHolder.mIvFindTag.setImageResource(R.mipmap.fx_rc);
        }else if (bean.getC3().equals("1")){
            mHolder.mIvFindTag.setImageResource(R.mipmap.fx_jy);
        }else if (bean.getC5().equals("1")){
            mHolder.mIvFindTag.setImageResource(R.mipmap.fx_vip);
        }else if (bean.getC2().equals("1")){
            mHolder.mIvFindTag.setImageResource(R.mipmap.fx_sr);
        }else if (bean.getC6().equals("1")){
            mHolder.mIvFindTag.setImageResource(R.mipmap.fx_fx);
        }
        if (bean.getC7().equals("1")){
            mHolder.mIvFindTag1.setVisibility(View.VISIBLE);
            mHolder.mIvFindTag1.setImageResource(R.mipmap.fx_hb);
        }else {
            mHolder.mIvFindTag1.setVisibility(View.GONE);
        }

        if (bean.getC8().equals("1")){
            mHolder.mIvFindTag2.setVisibility(View.VISIBLE);
            mHolder.mIvFindTag2.setImageResource(R.mipmap.fx_jb);
        }else {
            mHolder.mIvFindTag2.setVisibility(View.GONE);
        }

        if (bean.getC9().equals("1")){
            mHolder.mIvFindTag3.setVisibility(View.VISIBLE);
            mHolder.mIvFindTag3.setImageResource(R.mipmap.fx_lw);
        }else {
            mHolder.mIvFindTag3.setVisibility(View.GONE);
        }

        if (bean.getC10().equals("1")){
            mHolder.mIvFindTag4.setVisibility(View.VISIBLE);
            mHolder.mIvFindTag4.setImageResource(R.mipmap.fx_kq);
        }else {
            mHolder.mIvFindTag4.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView mIvFindTag,mIvFindTag1,mIvFindTag2,mIvFindTag3,mIvFindTag4,mIvFindTag5,mIvGamePic;
        TextView mTvGameTitle;
    }

}
