package com.whale.beewin.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/4/4 0004.
 */

public class Game {
    /**
     * hlist : [{"hid":"0","htype":"2","hurl":"http://m1.judayouyuan.com/index.php?g=App&m=Index&a=gift201704&mid=0","htitle":"春季领好礼","himg":"http://m1.judayouyuan.com/static/img/201604/20170405slidetestshare.png","hstitle":"春季领好礼,每天都可以领哦","hsdesc":"春季领好礼","hspic":"http://m1.judayouyuan.com/static/img/201604/20170405slidetestshare.png","hsdo":"1","tiptype":"0","gtype":"1","c1":"0","c2":"0","c3":"0","c4":"0","c5":"0","c6":"0","c7":"0","c8":"0","c9":"0","c10":"1"},{"hid":"1","htype":"2","hurl":"http://m1.judayouyuan.com/index.php?g=App&m=Index&a=luckcircle&mid=0","htitle":"好运大转盘","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_zp.png","hstitle":"幸运大转盘,每天都可以领钱哦","hsdesc":"好礼天天有！点击\u201c转\u201d取iPhone6s","hspic":"http://m1.judayouyuan.com/static/zhuanpan/image/zhuanPanBg.png","hsdo":"1","tiptype":"0","gtype":"1","c1":"1","c2":"0","c3":"0","c4":"0","c5":"0","c6":"0","c7":"1","c8":"1","c9":"1","c10":"1"},{"hid":"2","htype":"2","hurl":"http://m1.judayouyuan.com/game/1010/index.php?mid=0","htitle":"俄罗斯金币","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_elsjb.png","hstitle":"从没玩过这样的俄罗斯方块！","hsdesc":"闯关还可以得花币,这个游戏太好玩了！","hspic":"http://img2.judayouyuan.com/game/1010/img/55d6af11a5296_wx.jpg","hsdo":"1","tiptype":"0","gtype":"1","c1":"1","c2":"0","c3":"0","c4":"0","c5":"0","c6":"0","c7":"1","c8":"0","c9":"0","c10":"0"},{"hid":"10","htype":"2","hurl":"http://m1.judayouyuan.com/index.php?g=App&m=Index&a=gamellf&mid=0","htitle":"连连好运","himg":"http://m1.judayouyuan.com/static/img/images/share_gamellf.png","hstitle":"我一分钟赚了1000花币,快来试试！","hsdesc":"我一分钟赚了1000花币,你也来试试-看你能赚多少！","hspic":"http://m1.judayouyuan.com/static/img/images/share_gamellf.png","hsdo":"1","tiptype":"0","gtype":"1","c1":"1","c2":"0","c3":"0","c4":"0","c5":"0","c6":"0","c7":"1","c8":"0","c9":"0","c10":"0"},{"hid":"11","htype":"2","hurl":"http://m1.judayouyuan.com/index.php?g=App&m=Index&a=redbomb&mid=0","htitle":"红包炸弹","himg":"http://img1.judayouyuan.com/static/img/201604/hongshare.png","hstitle":"我赚了好多积分,快来试试！","hsdesc":"我挂了一晚上,得了2万积分,你也来试试-看你能得多少！","hspic":"http://img1.judayouyuan.com/static/img/201604/hongshare.png","hsdo":"1","tiptype":"0","gtype":"1","c1":"1","c2":"0","c3":"0","c4":"0","c5":"0","c6":"0","c7":"1","c8":"0","c9":"0","c10":"0"},{"hid":"3","htype":"2","hurl":"http://m1.judayouyuan.com/game/h5/2016/fstgddp/index.htm","htitle":"沙漠淘金币","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_smtjb.png","hstitle":"从没玩过这样的消消乐！","hsdesc":"闯关还可以得花币,这个游戏太好玩了！","hspic":"http://img2.judayouyuan.com/game/1010/img/55d6af11a5296_wx.jpg","hsdo":"1","tiptype":"0","gtype":"1","c1":"1","c2":"0","c3":"0","c4":"0","c5":"0","c6":"0","c7":"1","c8":"0","c9":"0","c10":"0"},{"hid":"9","htype":"2","hurl":"http://m1.judayouyuan.com/game/benpaobajingbi2/index.htm?fapp=1&mid=0","htitle":"奔跑吧金币2","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_bpbjb2.png","hstitle":"3D奔跑吧蜜蜂！","hsdesc":"3D奔跑吧蜜蜂狂奔10000米,敢来试试么！","hspic":"http://m1.judayouyuan.com/game/benpaobajingbi2/css/icon.png","hsdo":"1","tiptype":"0","gtype":"1","c1":"1","c2":"0","c3":"0","c4":"0","c5":"0","c6":"0","c7":"1","c8":"0","c9":"0","c10":"0"},{"hid":"4","htype":"2","hurl":"http://m1.judayouyuan.com/game/csxxl/index.html","htitle":"蜂蜂小镇","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_ffxz.png","hstitle":"蜂蜂小镇","hsdesc":"建造属于你的城市","hspic":"http://m1.judayouyuan.com/static/img/images/share_fycsxxl.png","hsdo":"0","tiptype":"0","gtype":"2","c1":"0","c2":"0","c3":"1","c4":"0","c5":"0","c6":"0","c7":"0","c8":"1","c9":"0","c10":"0"},{"hid":"5","htype":"2","hurl":"http://m1.judayouyuan.com/index.php?g=App&m=Index&a=birthday&mid=0","htitle":"生日红包","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_srhb.png","hstitle":"祝我生日快乐吧","hsdesc":"祝我生日快乐吧","hspic":"http://img1.judayouyuan.com/static/img/201604/share_birthday.png","hsdo":"0","tiptype":"0","gtype":"3","c1":"0","c2":"1","c3":"0","c4":"0","c5":"0","c6":"0","c7":"0","c8":"1","c9":"0","c10":"0"},{"hid":"6","htype":"2","hurl":"http://m1.judayouyuan.com/game/fruit/yzm/sgrz/sgrz.htm?mid=0","htitle":"红包忍者","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_hbrz.png","hstitle":"忍者领红包","hsdesc":"从来没玩过这么好玩的活动","hspic":"http://m1.judayouyuan.com/static/img/201604/55ee6a46c9bf9_wx.jpg","hsdo":"0","tiptype":"0","gtype":"3","c1":"0","c2":"0","c3":"0","c4":"0","c5":"1","c6":"0","c7":"0","c8":"1","c9":"0","c10":"0"},{"hid":"7","htype":"2","hurl":"http://m1.judayouyuan.com/game/shouhb/index.htm?mid=0","htitle":"红包削削乐","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hdhbxxl.png","hstitle":"数钱数到手抽筋，可申请提现哦！","hsdesc":"我是红包之神，拆出乐最高限额!","hspic":"https://img.alicdn.com/imgextra/i1/534090410/TB23Um7hFXXXXbMXXXXXXXXXXXX_!!534090410.png","hsdo":"0","tiptype":"2","gtype":"0","c1":"0","c2":"0","c3":"0","c4":"1","c5":"0","c6":"0","c7":"0","c8":"1","c9":"0","c10":"0"}]
     * tlist : [{"hid":"0","hurl":"http://fengfengkeji.kuaizhan.com/fp/page/display/5704b9424b0c49606e2ed016","htitle":"蜂蜂时光机","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_ffsgj.png","hstitle":"蜂赢金服","hsdesc":"财富丰盈 收益杠杠滴","hspic":"http://m1.judayouyuan.com/static/img/images/share_yearago10.png"},{"hid":"1","hurl":"http://fengfengkeji.kuaizhan.com/fp/page/display/57197e8bd068b8c864780aab","htitle":"做做小学题","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_xxs.png","hstitle":"我是小学生我光荣","hsdesc":"\u201c小学生\u201d才适合投资P2P,你小学毕业了吗？","hspic":"http://pic.kuaizhan.com/g1/M01/8F/50/CgpQU1cjAf2AVYcmAABCuatjEhA0229325/imageView/v1/thumbnail/640x0"},{"hid":"2","hurl":"http://fengfengkeji.kuaizhan.com/fp/page/display/5795e17eb86369ea7a2fcbfe","htitle":"搭建互金平台","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_yxhjpt.png","hstitle":"搭建互金平台","hsdesc":"教你如何运营好移动互联网金融平台","hspic":"http://pic.kuaizhan.com/g2/M00/B5/77/wKjmqleVdTiAGP6JAAEteKcBPgY6760657/imageView/v1/thumbnail/640x0"},{"hid":"3","hurl":"http://fengfengkeji.kuaizhan.com/fp/page/display/575e1b22b9a395c66deb08d4","htitle":"员外理财记","himg":"http://m1.judayouyuan.com/static/img/201604/h5coin/hd_ywlcj.png","hstitle":"员外理财记","hsdesc":"看清朝财阀如何\u201c行贿\u201d高官！","hspic":"http://pic.kuaizhan.com/g2/M00/C4/9A/CgpQVFdjs0GATo0NAAB6IhPXjc89664315/imageView/v1/thumbnail/640x0"}]
     * uid : 0
     * ujiangli : 0
     * ujifen : 0
     * usharepic : http://img2.judayouyuan.com/imgpublic/ticket_.jpg
     */

