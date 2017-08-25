package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class Banner {
    /**
     * alist : [{"aid":"2","type":"1","title":"\"蜂赢金服2月份运营报告","aurl":"https://yunyingbaogao.kuaizhan.com/fp/page/display/58b621bc39d9de685d5c3707","aimg":"http://img3.judayouyuan.com/static/img/201604/slideimgs/ios20170306slide1604.png","hsdesc":"蜂赢金服2月份运营报告","hspic":"http://img3.judayouyuan.com/static/img/201604/share_20170306.png"},{"aid":"3","type":"2","title":"","aurl":"WelfareVC","aimg":"http://img3.judayouyuan.com/static/img/201604/slideimgs/ios20160425slidesantou.png","hsdesc":"","hspic":""},{"aid":"4","type":"1","title":"\"红包炸弹","aurl":"http://m1.judayouyuan.com/index.php?g=App&m=Index&a=redbomb","aimg":"http://img3.judayouyuan.com/static/img/201604/slideimgs/ios20160425slidehongbao.png","hsdesc":"红包炸弹","hspic":"http://m1.judayouyuan.com/static/img/201604/hongshare.png"}]
     * mlist : []
     * uid : 0
     * isupdate : 1
     * version : 29
     * ucontent : 增加新功能
     * downurl : http://m1.judayouyuan.com/beewin.apk
     * ujiangli : null
     * ujifen : null
     * usharepic : null
     */

    private String uid;
    private String isupdate;
    private String version;
    private String ucontent;
    private String downurl;
    private Object ujiangli;
    private Object ujifen;
    private Object usharepic;
    private List<AlistBean> alist;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(String isupdate) {
        this.isupdate = isupdate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUcontent() {
        return ucontent;
    }

    public void setUcontent(String ucontent) {
        this.ucontent = ucontent;
    }

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public Object getUjiangli() {
        return ujiangli;
    }

    public void setUjiangli(Object ujiangli) {
        this.ujiangli = ujiangli;
    }

    public Object getUjifen() {
        return ujifen;
    }

    public void setUjifen(Object ujifen) {
        this.ujifen = ujifen;
    }

    public Object getUsharepic() {
        return usharepic;
    }

    public void setUsharepic(Object usharepic) {
        this.usharepic = usharepic;
    }

    public List<AlistBean> getAlist() {
        return alist;
    }

    public void setAlist(List<AlistBean> alist) {
        this.alist = alist;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "uid='" + uid + '\'' +
                ", isupdate='" + isupdate + '\'' +
                ", version='" + version + '\'' +
                ", ucontent='" + ucontent + '\'' +
                ", downurl='" + downurl + '\'' +
                ", ujiangli=" + ujiangli +
                ", ujifen=" + ujifen +
                ", usharepic=" + usharepic +
                '}';
    }

    public static class AlistBean {
        /**
         * aid : 2
         * type : 1
         * title : "蜂赢金服2月份运营报告
         * aurl : https://yunyingbaogao.kuaizhan.com/fp/page/display/58b621bc39d9de685d5c3707
         * aimg : http://img3.judayouyuan.com/static/img/201604/slideimgs/ios20170306slide1604.png
         * hsdesc : 蜂赢金服2月份运营报告
         * hspic : http://img3.judayouyuan.com/static/img/201604/share_20170306.png
         */

        private String aid;
        private String type;
        private String title;
        private String aurl;
        private String aimg;
        private String hsdesc;
        private String hspic;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAurl() {
            return aurl;
        }

        public void setAurl(String aurl) {
            this.aurl = aurl;
        }

        public String getAimg() {
            return aimg;
        }

        public void setAimg(String aimg) {
            this.aimg = aimg;
        }

        public String getHsdesc() {
            return hsdesc;
        }

        public void setHsdesc(String hsdesc) {
            this.hsdesc = hsdesc;
        }

        public String getHspic() {
            return hspic;
        }

        public void setHspic(String hspic) {
            this.hspic = hspic;
        }
    }
}
