package com.whale.beewin.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.R;
import com.whale.beewin.activity.found.MallActivity;
import com.whale.beewin.adapter.CardAdapter;
import com.whale.beewin.adapter.MyCardAdapter;
import com.whale.beewin.base.BaseActivity;
import com.whale.beewin.bean.MyCard;
import com.whale.beewin.bean.MyCash;
import com.whale.beewin.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/11 0011.
 */

public class MyCashActivity extends BaseActivity {
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.ll_me_none)
    LinearLayout mLlMeNone;
    @Bind(R.id.list)
    ListView mList;
    @Bind(R.id.iv_home_left)
    ImageView mIvHomeLeft;
    @Bind(R.id.iv_home_right)
    TextView mIvHomeRight;
    private String pwd;//用户密码
    private String login;//用户名
    List<MyCash.MlistBean> list = new ArrayList<>();
    List<MyCard.MlistBean> list2 = new ArrayList<>();
    CardAdapter mAdapter;
    MyCardAdapter mAdapter2;
    private String flag = "0";
    private String sid;
    private String qid;
    Subscription sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycash);
        if (getIntent() != null) {
            flag = getIntent().getStringExtra("flag");
        }
        pwd = (String) SPUtils.get(BeewinApp.getContext(), "pwd", "");
        login = (String) SPUtils.get(BeewinApp.getContext(), "login", "");
        initView();
        httpData();
    }

    private void initView() {
        if (flag.equals("1")) {
            mIvHomeRight.setVisibility(View.VISIBLE);
        }

        mIvHomeRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sid == null || sid.isEmpty()) {
                    showToast("未选择代金券");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("score",sid);
                intent.putExtra("qid", qid);

                // 设置结果，并进行传送
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mIvHomeLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sid = list2.get(position).getScore();
                qid = list2.get(position).getId();
                mAdapter2.changeSelected(position);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void httpData() {
        if (flag.equals("1")) {
            sub = wrapper.getMyCard(login, pwd)
                    .subscribe(newSubscriber(new Action1<MyCard>() {
                        @Override
                        public void call(MyCard myCash) {
                            mLlLoading.setVisibility(View.GONE);
                            if (myCash.getMlist() == null || myCash.getMlist().size() == 0) {
                                mLlMeNone.setVisibility(View.VISIBLE);
                            } else {
                                mList.setVisibility(View.VISIBLE);
                                list2.addAll(myCash.getMlist());
                                mAdapter2 = new MyCardAdapter(activity, list2);
                                mList.setAdapter(mAdapter2);
                            }
                        }
                    }));
        } else {
            sub = wrapper.getMyCash(login, pwd)
                    .subscribe(newSubscriber(new Action1<MyCash>() {
                        @Override
                        public void call(MyCash myCash) {
                            mLlLoading.setVisibility(View.GONE);
                            if (myCash.getMlist() == null || myCash.getMlist().size() == 0) {
                                mLlMeNone.setVisibility(View.VISIBLE);
                            } else {
                                mList.setVisibility(View.VISIBLE);
                                list.addAll(myCash.getMlist());
                                mAdapter = new CardAdapter(activity, list);
                                mList.setAdapter(mAdapter);
                            }
                        }
                    }));
        }
        mCompositeSubscription.add(sub);
    }

    @OnClick(R.id.button_me_touzi)
    void bugCash() {
        startActivity(new Intent(activity, MallActivity.class));
    }
}
