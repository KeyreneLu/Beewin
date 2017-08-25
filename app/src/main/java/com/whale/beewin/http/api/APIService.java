package com.whale.beewin.http.api;

import com.whale.beewin.bean.Banner;
import com.whale.beewin.bean.Bulk;
import com.whale.beewin.bean.Cash;
import com.whale.beewin.bean.CashInfo;
import com.whale.beewin.bean.Coin;
import com.whale.beewin.bean.Earning;
import com.whale.beewin.bean.Game;
import com.whale.beewin.bean.GoodInfo;
import com.whale.beewin.bean.Honor;
import com.whale.beewin.bean.Income;
import com.whale.beewin.bean.Invest;
import com.whale.beewin.bean.InvestRecord;
import com.whale.beewin.bean.MyCard;
import com.whale.beewin.bean.MyCash;
import com.whale.beewin.bean.Notice;
import com.whale.beewin.bean.NoticeDetail;
import com.whale.beewin.bean.Rate;
import com.whale.beewin.bean.Realm;
import com.whale.beewin.bean.Reckoning;
import com.whale.beewin.bean.Response;
import com.whale.beewin.bean.Return;
import com.whale.beewin.bean.Routine;
import com.whale.beewin.bean.ScatterRecord;
import com.whale.beewin.bean.Sold;
import com.whale.beewin.bean.User;
import com.whale.beewin.bean.UserInfo;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Sunflower on 2015/11/4.
 */
public interface APIService {
    /**
     * 用户登录界面
     * @param login 用户手机号
     * @param password 密码
     * @return User
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=login")
    Observable<Response<User>> Login(@Field("tel") String login, @Field("pwd") String password);

    /**
     * 获取注册手机验证码
     * @param login 用户手机号
     * @param code 密码
     * @return 结果
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=getyzcode")
    Observable<Response<Object>> getCode(@Field("tel") String login, @Field("vocode") String code);

    /**
     * 获取修改密码手机验证码
     * @param login 用户手机号
     * @param code 密码
     * @return 结果
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=getresetcode")
    Observable<Response<Object>> getModifyCode(@Field("tel") String login, @Field("vocode") String code);

    /**
     * 用户注册
     * @param login 用户手机号
     * @param password 用户密码
     * @param mcode 手机验证码
     * @param mid 邀请码
     * @return 注册结果
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=register")
    Observable<Response<Object>>Register(@Field("login") String login, @Field("password") String password,@Field("mcode") String mcode,@Field("mid") String mid);

    /**
     * 用户修改密码
     * @param login 用户手机号
     * @param password //用户新密码
     * @param mcode //手机验证码
     * @return 修改密码结果
     */
    @FormUrlEncoded
    @POST("index.php?g=Api&m=Client&a=resetpassword")
    Observable<Response<Object>> ChangePassword(@Field("login") String login, @Field("password") String password,@Field("mcode") String mcode);

    /**
     * 获取首页banner
     * @return
     */
    @POST("/index.php?g=Api&m=Client&a=bwindex")
    Observable<Response<Banner>> getBanner();

    /**
     * 获取系统公告
     * @param login 用户手机号
     * @param password 用户密码
     * @param page 页数
     * @return 系统公告
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=sysmsg")
    Observable<Response<Notice>> getNotice(@Field("tel") String login, @Field("pwd") String password, @Field("page") String page);

    /**
     * 公告详情
     * @param tel 用户手机号
     * @param pwd 用户密码
     * @param id 公告id
     * @return NoticeDetail
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=readmsg")
    Observable<Response<NoticeDetail>> getNoticeDetail(@Field("tel") String tel, @Field("pwd") String pwd, @Field("id") String id);

    /**
     * 获得投资列表
     * @return Invest
     */
    @POST("/index.php?g=Api&m=Client&a=listsp&page=1")
    Observable<Response<Invest>>getInvest();

    /**
     * 获取投资详情
     * @param id 产品id
     * @return GoodInfo
     */
    @GET("/index.php?g=Api&m=Client&a=p_info")
    Observable<Response<GoodInfo>> getInvestDetail(@Query("id")String id);

    /**
     * 已购列表
     * @param id 产品id
     * @param page 页数
     * @return Sold
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=p_orders")
    Observable<Response<Sold>> getSoldList(@Field("id") String id, @Field("page") String page);

    /**
     * 游戏列表
     * @return Game
     */
    @GET("/index.php?g=Api&m=Client&a=fshares2")
    Observable<Response<Game>> getGameList();

    /**
     * 获取用户信息
     * @param tel 用户名
     * @param pwd 用户密码
     * @return UserInfo
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=fuser")
    Observable<Response<UserInfo>> getUserInfo(@Field("tel") String tel, @Field("pwd") String pwd);

    /**
     * 获取花币使用记录
     * @param tel 用户名
     * @param pwd 用户密码
     * @param page 页数
     * @return Coin
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=score_log")
    Observable<Response<Coin>> getCoinRecord(@Field("tel") String tel, @Field("pwd") String pwd, @Field("page") String page);

    /**
     * 购买花币
     * @param tel 用户名
     * @param pwd 用户密码
     * @param cmon 购买金额
     * @param upwd md5密码
     * @return 购买结果
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=score_charge")
    Observable<Response<Object>> getCoinPay(@Field("tel") String tel, @Field("pwd") String pwd,@Field("cmon") String cmon,@Field("upwd") String upwd);

    /**
     * 获得代金券列表
     * @param page 页数
     * @return Cash
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=score_index_new")
    Observable<Response<Cash>> getCashList(@Field("tel") String tel, @Field("pwd") String pwd,@Field("page") String page);

    /**
     * 代金券详情
     * @param sid 代金券id
     * @return CashInfo
     */
    @GET("/index.php?g=Api&m=Client&a=score_info")
    Observable<Response<CashInfo>> getCashInfo(@Query("sid") String sid);

