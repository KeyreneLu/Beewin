package com.whale.beewin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.whale.beewin.R;

/**
 * vip适配器
 * Created by Administrator on 2017/4/4 0004.
 */

public class ImageAdapter extends BaseAdapter {
    private Context context;

    // 图片数组溿
    public Integer[] imgs = {R.drawable.banner_aa, R.drawable.banner_bb, R.drawable.banner_cc, R.drawable.banner_dd,
            R.drawable.banner_ee, R.drawable.banner_ff};

    public ImageAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    // 获取图片位置
    @Override
    public Object getItem(int position) {
        return imgs[position];
    }

    // 获取图片ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imgs[position % imgs.length]);
        imageView.setLayoutParams(new Gallery.LayoutParams(650, 300));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundColor(Color.alpha(1));
        return imageView;
    }
}