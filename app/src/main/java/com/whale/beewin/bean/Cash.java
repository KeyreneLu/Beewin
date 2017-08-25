package com.whale.beewin.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class Cash {

    private List<MlistBean> mlist;

    public List<MlistBean> getMlist() {
        return mlist;
    }

    public void setMlist(List<MlistBean> mlist) {
        this.mlist = mlist;
    }

    public static class MlistBean implements Parcelable {
        /**
         * id : 8
         * cid : 2
         * score : 2
         * bscore : 6575
         * days : 1天
         * file_url : http://img1.judayouyuan.com/Public/Uploads/56ebb16d3900a.png
         * is_open : 1
         * title : 2% 加息券
         */
        private String id;
        private String cid;
        private String score;
        private String bscore;
        private String days;
        private String file_url;
        private String is_open;
        private String title;

        @Override
        public String toString() {
            return "MlistBean{" +
                    "id='" + id + '\'' +
                    ", cid='" + cid + '\'' +
                    ", score='" + score + '\'' +
                    ", bscore='" + bscore + '\'' +
                    ", days='" + days + '\'' +
                    ", file_url='" + file_url + '\'' +
                    ", is_open='" + is_open + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

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

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.cid);
            dest.writeString(this.score);
            dest.writeString(this.bscore);
            dest.writeString(this.days);
            dest.writeString(this.file_url);
            dest.writeString(this.is_open);
            dest.writeString(this.title);
        }

        public MlistBean() {
        }

        protected MlistBean(Parcel in) {
            this.id = in.readString();
            this.cid = in.readString();
            this.score = in.readString();
            this.bscore = in.readString();
            this.days = in.readString();
            this.file_url = in.readString();
            this.is_open = in.readString();
            this.title = in.readString();
        }

        public static final Parcelable.Creator<MlistBean> CREATOR = new Parcelable.Creator<MlistBean>() {
            @Override
            public MlistBean createFromParcel(Parcel source) {
                return new MlistBean(source);
            }

            @Override
            public MlistBean[] newArray(int size) {
                return new MlistBean[size];
            }
        };
    }

    @Override
    public String toString() {
        return "Cash{" +
                "mlist=" + mlist +
                '}';
    }
}
