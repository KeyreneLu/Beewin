package com.whale.beewin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.whale.beewin.bean.Banner;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class BannerAdapter  extends StaticPagerAdapter {
    private Context ctx;
    private List<Banner.AlistBean> list;

    public BannerAdapter(Context ctx, List<Banner.AlistBean> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        final ImageView imageView = new ImageView(ctx);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //加载图片
        ImageLoader.getInstance().displayImage(list.get(position).getAimg(),imageView);
//        //点击事件
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mIslogin == 1) {
//                    if (list.get(position).getType().equals("1")) {
//                        Intent intent = new Intent();
//                        intent.setClass(ctx, WithCookieActivity.class);
//                        intent.putExtra("url", list.get(position).getAurl());
//                        intent.putExtra("title", list.get(position).getTitle());
//                        ctx.startActivity(intent);
//                    } else {
//                        ctx.startActivity(new Intent(ctx, ScatterWealActivity.class));
//                    }
//                } else {
//                   ctx.startActivity(new Intent(ctx, LoginActivity.class));
//                }
//            }
//        });
        return imageView;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
