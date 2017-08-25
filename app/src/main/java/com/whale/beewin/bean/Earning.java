package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class Earning {
    /**
     * mlist : [{"imoney":"0.65","add_time":"2017-04-10"},{"imoney":"0.65","add_time":"2017-04-09"},{"imoney":"0.62","add_time":"2017-04-08"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-07"},{"imoney":"0.61","add_time":"2017-04-07"},{"imoney":"1.00","add_time":"2017-04-06"},{"imoney":"1.00","add_time":"2017-04-06"},{"imoney":"6.92","add_time":"2017-04-06"},{"imoney":"0.83","add_time":"2017-04-05"},{"imoney":"0.61","add_time":"2017-04-05"},{"imoney":"0.83","add_time":"2017-04-04"},{"imoney":"0.59","add_time":"2017-04-04"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.83","add_time":"2017-04-03"},{"imoney":"0.59","add_time":"2017-04-03"},{"imoney":"0.58","add_time":"2017-04-02"},{"imoney":"0.83","add_time":"2017-04-01"}]
     * ucmoney : 17117.53
     */

    private String ucmoney;
    private List<MlistBean> mlist;

    public String getUcmoney() {
        return ucmoney;
    }

    public void setUcmoney(String ucmoney) {
        this.ucmoney = ucmoney;
    }

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class MlistBean {
        /**
         * imoney : 0.65
         * add_time : 2017-04-10
         */

        private String imoney;
        private String add_time;

        public String getImoney() {
            return imoney;
        }

        public void setImoney(String imoney) {
            this.imoney = imoney;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }
}
