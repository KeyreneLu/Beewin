package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4 0004.
 */

public class Sold {
    private List<MlistBean> mlist;

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class MlistBean {
        /**
         * uname : 135****3228
         * totalprice : 1000.00
         * time : 2017-04-04 09:30:01
         */

        private String uname;
        private String totalprice;
        private String time;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