    private String uid;
    private double ujiangli;
    private int ujifen;
    private String usharepic;
    private List<HlistBean> hlist;
    private List<TlistBean> tlist;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getUjiangli() {
        return ujiangli;
    }

    public void setUjiangli(double ujiangli) {
        this.ujiangli = ujiangli;
    }

    public int getUjifen() {
        return ujifen;
    }

    public void setUjifen(int ujifen) {
        this.ujifen = ujifen;
    }

    public String getUsharepic() {
        return usharepic;
    }

    public void setUsharepic(String usharepic) {
        this.usharepic = usharepic;
    }

    public List<HlistBean> getHlist() {
        return hlist;
    }

    public void setHlist(List<HlistBean> hlist) {
        this.hlist = hlist;
    }

    public List<TlistBean> getTlist() {
        return tlist;
    }

    public void setTlist(List<TlistBean> tlist) {
        this.tlist = tlist;
    }

    public static class HlistBean implements Parcelable {
        /**
         * hid : 0
         * htype : 2
         * hurl : http://m1.judayouyuan.com/index.php?g=App&m=Index&a=gift201704&mid=0
         * htitle : 春季领好礼
         * himg : http://m1.judayouyuan.com/static/img/201604/20170405slidetestshare.png
         * hstitle : 春季领好礼,每天都可以领哦
         * hsdesc : 春季领好礼
         * hspic : http://m1.judayouyuan.com/static/img/201604/20170405slidetestshare.png
         * hsdo : 1
         * tiptype : 0
         * gtype : 1
         * c1 : 0
         * c2 : 0
         * c3 : 0
         * c4 : 0
         * c5 : 0
         * c6 : 0
         * c7 : 0
         * c8 : 0
         * c9 : 0
         * c10 : 1
         */

