package com.whale.beewin.http.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 下载
 * Created by Administrator on 2017/3/24 0024.
 */

public interface DownloadService {
    /**
     * 图片验证码
     * @param fileUrl 图片网址
     * @return bitmap
     */
    @GET
    Call<ResponseBody> downloadPicFromNet(@Url String fileUrl);

    /**
     * 散投支付接口
     * @param tel 用户名
     * @param pwd 用户密码
     * @param mmon 支付金额
     * @param rpdo 自动购买
     * @param rennums 份数
     * @param rentimes 周期
     * @param qid 代金券
     * @param ptype 支付方式
     * @param upwd 支付密码
     * @return 支付结果
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=dealorder")
    Call<ResponseBody> userPay(@Field("tel") String tel, @Field("pwd") String pwd, @Field("mmon") String mmon, @Field("rpdo") String rpdo, @Field("rennums") String rennums,
                                         @Field("rentimes") String rentimes, @Field("qid") String qid, @Field("ptype") String ptype, @Field("upwd") String upwd);

    /**
     *其他产品的支付接口
     * @param tel 用户名
     * @param pwd 用户密码
     * @param mmon 支付金额
     * @param ptype 支付方式
     * @param upwd 支付密码
     * @return 支付结果
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=order")
    Call<ResponseBody> investPay(@Field("tel") String tel, @Field("pwd") String pwd, @Field("mmon") String mmon,@Field("gid") String gid, @Field("ptype") String ptype, @Field("upwd") String upwd);


    /**
     * 实名认证
     * @param tel 用户名
     * @param pwd 密码
     * @param name 真实名字
     * @param bCardNo 身份证号
     * @param bCardName 所属银行名
     * @param idCardNo 银行卡号
     * @param bCardPlace 开户行
     * @return 结果
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=verfiy")
    Call<ResponseBody> realName(@Field("tel") String tel, @Field("pwd") String pwd, @Field("name") String name, @Field("idCardNo") String idCardNo, @Field("bCardName") String bCardName,
                                          @Field("bCardNo") String bCardNo, @Field("bCardPlace") String bCardPlace);

    /**
     * 用户充值
     * @param tel 用户名
     * @param pwd 密码
     * @param mmon 充值金额
     * @return 充值金额
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=charge")
    Call<ResponseBody> userRecharge(@Field("tel") String tel, @Field("pwd") String pwd, @Field("mmon") String mmon);
}
