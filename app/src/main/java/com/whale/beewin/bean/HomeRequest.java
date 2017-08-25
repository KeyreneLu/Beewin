package com.whale.beewin.bean;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class HomeRequest {
    UserInfo mUserInfo;
    InvestRecord mInvestRecord;
    ScatterRecord mScatterRecord;
    MyCash mMyCash;
    Routine mRoutine;
    Realm mRealm;
    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }

    public InvestRecord getInvestRecord() {
        return mInvestRecord;
    }

    public void setInvestRecord(InvestRecord investRecord) {
        mInvestRecord = investRecord;
    }

    public ScatterRecord getScatterRecord() {
        return mScatterRecord;
    }

    public void setScatterRecord(ScatterRecord scatterRecord) {
        mScatterRecord = scatterRecord;
    }

    public MyCash getMyCash() {
        return mMyCash;
    }

    public void setMyCash(MyCash myCash) {
        mMyCash = myCash;
    }

    public Routine getRoutine() {
        return mRoutine;
    }

    public void setRoutine(Routine routine) {
        mRoutine = routine;
    }

    public Realm getRealm() {
        return mRealm;
    }

    public void setRealm(Realm realm) {
        mRealm = realm;
    }
}
