package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class Income {
    private List<MlistBean> mlist;

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class MlistBean {
        /**
         * id : 13262718
         * uid : 864050
         * action : sreward
         * score : 0.21
         * descripthion : 特权本金收益
         * add_time : 1490123230
         * ftime : <span>星期三</span><span>03-22</span>
         */

        private String id;
        private String uid;
        private String action;
        private String score;
        private String descripthion;
        private String add_time;
        private String ftime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

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

        public String getDescripthion() {
            return descripthion;
        }

        public void setDescripthion(String descripthion) {
            this.descripthion = descripthion;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }
    }
}
