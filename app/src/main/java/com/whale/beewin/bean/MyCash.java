package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class MyCash {
    /**
     * imoney : 32919.49
     * ugmoney : 2919.49
     * mlist : [{"action":"djquan","score":"10","add_time":"1490941221","end_time":"2017-05-01","descripthion":"10 元现金券","isfeed":"0","days":"永久","mimg":"http://img3.judayouyuan.com/static/img/201604/djquan0.png"},{"action":"lottery","score":"10000.00","add_time":"1491382090","descripthion":"10000 元体验金1","isfeed":"0","end_time":"2017-04-06","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"},{"action":"lottery","score":"10000.00","add_time":"1491381944","descripthion":"10000 元体验金1","isfeed":"0","end_time":"2017-04-06","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"},{"action":"lottery","score":"10000.00","add_time":"1491381815","descripthion":"10000 元体验金1","isfeed":"0","end_time":"2017-04-06","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"},{"action":"lottery","score":"10000.00","add_time":"1490079359","descripthion":"10000 元体验金1","isfeed":1,"end_time":"2017-03-22","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"},{"action":"lottery","score":"10000.00","add_time":"1490079347","descripthion":"10000 元体验金1","isfeed":1,"end_time":"2017-03-22","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"},{"action":"lottery","score":"1000.00","add_time":"1489806065","descripthion":"转盘抽奖1000元特权本金","isfeed":1,"end_time":"2017-03-19","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"},{"action":"lottery","score":"10000.00","add_time":"1489724713","descripthion":"10000 元体验金1","isfeed":1,"end_time":"2017-03-18","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"},{"action":"lottery","score":"10000.00","add_time":"1489724605","descripthion":"10000 元体验金1","isfeed":1,"end_time":"2017-03-18","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"},{"action":"lottery","score":"10000.00","add_time":"1489724513","descripthion":"10000 元体验金1","isfeed":1,"end_time":"2017-03-18","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"},{"action":"lottery","score":"10000.00","add_time":"1489724004","descripthion":"10000 元体验金1","isfeed":1,"end_time":"2017-03-18","days":"1天","mimg":"http://img3.judayouyuan.com/static/img/201604/lottery0.png"}]
     */

    private String imoney;
    private String ugmoney;
    private List<MlistBean> mlist;

    public String getImoney() {
        return imoney;
    }

    public void setImoney(String imoney) {
        this.imoney = imoney;
    }

    public String getUgmoney() {
        return ugmoney;
    }

    public void setUgmoney(String ugmoney) {
        this.ugmoney = ugmoney;
    }

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class MlistBean {
        /**
         * action : djquan
         * score : 10
         * add_time : 1490941221
         * end_time : 2017-05-01
         * descripthion : 10 元现金券
         * isfeed : 0
         * days : 永久
         * mimg : http://img3.judayouyuan.com/static/img/201604/djquan0.png
         */
        private String action;
        private String score;
        private String add_time;
        private String end_time;
        private String descripthion;
        private String isfeed;
        private String days;
        private String mimg;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getDescripthion() {
            return descripthion;
        }

        public void setDescripthion(String descripthion) {
            this.descripthion = descripthion;
        }

        public String getIsfeed() {
            return isfeed;
        }

        public void setIsfeed(String isfeed) {
            this.isfeed = isfeed;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getMimg() {
            return mimg;
        }

        public void setMimg(String mimg) {
            this.mimg = mimg;
        }
    }
}
