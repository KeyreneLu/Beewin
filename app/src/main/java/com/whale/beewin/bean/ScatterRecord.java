package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class ScatterRecord {
    private List<MlistBean> mlist;

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class MlistBean {
        /**
         * id : 983
         * shichang : 3
         * tamount : 2000.00
         * add_time : 2017-03-31 12:03
         * end_time : 1498881819
         * isover : dealison
         */

        private String id;
        private String shichang;
        private String tamount;
        private String add_time;
        private String end_time;
        private String isover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShichang() {
            return shichang;
        }

        public void setShichang(String shichang) {
            this.shichang = shichang;
        }

        public String getTamount() {
            return tamount;
        }

        public void setTamount(String tamount) {
            this.tamount = tamount;
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

        public String getIsover() {
            return isover;
        }

        public void setIsover(String isover) {
            this.isover = isover;
        }
    }
}
