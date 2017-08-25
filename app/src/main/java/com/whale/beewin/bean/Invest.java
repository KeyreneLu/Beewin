package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class Invest {
    private List<MlistBean> mlist;

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class MlistBean {
        /**
         * id : 708
         * menu_id : 2
         * qtype : 天
         * sort : 99
         * qishu : 111
         * name : 新手标（限购1次）
         * price : 1000.00
         * totalprice : 1000000.00
         * totaltime : 10
         * yearper : 16.00
         * saled : 86.05
         * begaintime : T(成交日)+1
         * paytype : 先息后本
         * commision :
         * old_price :
         * status : 1
         * time : 2017-03-29 10:00:01
         */

        private String id;
        private String menu_id;
        private String qtype;
        private String sort;
        private String qishu;
        private String name;
        private String price;
        private String totalprice;
        private String totaltime;
        private String yearper;
        private String saled;
        private String begaintime;
        private String paytype;
        private String commision;
        private String old_price;
        private String status;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getQtype() {
            return qtype;
        }

        public void setQtype(String qtype) {
            this.qtype = qtype;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getQishu() {
            return qishu;
        }

        public void setQishu(String qishu) {
            this.qishu = qishu;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public String getTotaltime() {
            return totaltime;
        }

        public void setTotaltime(String totaltime) {
            this.totaltime = totaltime;
        }

        public String getYearper() {
            return yearper;
        }

        public void setYearper(String yearper) {
            this.yearper = yearper;
        }

        public String getSaled() {
            return saled;
        }

        public void setSaled(String saled) {
            this.saled = saled;
        }

        public String getBegaintime() {
            return begaintime;
        }

        public void setBegaintime(String begaintime) {
            this.begaintime = begaintime;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getCommision() {
            return commision;
        }

        public void setCommision(String commision) {
            this.commision = commision;
        }

        public String getOld_price() {
            return old_price;
        }

        public void setOld_price(String old_price) {
            this.old_price = old_price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