        private String hid;
        private String htype;
        private String hurl;
        private String htitle;
        private String himg;
        private String hstitle;
        private String hsdesc;
        private String hspic;
        private String hsdo;
        private String tiptype;
        private String gtype;
        private String c1;
        private String c2;
        private String c3;
        private String c4;
        private String c5;
        private String c6;
        private String c7;
        private String c8;
        private String c9;
        private String c10;

        public String getHid() {
            return hid;
        }

        public void setHid(String hid) {
            this.hid = hid;
        }

        public String getHtype() {
            return htype;
        }

        public void setHtype(String htype) {
            this.htype = htype;
        }

        public String getHurl() {
            return hurl;
        }

        public void setHurl(String hurl) {
            this.hurl = hurl;
        }

        public String getHtitle() {
            return htitle;
        }

        public void setHtitle(String htitle) {
            this.htitle = htitle;
        }

        public String getHimg() {
            return himg;
        }

        public void setHimg(String himg) {
            this.himg = himg;
        }

        public String getHstitle() {
            return hstitle;
        }

        public void setHstitle(String hstitle) {
            this.hstitle = hstitle;
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

        public String getHsdo() {
            return hsdo;
        }

        public void setHsdo(String hsdo) {
            this.hsdo = hsdo;
        }

        public String getTiptype() {
            return tiptype;
        }

        public void setTiptype(String tiptype) {
            this.tiptype = tiptype;
        }

        public String getGtype() {
            return gtype;
        }

        public void setGtype(String gtype) {
            this.gtype = gtype;
        }

        public String getC1() {
            return c1;
        }

        public void setC1(String c1) {
            this.c1 = c1;
        }

        public String getC2() {
            return c2;
        }

        public void setC2(String c2) {
            this.c2 = c2;
        }

        public String getC3() {
            return c3;
        }

        public void setC3(String c3) {
            this.c3 = c3;
        }

        public String getC4() {
            return c4;
        }

        public void setC4(String c4) {
            this.c4 = c4;
        }

        public String getC5() {
            return c5;
        }

        public void setC5(String c5) {
            this.c5 = c5;
        }

        public String getC6() {
            return c6;
        }

        public void setC6(String c6) {
            this.c6 = c6;
        }

        public String getC7() {
            return c7;
        }

        public void setC7(String c7) {
            this.c7 = c7;
        }

        public String getC8() {
            return c8;
        }

        public void setC8(String c8) {
            this.c8 = c8;
        }

        public String getC9() {
            return c9;
        }

        public void setC9(String c9) {
            this.c9 = c9;
        }

        public String getC10() {
            return c10;
        }

        public void setC10(String c10) {
            this.c10 = c10;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.hid);
            dest.writeString(this.htype);
            dest.writeString(this.hurl);
            dest.writeString(this.htitle);
            dest.writeString(this.himg);
            dest.writeString(this.hstitle);
            dest.writeString(this.hsdesc);
            dest.writeString(this.hspic);
            dest.writeString(this.hsdo);
            dest.writeString(this.tiptype);
            dest.writeString(this.gtype);
            dest.writeString(this.c1);
            dest.writeString(this.c2);
            dest.writeString(this.c3);
            dest.writeString(this.c4);
            dest.writeString(this.c5);
            dest.writeString(this.c6);
            dest.writeString(this.c7);
            dest.writeString(this.c8);
            dest.writeString(this.c9);
            dest.writeString(this.c10);
        }

