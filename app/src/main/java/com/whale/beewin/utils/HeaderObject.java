package com.whale.beewin.utils;

import com.whale.beewin.BeewinApp;
import com.whale.beewin.bean.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class HeaderObject {

    public static void setUser(User user){
        SPUtils.put(BeewinApp.getContext(),"syslv", user.getSyslv());
        SPUtils.put(BeewinApp.getContext(),"twfu", (float) Double.parseDouble(user.getTwfu()));
        SPUtils.put(BeewinApp.getContext(),"stel", user.getStel());
        SPUtils.put(BeewinApp.getContext(),"rmjl", user.getRmjl());
        SPUtils.put(BeewinApp.getContext(),"umsg", user.getUmsg());
        SPUtils.put(BeewinApp.getContext(),"islogin", Integer.parseInt(user.getIslogin()));
        SPUtils.put(BeewinApp.getContext(),"uhead", user.getUhead());
        SPUtils.put(BeewinApp.getContext(),"ticket", user.getTicket());
        SPUtils.put(BeewinApp.getContext(),"sysmember", Integer.parseInt(user.getSysmember()));
        SPUtils.put(BeewinApp.getContext(),"smoney", (float) Double.parseDouble(user.getSmoney()));
        SPUtils.put(BeewinApp.getContext(),"ugmoney", (float) Double.parseDouble(user.getUgmoney()));
        SPUtils.put(BeewinApp.getContext(),"imoney", (float) Double.parseDouble(user.getImoney()));
        SPUtils.put(BeewinApp.getContext(),"tmoney", (float) Double.parseDouble(user.getTmoney()));
        SPUtils.put(BeewinApp.getContext(),"ujmoney", (float) Double.parseDouble(user.getUjmoney()));
        SPUtils.put(BeewinApp.getContext(),"ucmoney", (float) Double.parseDouble(user.getUcmoney()));
        SPUtils.put(BeewinApp.getContext(),"bcard", Integer.parseInt(user.getBcard()));
        SPUtils.put(BeewinApp.getContext(),"login", user.getLogin());
        SPUtils.put(BeewinApp.getContext(),"uuid", user.getUuid());
        SPUtils.put(BeewinApp.getContext(),"jifen", Integer.parseInt(user.getJifen()));
        SPUtils.put(BeewinApp.getContext(),"sstel", user.getSstel());
        SPUtils.put(BeewinApp.getContext(),"pwd", user.getPwd());
    }

//    public static void setUserInfo(User user){
//        SPUtils.put(BeewinApp.getContext(),"showyzcode", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"aname", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"id_no", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"ulevel", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"n1", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"n2", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"n3", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"n4", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"b1", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"b2", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"b3", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"b4", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"j1", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"j2", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"j3", user.getSyslv());
//        SPUtils.put(BeewinApp.getContext(),"j4",user.getSyslv());
//    }
    /**
     * MD5 加密
     * @param info
     * @return
     */
    public static String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