    /**
     * 商城兑换
     * @param tel 用户名
     * @param pwd 用户密码
     * @param mmon 购买金额
     * @param sid 代金券id
     * @return 支付结果
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=score_order")
    Observable<Response<Object>> cashPay(@Field("tel") String tel, @Field("pwd") String pwd,@Field("mmon") String mmon,@Field("sid") String sid);

    /**
     * 人脉收益
     * @param tel 用户名
     * @param pwd 用户密码
     * @param page 页数
     * @return Income
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=jiangli")
    Observable<Response<Income>> getIncomeList(@Field("tel") String tel, @Field("pwd") String pwd, @Field("page") String page);

    /**
     *投资记录
     * @return InvestRecord
     */
    @GET("index.php?g=Api&m=Client&a=fgoods")
    Observable<Response<InvestRecord>> getInvestRecord();
    /**
     *散投标记录
     * @param tel 用户名
     * @param pwd 用户密码
     * @param page 页数
     * @return ScatterRecord
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=clist")
    Observable<Response<ScatterRecord>> getScatterRecord(@Field("tel") String tel, @Field("pwd") String pwd, @Field("page") String page);

    /**
     * 我的卡卷
     * @param tel 用户名
     * @param pwd 用户密码
     * @return MyCash
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=smoney")
    Observable<Response<MyCash>> getMyCash(@Field("tel") String tel, @Field("pwd") String pwd);

    /**
     * 用户上传头像
     * @param params 上传的参数文件（用户名，密码，文件）
     * @return 上传结果
     */
    @Multipart
    @POST("index.php?g=Api&m=Client&a=fsetuimg")
    Observable<Response<Object>> updateUserInfo(@PartMap Map<String, RequestBody> params);

    /**
     * 获取用户认证信息
     * @param tel 用户名
     * @param pwd 用户密码
     * @return Realm
     */
    @FormUrlEncoded
    @POST("index.php?g=Api&m=Client&a=verfiyinfo")
    Observable<Response<Realm>> getRealm(@Field("tel") String tel, @Field("pwd") String pwd);

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
    Observable<Response<Object>> realName(@Field("tel") String tel, @Field("pwd") String pwd,@Field("name") String name, @Field("bCardNo") String bCardNo,@Field("bCardName") String bCardName,
                                          @Field("idCardNo") String idCardNo, @Field("bCardPlace") String bCardPlace);


    /**
     * 退出登录
     * @return 结果
     */
    @GET("index.php?g=Api&m=Client&a=flogout")
    Observable<Response<Object>> appExit();

    /**
     * 散投详情
     * @param tel 用户名
     * @param pwd 用户密码
     * @param id id
     * @return Bulk
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=ccontent")
    Observable<Response<Bulk>> getScatterDetail(@Field("tel") String tel, @Field("pwd") String pwd, @Field("id") String id);


    /**
     * 散投详情
     * @param tel 用户名
     * @param pwd 用户密码
     * @param id id
     * @return Return
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=cdetail")
    Observable<Response<Return>> getScatterItems(@Field("tel") String tel, @Field("pwd") String pwd, @Field("id") String id);

    /**
     * 累计收益
     * @param tel 用户名
     * @param pwd 密码
     * @return Earning
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=moneylog")
    Observable<Response<Earning>> getUserEarning(@Field("tel") String tel, @Field("pwd") String pwd);

    /**
     *账单信息
     * @param tel 用户名
     * @param pwd 密码
     * @param page 页数
     * @param t 类型
     * @param y 年份
     * @param n 月份
     * @param d
     * @return Reckoning
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=moneydetails")
    Observable<Response<Reckoning>> getUserReckoning(@Field("tel") String tel, @Field("pwd") String pwd, @Field("page") String page,@Field("t") String t,
    @Field("y") String y, @Field("n") String n, @Field("d") String d);

    /**
     * 七日年利率
     * @return Rate
     */
    @GET("/index.php?g=Api&m=Client&a=fsyslvli")
    Observable<Response<Rate>> getRate();

    /**
     * 提现步骤1
     * @param tel 用户名
     * @param pwd 密码
     * @return Routine
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=tixian")
    Observable<Response<Routine>> getRoutine(@Field("tel") String tel, @Field("pwd") String pwd);

    /**
     * 提现步骤2
     * @param tel 用户名
     * @param pwd 密码
     * @param price 提现金额
     * @param mpwd 支付密码
     * @param txid 回话id
     * @return 提现结果
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=addtxorder")
    Observable<Response<Object>> userDeposit(@Field("tel") String tel, @Field("pwd") String pwd, @Field("price") String price,@Field("mpwd") String mpwd, @Field("txid") String txid);

    /**
     * 获取个人信用度
     * @param tel 用户名
     * @param pwd 密码
     * @return Honor
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=getmoney")
    Observable<Response<Honor>> gethonor(@Field("tel") String tel, @Field("pwd") String pwd);

    /**
     * 获取自己的代金券
     * @param tel 用户名
     * @param pwd 密码
     * @return MyCard
     */
    @FormUrlEncoded
    @POST("/index.php?g=Api&m=Client&a=quanlist")
    Observable<Response<MyCard>> getMyCard(@Field("tel") String tel, @Field("pwd") String pwd);

}
