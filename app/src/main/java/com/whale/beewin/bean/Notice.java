package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class Notice {
    private List<SlistBean> slist;

    public List<SlistBean> getSlist() {
        return slist;
    }

    public void setSlist(List<SlistBean> slist) {
        this.slist = slist;
    }

    public static class SlistBean {
        /**
         * id : 3070183
         * info : 认证支付中国银行临时维护通知
         * mtype : 系统通知
         * add_time : 2017-03-20 11:52:21
         * status : 0
         */

        private String id;
        private String info;
        private String mtype;
        private String add_time;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getMtype() {
            return mtype;
        }

        public void setMtype(String mtype) {
            this.mtype = mtype;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
