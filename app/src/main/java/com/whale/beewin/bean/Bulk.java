package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class Bulk {


    private List<MlistBean> mlist;

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class MlistBean {
        /**
         * id : 6866
         * deal_sn : BW2017030006867
         * tamount : 100.00
         * dstatus : 2
         * yearper : 10.39
         * deal_time : 1490933022
         * end_time : 1498881819
         * feed_time : 0
         * add_time : 1490933019
         * shichang : 3
         * btips : 2017-03-31 12:03   已满标 开始计息
         */

        private String id;
        private String deal_sn;
        private String tamount;
        private String dstatus;
        private double yearper;
        private String deal_time;
        private String end_time;
        private String feed_time;
        private String add_time;
        private String shichang;
        private String btips;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeal_sn() {
            return deal_sn;
        }

        public void setDeal_sn(String deal_sn) {
            this.deal_sn = deal_sn;
        }

        public String getTamount() {
            return tamount;
        }

        public void setTamount(String tamount) {
            this.tamount = tamount;
        }

        public String getDstatus() {
            return dstatus;
        }

        public void setDstatus(String dstatus) {
            this.dstatus = dstatus;
        }

        public double getYearper() {
            return yearper;
        }

        public void setYearper(double yearper) {
            this.yearper = yearper;
        }

        public String getDeal_time() {
            return deal_time;
        }

        public void setDeal_time(String deal_time) {
            this.deal_time = deal_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getFeed_time() {
            return feed_time;
        }

        public void setFeed_time(String feed_time) {
            this.feed_time = feed_time;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getShichang() {
            return shichang;
        }

        public void setShichang(String shichang) {
            this.shichang = shichang;
        }

        public String getBtips() {
            return btips;
        }

        public void setBtips(String btips) {
            this.btips = btips;
        }
    }
}
