package com.whale.beewin.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class Reckoning {
    /**
     * t : 0
     * y : 0
     * n : 0
     * p : 1
     * d : 1
     * tnmae : 全部类型
     * nnmae : 全部时间
     * tnmaes : ["全部类型","充值提现","投资理财","互动奖励"]
     * months : {"0":"全年","10":"10月","11":"11月","12":"12月","09":"9月","08":"8月","07":"7月","06":"6月","05":"5月","04":"4月","03":"3月","02":"2月","01":"1月"}
     * nnmaes : {"0":"全部时间","2014":"2014年","2015":"2015年","2016":"2016年"}
     * slist : [{"id":"13434640","uid":"8918","action":"win","score":"+0.65","descripthion":"钱包收益","add_time":"1491770607","ftime":"今天7小时前","icon":"zz"},{"id":"13425023","uid":"8918","action":"win","score":"+0.65","descripthion":"钱包收益","add_time":"1491684532","ftime":"昨天04-09","icon":"zz"},{"id":"13415063","uid":"8918","action":"win","score":"+0.62","descripthion":"钱包收益","add_time":"1491598156","ftime":"前天04-08","icon":"zz"},{"id":"13409012","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标745回款","add_time":"1491552701","ftime":"星期五04-07","icon":"zz"},{"id":"13408986","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标744回款","add_time":"1491552102","ftime":"星期五04-07","icon":"zz"},{"id":"13408974","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标743回款","add_time":"1491552001","ftime":"星期五04-07","icon":"zz"},{"id":"13408958","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标742回款","add_time":"1491551801","ftime":"星期五04-07","icon":"zz"},{"id":"13407266","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标741回款","add_time":"1491531402","ftime":"星期五04-07","icon":"zz"},{"id":"13407064","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标740回款","add_time":"1491529801","ftime":"星期五04-07","icon":"zz"},{"id":"13407045","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标739回款","add_time":"1491529702","ftime":"星期五04-07","icon":"zz"},{"id":"13407026","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标738回款","add_time":"1491529401","ftime":"星期五04-07","icon":"zz"},{"id":"13407011","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标737回款","add_time":"1491529301","ftime":"星期五04-07","icon":"zz"},{"id":"13406993","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标736回款","add_time":"1491529201","ftime":"星期五04-07","icon":"zz"},{"id":"13406986","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标735回款","add_time":"1491529102","ftime":"星期五04-07","icon":"zz"},{"id":"13406953","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标734回款","add_time":"1491528901","ftime":"星期五04-07","icon":"zz"},{"id":"13406933","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标733回款","add_time":"1491528701","ftime":"星期五04-07","icon":"zz"},{"id":"13406902","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标732回款","add_time":"1491528502","ftime":"星期五04-07","icon":"zz"},{"id":"13405428","uid":"8918","action":"win","score":"+0.61","descripthion":"钱包收益","add_time":"1491511389","ftime":"星期五04-07","icon":"zz"},{"id":"13399941","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标362回款","add_time":"1491470401","ftime":"星期四04-06","icon":"zz"},{"id":"13399917","uid":"8918","action":"wingood","score":"+9.33","descripthion":"散投标361回款","add_time":"1491470001","ftime":"星期四04-06","icon":"zz"},{"id":"13399814","uid":"8918","action":"lottery","score":"-10000.00","descripthion":"体验金到期","add_time":"1491467892","ftime":"星期四04-06","icon":"sy"},{"id":"13399794","uid":"8918","action":"lottery","score":"-10000.00","descripthion":"体验金到期","add_time":"1491467746","ftime":"星期四04-06","icon":"sy"},{"id":"13399786","uid":"8918","action":"lottery","score":"-10000.00","descripthion":"体验金到期","add_time":"1491467621","ftime":"星期四04-06","icon":"sy"},{"id":"13396226","uid":"8918","action":"win","score":"+6.92","descripthion":"钱包收益","add_time":"1491425206","ftime":"星期四04-06","icon":"zz"},{"id":"13391035","uid":"8918","action":"lottery","score":"+10000.00","descripthion":"10000 元体验金1","add_time":"1491382090","ftime":"星期三04-05","icon":"sy"},{"id":"13391022","uid":"8918","action":"lottery","score":"+10000.00","descripthion":"10000 元体验金1","add_time":"1491381944","ftime":"星期三04-05","icon":"sy"},{"id":"13391015","uid":"8918","action":"lottery","score":"+10000.00","descripthion":"10000 元体验金1","add_time":"1491381815","ftime":"星期三04-05","icon":"sy"},{"id":"13390604","uid":"8918","action":"wingood","score":"+9.16","descripthion":"散投标1906回款","add_time":"1491375401","ftime":"星期三04-05","icon":"zz"},{"id":"13389692","uid":"8918","action":"jfcharge","score":"-1.00","descripthion":"花币充值","add_time":"1491364913","ftime":"星期三04-05","icon":"zz"},{"id":"13389667","uid":"8918","action":"jfcharge","score":"-1.00","descripthion":"花币充值","add_time":"1491364751","ftime":"星期三04-05","icon":"zz"},{"id":"13389653","uid":"8918","action":"jfcharge","score":"-1.00","descripthion":"花币充值","add_time":"1491364628","ftime":"星期三04-05","icon":"zz"},{"id":"13389642","uid":"8918","action":"jfcharge","score":"-1.00","descripthion":"花币充值","add_time":"1491364536","ftime":"星期三04-05","icon":"zz"},{"id":"13389625","uid":"8918","action":"jfcharge","score":"-1.00","descripthion":"花币充值","add_time":"1491364340","ftime":"星期三04-05","icon":"zz"},{"id":"13389593","uid":"8918","action":"jfcharge","score":"-1.00","descripthion":"花币充值","add_time":"1491363818","ftime":"星期三04-05","icon":"zz"},{"id":"13387362","uid":"8918","action":"win","score":"+0.61","descripthion":"钱包收益","add_time":"1491338719","ftime":"星期三04-05","icon":"zz"},{"id":"13380279","uid":"8918","action":"wingood","score":"+9.16","descripthion":"散投标1905回款","add_time":"1491271802","ftime":"星期二04-04","icon":"zz"},{"id":"13378389","uid":"8918","action":"win","score":"+0.59","descripthion":"钱包收益","add_time":"1491252480","ftime":"星期二04-04","icon":"zz"},{"id":"13372309","uid":"8918","action":"wingood","score":"+9.16","descripthion":"散投标1904回款","add_time":"1491201901","ftime":"星期一04-03","icon":"zz"},{"id":"13371393","uid":"8918","action":"wingood","score":"+9.16","descripthion":"散投标2041回款","add_time":"1491189801","ftime":"星期一04-03","icon":"zz"},{"id":"13371323","uid":"8918","action":"wingood","score":"+9.16","descripthion":"散投标2040回款","add_time":"1491189101","ftime":"星期一04-03","icon":"zz"}]
     */
    private String t;
    private String y;
    private String n;
    private String p;
    private String d;
    private String tnmae;
    private String nnmae;
    private MonthsBean months;
    private NnmaesBean nnmaes;
    private List<String> tnmaes;
    private List<SlistBean> slist;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getTnmae() {
        return tnmae;
    }

    public void setTnmae(String tnmae) {
        this.tnmae = tnmae;
    }

    public String getNnmae() {
        return nnmae;
    }

    public void setNnmae(String nnmae) {
        this.nnmae = nnmae;
    }

    public MonthsBean getMonths() {
        return months;
    }

    public void setMonths(MonthsBean months) {
        this.months = months;
    }

    public NnmaesBean getNnmaes() {
        return nnmaes;
    }

    public void setNnmaes(NnmaesBean nnmaes) {
        this.nnmaes = nnmaes;
    }

    public List<String> getTnmaes() {
        return tnmaes;
    }

    public void setTnmaes(List<String> tnmaes) {
        this.tnmaes = tnmaes;
    }

    public List<SlistBean> getSlist() {
        return slist;
    }

    public void setSlist(List<SlistBean> slist) {
        this.slist = slist;
    }

    public static class MonthsBean {
        /**
         * 0 : 全年
         * 10 : 10月
         * 11 : 11月
         * 12 : 12月
         * 09 : 9月
         * 08 : 8月
         * 07 : 7月
         * 06 : 6月
         * 05 : 5月
         * 04 : 4月
         * 03 : 3月
         * 02 : 2月
         * 01 : 1月
         */

        @SerializedName("0")
        private String _$0;
        @SerializedName("10")
        private String _$10;
        @SerializedName("11")
        private String _$11;
        @SerializedName("12")
        private String _$12;
        @SerializedName("09")
        private String _$09;
        @SerializedName("08")
        private String _$08;
        @SerializedName("07")
        private String _$07;
        @SerializedName("06")
        private String _$06;
        @SerializedName("05")
        private String _$05;
        @SerializedName("04")
        private String _$04;
        @SerializedName("03")
        private String _$03;
        @SerializedName("02")
        private String _$02;
        @SerializedName("01")
        private String _$01;

        public String get_$0() {
            return _$0;
        }

        public void set_$0(String _$0) {
            this._$0 = _$0;
        }

        public String get_$10() {
            return _$10;
        }

        public void set_$10(String _$10) {
            this._$10 = _$10;
        }

        public String get_$11() {
            return _$11;
        }

        public void set_$11(String _$11) {
            this._$11 = _$11;
        }

        public String get_$12() {
            return _$12;
        }

        public void set_$12(String _$12) {
            this._$12 = _$12;
        }

        public String get_$09() {
            return _$09;
        }

        public void set_$09(String _$09) {
            this._$09 = _$09;
        }

        public String get_$08() {
            return _$08;
        }

        public void set_$08(String _$08) {
            this._$08 = _$08;
        }

        public String get_$07() {
            return _$07;
        }

        public void set_$07(String _$07) {
            this._$07 = _$07;
        }

        public String get_$06() {
            return _$06;
        }

        public void set_$06(String _$06) {
            this._$06 = _$06;
        }

        public String get_$05() {
            return _$05;
        }

        public void set_$05(String _$05) {
            this._$05 = _$05;
        }

        public String get_$04() {
            return _$04;
        }

        public void set_$04(String _$04) {
            this._$04 = _$04;
        }

        public String get_$03() {
            return _$03;
        }

        public void set_$03(String _$03) {
            this._$03 = _$03;
        }

        public String get_$02() {
            return _$02;
        }

        public void set_$02(String _$02) {
            this._$02 = _$02;
        }

        public String get_$01() {
            return _$01;
        }

        public void set_$01(String _$01) {
            this._$01 = _$01;
        }
    }

    public static class NnmaesBean {
        /**
         * 0 : 全部时间
         * 2014 : 2014年
         * 2015 : 2015年
         * 2016 : 2016年
         */

        @SerializedName("0")
        private String _$0;
        @SerializedName("2014")
        private String _$2014;
        @SerializedName("2015")
        private String _$2015;
        @SerializedName("2016")
        private String _$2016;

        public String get_$0() {
            return _$0;
        }

        public void set_$0(String _$0) {
            this._$0 = _$0;
        }

        public String get_$2014() {
            return _$2014;
        }

        public void set_$2014(String _$2014) {
            this._$2014 = _$2014;
        }

        public String get_$2015() {
            return _$2015;
        }

        public void set_$2015(String _$2015) {
            this._$2015 = _$2015;
        }

        public String get_$2016() {
            return _$2016;
        }

        public void set_$2016(String _$2016) {
            this._$2016 = _$2016;
        }
    }

    public static class SlistBean {
        /**
         * id : 13434640
         * uid : 8918
         * action : win
         * score : +0.65
         * descripthion : 钱包收益
         * add_time : 1491770607
         * ftime : 今天7小时前
         * icon : zz
         */

        private String id;
        private String uid;
        private String action;
        private String score;
        private String descripthion;
        private String add_time;
        private String ftime;
        private String icon;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
