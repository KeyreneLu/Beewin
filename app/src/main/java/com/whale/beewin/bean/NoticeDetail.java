package com.whale.beewin.bean;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class NoticeDetail {
    /**
     * msg : {"id":"2988311","info":"部分中国银行批付异常通知","mtype":"系统公告","add_time":"2017-02-04 16:55:12","status":"0"}
     */

    private MsgBean msg;

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * id : 2988311
         * info : 部分中国银行批付异常通知
         * mtype : 系统公告
         * add_time : 2017-02-04 16:55:12
         * status : 0
         */

        private String id;
        private String info;
        private String content;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
