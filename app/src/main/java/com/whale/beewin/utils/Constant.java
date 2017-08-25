package com.whale.beewin.utils;

import android.os.Environment;

import java.io.File;

/**
 * 系统常用量
 * Created by Administrator on 2017/3/23 0023.
 */

public class Constant {
    //host网址
    public static final String API_HOST = "http://m.judayouyuan.com";
    //判断请求是否成功
    public static final String OK = "yes";
    // 默认存放文件下载的路径
    public final static String DEFAULT_SAVE_FILE_PATH = Environment.getExternalStorageDirectory() + File.separator + "Beewin" + File.separator + "download" + File.separator;
    //购买花币
    public static final int BUG_FLAG = 1;
    public static final int USE_MONEY = 2;
}
