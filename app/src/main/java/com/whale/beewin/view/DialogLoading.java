package com.whale.beewin.view;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.whale.beewin.R;

/**
 * Created by Administrator on 2017/3/23 0023.
 */

public class DialogLoading extends Dialog {

    private TextView loadingLabel;

    public DialogLoading(Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.dialog_loading_layout);
        setCanceledOnTouchOutside(false);
        loadingLabel = (TextView) findViewById(R.id.loading_text);
    }

    public void setDialogLabel(String label) {
        loadingLabel.setText(label);
    }
}
