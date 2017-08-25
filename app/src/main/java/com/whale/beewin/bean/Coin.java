package com.whale.beewin.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4 0004.
 */

public class Coin {
    /**
     * jifen : 315
     * slist : [{"id":"2348003","uid":"864050","action":"52","score":"5","add_time":"1490665626","icon":"h51","descripthion":"转盘抽奖","ftime":"星期二03-28"},{"id":"2304048","uid":"864050","action":"52","score":"5","add_time":"1489998433","icon":"h51","descripthion":"转盘抽奖","ftime":"星期一03-20"},{"id":"2285586","uid":"864050","action":"1","score":"100","add_time":"1489721646","icon":"h1","descripthion":"花币充值","ftime":"星期五03-17"},{"id":"2280256","uid":"864050","action":"1","score":"100","add_time":"1489644359","icon":"h1","descripthion":"花币充值","ftime":"星期四03-16"},{"id":"2279638","uid":"864050","action":"1","score":"100","add_time":"1489636284","icon":"h1","descripthion":"花币充值","ftime":"星期四03-16"},{"id":"2274130","uid":"864050","action":"52","score":"5","add_time":"1489546627","icon":"h51","descripthion":"转盘抽奖","ftime":"星期三03-15"},{"id":"2270122","uid":"864050","action":"111","score":"-10","add_time":"1489478094","icon":"h61","descripthion":"连连好运扣除","ftime":"星期二03-14"},{"id":"2264291","uid":"864050","action":"52","score":"5","add_time":"1489373612","icon":"h51","descripthion":"转盘抽奖","ftime":"星期一03-13"},{"id":"2247905","uid":"864050","action":"111","score":"-10","add_time":"1489113795","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五03-10"},{"id":"2247902","uid":"864050","action":"112","score":"14","add_time":"1489113785","icon":"hq","descripthion":"连连好运奖励","ftime":"星期五03-10"},{"id":"2247901","uid":"864050","action":"111","score":"-10","add_time":"1489113785","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五03-10"},{"id":"2247900","uid":"864050","action":"111","score":"-10","add_time":"1489113774","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五03-10"},{"id":"2247899","uid":"864050","action":"111","score":"-10","add_time":"1489113762","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五03-10"},{"id":"2247889","uid":"864050","action":"52","score":"5","add_time":"1489113702","icon":"h51","descripthion":"转盘抽奖","ftime":"星期五03-10"},{"id":"2237607","uid":"864050","action":"52","score":"5","add_time":"1488951563","icon":"h51","descripthion":"转盘抽奖","ftime":"星期三03-08"},{"id":"2229682","uid":"864050","action":"52","score":"5","add_time":"1488851942","icon":"h51","descripthion":"转盘抽奖","ftime":"星期二03-07"},{"id":"2207342","uid":"864050","action":"52","score":"5","add_time":"1488580786","icon":"h51","descripthion":"转盘抽奖","ftime":"星期六03-04"},{"id":"2197631","uid":"864050","action":"52","score":"5","add_time":"1488412002","icon":"h51","descripthion":"转盘抽奖","ftime":"星期四03-02"},{"id":"2163447","uid":"864050","action":"111","score":"-10","add_time":"1487951132","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163446","uid":"864050","action":"111","score":"-10","add_time":"1487951122","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163445","uid":"864050","action":"112","score":"14","add_time":"1487951112","icon":"hq","descripthion":"连连好运奖励","ftime":"星期五02-24"},{"id":"2163444","uid":"864050","action":"111","score":"-10","add_time":"1487951112","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163443","uid":"864050","action":"111","score":"-10","add_time":"1487951096","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163442","uid":"864050","action":"112","score":"14","add_time":"1487951087","icon":"hq","descripthion":"连连好运奖励","ftime":"星期五02-24"},{"id":"2163441","uid":"864050","action":"111","score":"-10","add_time":"1487951087","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163438","uid":"864050","action":"112","score":"14","add_time":"1487951035","icon":"hq","descripthion":"连连好运奖励","ftime":"星期五02-24"},{"id":"2163437","uid":"864050","action":"111","score":"-10","add_time":"1487951035","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163435","uid":"864050","action":"111","score":"-10","add_time":"1487951022","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163434","uid":"864050","action":"112","score":"14","add_time":"1487951012","icon":"hq","descripthion":"连连好运奖励","ftime":"星期五02-24"},{"id":"2163433","uid":"864050","action":"111","score":"-10","add_time":"1487951012","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163432","uid":"864050","action":"112","score":"14","add_time":"1487951000","icon":"hq","descripthion":"连连好运奖励","ftime":"星期五02-24"},{"id":"2163431","uid":"864050","action":"111","score":"-10","add_time":"1487951000","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163429","uid":"864050","action":"111","score":"-10","add_time":"1487950988","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163428","uid":"864050","action":"112","score":"14","add_time":"1487950978","icon":"hq","descripthion":"连连好运奖励","ftime":"星期五02-24"},{"id":"2163427","uid":"864050","action":"111","score":"-10","add_time":"1487950978","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2163426","uid":"864050","action":"111","score":"-10","add_time":"1487950965","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2161305","uid":"864050","action":"112","score":"14","add_time":"1487934017","icon":"hq","descripthion":"连连好运奖励","ftime":"星期五02-24"},{"id":"2161304","uid":"864050","action":"111","score":"-10","add_time":"1487934017","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2161302","uid":"864050","action":"111","score":"-20","add_time":"1487934006","icon":"h61","descripthion":"连连好运扣除","ftime":"星期五02-24"},{"id":"2161298","uid":"864050","action":"112","score":"28","add_time":"1487933996","icon":"hq","descripthion":"连连好运奖励","ftime":"星期五02-24"}]
     */

    private String jifen;
    private List<SlistBean> slist;

    public String getJifen() {
        return jifen;
    }

    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    public List<SlistBean> getSlist() {
        return slist;
    }

    public void setSlist(List<SlistBean> slist) {
        this.slist = slist;
    }

    public static class SlistBean {
        /**
         * id : 2348003
         * uid : 864050
         * action : 52
         * score : 5
         * add_time : 1490665626
         * icon : h51
         * descripthion : 转盘抽奖
         * ftime : 星期二03-28
         */

        private String id;
        private String uid;
        private String action;
        private String score;
        private String add_time;
        private String icon;
        private String descripthion;
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

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDescripthion() {
            return descripthion;
        }

        public void setDescripthion(String descripthion) {
            this.descripthion = descripthion;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }
    }
}
