package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class InvestRecord {
    private List<GoodsBean> goods;

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * gprice : ￥100.00
         * btime : 2016-04-19
         * id : 263
         * qishu : 72
         * name : 蜂赢理财05号
         * totalprice : 60000.00
         * totaltime : 12
         * yearper : 12.00%
         * saled : 60000.00
         * begaintime : T(成交日)+1
         * paytype : 先息后本
         * status : 0
         * time : 2016-04-22 09:35:09
         * qtype : 个月
         * menu_id : 1
         * ost : 0
         * etime : 2017-04-19
         * mprice : ￥12.00
         * ostatus : 0
         */

        private String gprice;
        private String btime;
        private String id;
        private String qishu;
        private String name;
        private String totalprice;
        private String totaltime;
        private String yearper;
        private String saled;
        private String begaintime;
        private String paytype;
        private String status;
        private String time;
        private String qtype;
        private String menu_id;
        private String ost;
        private String etime;
        private String mprice;
        private String ostatus;

        public String getGprice() {
            return gprice;
        }

        public void setGprice(String gprice) {
            this.gprice = gprice;
        }

        public String getBtime() {
            return btime;
        }

        public void setBtime(String btime) {
            this.btime = btime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getQtype() {
            return qtype;
        }

        public void setQtype(String qtype) {
            this.qtype = qtype;
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getOst() {
            return ost;
        }

        public void setOst(String ost) {
            this.ost = ost;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getMprice() {
            return mprice;
        }

        public void setMprice(String mprice) {
            this.mprice = mprice;
        }

        public String getOstatus() {
            return ostatus;
        }

        public void setOstatus(String ostatus) {
            this.ostatus = ostatus;
        }
    }
}
