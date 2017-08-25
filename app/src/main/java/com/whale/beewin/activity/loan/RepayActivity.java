package com.whale.beewin.activity.loan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.whale.beewin.R;
import com.whale.beewin.activity.me.BulkRuleActivity;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.utils.Utils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 还款界面
 * Created by Administrator on 2017/4/12 0012.
 */

public class RepayActivity extends BaseActivity {
    @Bind(R.id.tv_repay_sum)
    TextView mTvRepaySum;
    @Bind(R.id.rb_take_all)
    RadioButton mRbTakeAll;
    @Bind(R.id.rb_sale_proccess)
    RadioButton mRbSaleProccess;
    @Bind(R.id.rg_menu)
    RadioGroup mRgMenu;
    @Bind(R.id.id_tab_line)
    ImageView mIdTabLine;
    @Bind(R.id.iv_bulk_type)
    ImageView mIvBulkType;
    @Bind(R.id.ll_repay_status)
    LinearLayout mLlRepayStatus;
    @Bind(R.id.list)
    ListView mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay);
        initView();
    }

    private void initView() {
        mIdTabLine.getLayoutParams().width = Utils.getScreenWidth(activity) / 2;

        mRgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mIdTabLine.getLayoutParams();
                switch (checkedId) {
                    case R.id.rb_take_all:
                        mList.setVisibility(View.GONE);
                        lp.leftMargin = (int) (mRgMenu.getWidth() / 2) * 0;
                        mIdTabLine.setLayoutParams(lp);
                        mLlRepayStatus.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_sale_proccess:
                        //获取组件距离左侧组件的距离
                        mList.setVisibility(View.GONE);
                        lp.leftMargin = (int) (mRgMenu.getWidth() / 2);
                        mIdTabLine.setLayoutParams(lp);
                        mLlRepayStatus.setVisibility(View.GONE);

                        break;
                }
            }
        });
    }

    @OnClick(R.id.iv_bulk_type)
    void bulkType(){
        Intent intent = new Intent(activity, BulkRuleActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.iv_home_left)
    void Finish(){
        finish();
    }
}
