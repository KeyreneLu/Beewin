package com.whale.beewin.view;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.whale.beewin.R;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public class MyMarkerView extends MarkerView {
    private TextView tvContent;

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent= (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        final DecimalFormat df = new DecimalFormat("#.00");
        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
//            tvContent.setText("" + Utils.formatNumber(ce.getHigh()/100, 0, true));
            tvContent.setText("" +df.format(ce.getHigh()/100));

        } else {
//            tvContent.setText("" + Utils.formatNumber(e.getY()/100, 0, true));
            tvContent.setText("" + df.format(e.getY()/100));
        }
        super.refreshContent(e, highlight);
    }
    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
