package com.whale.beewin.bean;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class GoodInfo {

    /**
     * goodinfo : {"id":"713","menu_id":"1","qtype":"个月","sort":"98","qishu":"198","name":"蜂赢01号","yongjin":"","price":"100.00","totalprice":"50000.00","totaltime":"1","yearper":"8.00","saled":"100.00","begaintime":"T(成交日)+1","paytype":"先息后本","commision":"","old_price":"","image":"","detail":"http://img2.judayouyuan.com/Public/Uploads/20170330/14908423841945.png","status":"1","time":"2017-04-02 05:05:31","remone":"0.00"}
     */

    private GoodinfoBean goodinfo;

    public GoodinfoBean getGoodinfo() {
        return goodinfo;
    }

    public void setGoodinfo(GoodinfoBean goodinfo) {
        this.goodinfo = goodinfo;
    }

    public static class GoodinfoBean {
        /**
         * id : 713
         * menu_id : 1
         * qtype : 个月
         * sort : 98
         * qishu : 198
         * name : 蜂赢01号
         * yongjin :
         * price : 100.00
         * totalprice : 50000.00
         * totaltime : 1
         * yearper : 8.00
         * saled : 100.00
         * begaintime : T(成交日)+1
         * paytype : 先息后本
         * commision :
         * old_price :
         * image :
         * detail : http://img2.judayouyuan.com/Public/Uploads/20170330/14908423841945.png
         * status : 1
         * time : 2017-04-02 05:05:31
         * remone : 0.00
         */

        private String id;
        private String menu_id;
        private String qtype;
        private String sort;
        private String qishu;
        private String name;
        private String yongjin;
        private String price;
        private String totalprice;
        private String totaltime;
        private String yearper;
        private String saled;
        private String begaintime;
        private String paytype;
        private String commision;
        private String old_price;
        private String image;
        private String detail;
        private String status;
        private String time;
        private String remone;

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

        public String getYongjin() {
            return yongjin;
        }

        public void setYongjin(String yongjin) {
            this.yongjin = yongjin;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
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

        public String getRemone() {
            return remone;
        }

        public void setRemone(String remone) {
            this.remone = remone;
        }
    }
}
