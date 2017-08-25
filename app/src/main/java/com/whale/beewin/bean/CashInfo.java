package com.whale.beewin.bean;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class CashInfo {
    /**
     * sinfo : {"id":"8","cid":"2","score":"2","bscore":"6575","desp":"加息券将自动提高钱包综合收益率，在有效期内本券将提高钱包综合年化2%收益率，每日限购1份。","despuse":"加息券直接生效，无需操作，生效记录可点击首页钱包查看。","is_open":"1","days":"1天","add_time":"1463649727","file_url":"56ebb16d3900a.png","sort":"9","title":"2% 加息券"}
     */

    private SinfoBean sinfo;

    public SinfoBean getSinfo() {
        return sinfo;
    }

    public void setSinfo(SinfoBean sinfo) {
        this.sinfo = sinfo;
    }

    public static class SinfoBean {
        /**
         * id : 8
         * cid : 2
         * score : 2
         * bscore : 6575
         * desp : 加息券将自动提高钱包综合收益率，在有效期内本券将提高钱包综合年化2%收益率，每日限购1份。
         * despuse : 加息券直接生效，无需操作，生效记录可点击首页钱包查看。
         * is_open : 1
         * days : 1天
         * add_time : 1463649727
         * file_url : 56ebb16d3900a.png
         * sort : 9
         * title : 2% 加息券
         */

        private String id;
        private String cid;
        private String score;
        private String bscore;
        private String desp;
        private String despuse;
        private String is_open;
        private String days;
        private String add_time;
        private String file_url;
        private String sort;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getBscore() {
            return bscore;
        }

        public void setBscore(String bscore) {
            this.bscore = bscore;
        }

        public String getDesp() {
            return desp;
        }

        public void setDesp(String desp) {
            this.desp = desp;
        }

        public String getDespuse() {
            return despuse;
        }

        public void setDespuse(String despuse) {
            this.despuse = despuse;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