        public HlistBean() {
        }

        protected HlistBean(Parcel in) {
            this.hid = in.readString();
            this.htype = in.readString();
            this.hurl = in.readString();
            this.htitle = in.readString();
            this.himg = in.readString();
            this.hstitle = in.readString();
            this.hsdesc = in.readString();
            this.hspic = in.readString();
            this.hsdo = in.readString();
            this.tiptype = in.readString();
            this.gtype = in.readString();
            this.c1 = in.readString();
            this.c2 = in.readString();
            this.c3 = in.readString();
            this.c4 = in.readString();
            this.c5 = in.readString();
            this.c6 = in.readString();
            this.c7 = in.readString();
            this.c8 = in.readString();
            this.c9 = in.readString();
            this.c10 = in.readString();
        }

        public static final Parcelable.Creator<HlistBean> CREATOR = new Parcelable.Creator<HlistBean>() {
            @Override
            public HlistBean createFromParcel(Parcel source) {
                return new HlistBean(source);
            }

            @Override
            public HlistBean[] newArray(int size) {
                return new HlistBean[size];
            }
        };
    }

    public static class TlistBean {
        /**
         * hid : 0
         * hurl : http://fengfengkeji.kuaizhan.com/fp/page/display/5704b9424b0c49606e2ed016
         * htitle : 蜂蜂时光机
         * himg : http://m1.judayouyuan.com/static/img/201604/h5coin/hd_ffsgj.png
         * hstitle : 蜂赢金服
         * hsdesc : 财富丰盈 收益杠杠滴
         * hspic : http://m1.judayouyuan.com/static/img/images/share_yearago10.png
         */

        private String hid;
        private String hurl;
        private String htitle;
        private String himg;
        private String hstitle;
        private String hsdesc;
        private String hspic;

        public String getHid() {
            return hid;
        }

        public void setHid(String hid) {
            this.hid = hid;
        }

        public String getHurl() {
            return hurl;
        }

        public void setHurl(String hurl) {
            this.hurl = hurl;
        }

        public String getHtitle() {
            return htitle;
        }

        public void setHtitle(String htitle) {
            this.htitle = htitle;
        }

        public String getHimg() {
            return himg;
        }

        public void setHimg(String himg) {
            this.himg = himg;
        }

        public String getHstitle() {
            return hstitle;
        }

        public void setHstitle(String hstitle) {
            this.hstitle = hstitle;
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
