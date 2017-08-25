package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class MyCard {
    private List<MlistBean> mlist;

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class MlistBean {
        /**
         * id : 99827
         * score : 20
         * sscore : 20
         * isfeed : 0
         * stype : 1
         * add_time : 无
         * days : 永久
         * sxsj : 无
         * yxq : 永久
         */

        private String id;
        private String score;
        private String sscore;
        private String isfeed;
        private String stype;
        private String add_time;
        private String days;
        private String sxsj;
        private String yxq;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getSscore() {
            return sscore;
        }

        public void setSscore(String sscore) {
            this.sscore = sscore;
        }

        public String getIsfeed() {
            return isfeed;
        }

        public void setIsfeed(String isfeed) {
            this.isfeed = isfeed;
        }

        public String getStype() {
            return stype;
        }

        public void setStype(String stype) {
            this.stype = stype;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getSxsj() {
            return sxsj;
        }

        public void setSxsj(String sxsj) {
            this.sxsj = sxsj;
        }

        public String getYxq() {
            return yxq;
        }

        public void setYxq(String yxq) {
            this.yxq = yxq;
        }
    }
}
