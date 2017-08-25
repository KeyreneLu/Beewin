package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/8 0008.
 */

public class Return {
    /**
     * detail : {"id":"6866","uid":"8918","did":"983","deal_sn":"BW2017030006867","duid":"828098","tamount":"100.00","sysmount":"0.00","gamount":"1.75","btype":"0","dstatus":"2","issys":"0","yearper":10.39,"shichang":"3","add_time":"2017-03-31 12:03","deal_time":"2017-03-31 12:03","end_time":"2017-07-01 12:03","feed_time":"1970-01-01 08:00","udescription":""}
     * mlist : [{"id":"38618","ftype":"0","tamount":"33.33","gamount":"0.58","deal_time":"2017-05-01 12:03","feed_time":"1970-01-01 08:00","add_time":"2017-03-31 12:03","duid":"828098"},{"id":"38619","ftype":"0","tamount":"33.33","gamount":"0.58","deal_time":"2017-05-31 12:03","feed_time":"1970-01-01 08:00","add_time":"2017-03-31 12:03","duid":"828098"},{"id":"38620","ftype":"0","tamount":"33.33","gamount":"0.58","deal_time":"2017-07-01 12:03","feed_time":"1970-01-01 08:00","add_time":"2017-03-31 12:03","duid":"828098"}]
     */

    private DetailBean detail;
    private List<MlistBean> mlist;

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class DetailBean {
        /**
         * id : 6866
         * uid : 8918
         * did : 983
         * deal_sn : BW2017030006867
         * duid : 828098
         * tamount : 100.00
         * sysmount : 0.00
         * gamount : 1.75
         * btype : 0
         * dstatus : 2
         * issys : 0
         * yearper : 10.39
         * shichang : 3
         * add_time : 2017-03-31 12:03
         * deal_time : 2017-03-31 12:03
         * end_time : 2017-07-01 12:03
         * feed_time : 1970-01-01 08:00
         * udescription :
         */

        private String id;
        private String uid;
        private String did;
        private String deal_sn;
        private String duid;
        private String tamount;
        private String sysmount;
        private String gamount;
        private String btype;
        private String dstatus;
        private String issys;
        private double yearper;
        private String shichang;
        private String add_time;
        private String deal_time;
        private String end_time;
        private String feed_time;
        private String udescription;

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

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getDeal_sn() {
            return deal_sn;
        }

        public void setDeal_sn(String deal_sn) {
            this.deal_sn = deal_sn;
        }

        public String getDuid() {
            return duid;
        }

        public void setDuid(String duid) {
            this.duid = duid;
        }

        public String getTamount() {
            return tamount;
        }

        public void setTamount(String tamount) {
            this.tamount = tamount;
        }

        public String getSysmount() {
            return sysmount;
        }

        public void setSysmount(String sysmount) {
            this.sysmount = sysmount;
        }

        public String getGamount() {
            return gamount;
        }

        public void setGamount(String gamount) {
            this.gamount = gamount;
        }

        public String getBtype() {
            return btype;
        }

        public void setBtype(String btype) {
            this.btype = btype;
        }

        public String getDstatus() {
            return dstatus;
        }

        public void setDstatus(String dstatus) {
            this.dstatus = dstatus;
        }

        public String getIssys() {
            return issys;
        }

        public void setIssys(String issys) {
            this.issys = issys;
        }

        public double getYearper() {
            return yearper;
        }

        public void setYearper(double yearper) {
            this.yearper = yearper;
        }

        public String getShichang() {
            return shichang;
        }

        public void setShichang(String shichang) {
            this.shichang = shichang;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
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

        public String getUdescription() {
            return udescription;
        }

        public void setUdescription(String udescription) {
            this.udescription = udescription;
        }
    }

    public static class MlistBean {
        /**
         * id : 38618
         * ftype : 0
         * tamount : 33.33
         * gamount : 0.58
         * deal_time : 2017-05-01 12:03
         * feed_time : 1970-01-01 08:00
         * add_time : 2017-03-31 12:03
         * duid : 828098
         */

        private String id;
        private String ftype;
        private String tamount;
        private String gamount;
        private String deal_time;
        private String feed_time;
        private String add_time;
        private String duid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFtype() {
            return ftype;
        }

        public void setFtype(String ftype) {
            this.ftype = ftype;
        }

        public String getTamount() {
            return tamount;
        }

        public void setTamount(String tamount) {
            this.tamount = tamount;
        }

        public String getGamount() {
            return gamount;
        }

        public void setGamount(String gamount) {
            this.gamount = gamount;
        }

        public String getDeal_time() {
            return deal_time;
        }

        public void setDeal_time(String deal_time) {
            this.deal_time = deal_time;
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

        public String getDuid() {
            return duid;
        }

        public void setDuid(String duid) {
            this.duid = duid;
        }
    }
}
