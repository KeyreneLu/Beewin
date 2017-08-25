package com.whale.beewin.http;

import com.whale.beewin.BeewinApp;
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
import com.whale.beewin.bean.Return;
import com.whale.beewin.bean.Routine;
import com.whale.beewin.bean.ScatterRecord;
import com.whale.beewin.bean.Sold;
import com.whale.beewin.bean.User;
import com.whale.beewin.bean.UserInfo;
import com.whale.beewin.utils.HeaderObject;
import com.whale.beewin.utils.T;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 *网络请求类
 * Created by Administrator on 2017/3/24 0024.
 */

public class ApiWrapper extends RetrofitUtil {

    private String newPassWord;

    //登录
    public Observable<User> userLogin(String login,String passwrod){
        if (!login.isEmpty()&&!passwrod.isEmpty()){
             newPassWord = HeaderObject.getMD5("iosbeewin"+HeaderObject.getMD5(passwrod));
        }else {
            T.showShort(BeewinApp.getContext(),"用户名密码不能为空!");
        }
        return getService().Login(login,newPassWord)
                .compose(this.<User>applySchedulers());
    }

    //获取注册手机验证码
    public  Observable<Object> askCode(String phone,String code){
        return getService().getCode(phone,code)
                .compose(this.<Object>applySchedulers());
    }
   // 获取修改密码手机验证码
    public  Observable<Object> askModifyCode(String phone,String code){
        return getService().getModifyCode(phone,code)
                .compose(this.<Object>applySchedulers());
    }

    //用户注册
    public  Observable<Object> userRegister(String phone,String password,String mcode,String mid){
        return getService().Register(phone,password,mcode,mid)
                .compose(this.<Object>applySchedulers());
    }

    //用户修改密码
    public Observable<Object> userChangePassword(String phone,String password,String mcode){
        return getService().ChangePassword(phone, password, mcode)
                .compose(this.<Object>applySchedulers());
    }

    //获取首页的banner
    public Observable<Banner> getHomeBanner(){
        return getService().getBanner()
                .compose(this.<Banner>applySchedulers());
    }

    //获取系统公告
    public Observable<Notice> getSysNotice(String login,String password,String page){
        return getService().getNotice(login, password, page)
                .compose(this.<Notice>applySchedulers());
    }

    //获取公告详情
    public Observable<NoticeDetail> getSysDetail(String tel,String pwd,String id){
        return getService().getNoticeDetail(tel, pwd, id)
                .compose(this.<NoticeDetail>applySchedulers());
    }

    //获取投资列表
    public Observable<Invest> getSysInvest(){
        return getService().getInvest()
                .compose(this.<Invest>applySchedulers());
    }

    //产品详情
    public Observable<GoodInfo> getDetail(String id){
        return getService().getInvestDetail(id)
                .compose(this.<GoodInfo>applySchedulers());
    }

    //已购列表
    public Observable<Sold> getSold(String id,String page){
        return getService().getSoldList(id, page)
                .compose(this.<Sold>applySchedulers());
    }

    //游戏列表
    public Observable<Game> getGame(){
        return getService().getGameList()
                .compose(this.<Game>applySchedulers());
    }

    //获取用户信息
    public Observable<UserInfo> getInfo(String tel,String pwd){
        return getService().getUserInfo(tel, pwd)
                .compose(this.<UserInfo>applySchedulers());
    }

    //花币记录
    public Observable<Coin> getCoin(String tel,String pwd,String page){
        return getService().getCoinRecord(tel, pwd, page)
                .compose(this.<Coin>applySchedulers());
    }

    //购买花币
    public Observable<Object> coinPay(String tel,String pwd,String cmon,String upwd){
        return getService().getCoinPay(tel, pwd, cmon, upwd)
                .compose(this.<Object>applySchedulers());
    }

    //花币商城代金券
    public Observable<Cash> getCash(String tel,String pwd,String page){
        return getService().getCashList(tel, pwd, page)
                .compose(this.<Cash>applySchedulers());
    }

    //花币商城代金券
    public Observable<CashInfo> getCashInfo(String sid){
        return getService().getCashInfo(sid)
                .compose(this.<CashInfo>applySchedulers());
    }

    //商城兑换
    public Observable<Object> getCashPay(String tel,String pwd,String mmon,String sid){
        return getService().cashPay(tel, pwd, mmon, sid)
                .compose(this.<Object>applySchedulers());
    }

    //人脉收益
    public Observable<Income> getIncome(String tel, String pwd, String page){
        return getService().getIncomeList(tel, pwd, page)
                .compose(this.<Income>applySchedulers());
    }

    //投资记录
    public Observable<InvestRecord> getInvestRecord(){
        return getService().getInvestRecord()
                .compose(this.<InvestRecord>applySchedulers());
    }

    //散投记录
    public Observable<ScatterRecord> getScatterRecord(String tel, String pwd, String page){
        return getService().getScatterRecord(tel, pwd, page)
                .compose(this.<ScatterRecord>applySchedulers());
    }

    //我的卡卷
    public Observable<MyCash> getMyCash(String tel, String pwd){
        return getService().getMyCash(tel, pwd)
                .compose(this.<MyCash>applySchedulers());
    }

    //用户上传头像
    public Observable<Object> updateHeadPic(String login,String pwd,String imagePath){
        File file = new File(imagePath);
        Map<String ,RequestBody> params=new HashMap<>();
        params.put("tel",createRequestBody(login));
        params.put("pwd",createRequestBody(pwd));
//        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        params.put("imagefile\"; filename=\""+file.getName()+"", requestFile);
//        params.put("imagefile",requestFile);
//        params.put("imagefile\"; filename=\"bee_head.jpg\"",createPictureRequestBody(imagePath));
        //key值中为images
//        params.put("avatar\"; filename=\"" + file.getName() + "" , images);
        return getService().updateUserInfo(params)
                .compose(this.<Object>applySchedulers());
    }

    //获取用户认证信息
    public Observable<Realm> getRealm(String login,String pwd){
        return getService().getRealm(login, pwd)
                .compose(this.<Realm>applySchedulers());
    }

    //用户认证
    public Observable<Object> realName(String login,String pwd,String name,String IDCard,String bankname,String bankcard,String bankAddress){
        return  getService().realName(login, pwd, name, IDCard, bankname, bankcard, bankAddress)
                .compose(this.<Object>applySchedulers());
    }

    //获取用户认证信息
    public Observable<Object> appexit(){
        return getService().appExit()
                .compose(this.<Object>applySchedulers());
    }
    //获得散投详情
    public Observable<Bulk> getScatterDetail(String login,String pwd,String id){
        return getService().getScatterDetail(login, pwd, id)
                .compose(this.<Bulk>applySchedulers());
    }

    public Observable<Return> getScatterItems(String login, String pwd, String id){
        return getService().getScatterItems(login, pwd, id)
                .compose(this.<Return>applySchedulers());
    }

    //累积收益
    public Observable<Earning> getUserEarning(String login,String pwd){
        return getService().getUserEarning(login,pwd)
                .compose(this.<Earning>applySchedulers());
    }

    //账单
    public Observable<Reckoning> getUserReckoning(String login,String pwd,String page,String t,String y,String n,String d){
        return getService().getUserReckoning(login, pwd, page, t, y, n, d)
                .compose(this.<Reckoning>applySchedulers());
    }
    //七日年化利率
    public Observable<Rate> getRate(){
        return getService().getRate()
                .compose(this.<Rate>applySchedulers());
    }

    //提现步骤1
    public Observable<Routine> getRoutine(String tel,String pwd){
        return getService().getRoutine(tel, pwd)
                .compose(this.<Routine>applySchedulers());
    }

    //提现步骤
    public Observable<Object> userDeposit(String tel,String pwd,String price,String mpwd,String txid){
        return getService().userDeposit(tel, pwd, price, mpwd, txid)
                .compose(this.<Object>applySchedulers());
    }

    //获取个人信用度
    public Observable<Honor> getHonor(String tel, String pwd){
        return getService().gethonor(tel, pwd)
                .compose(this.<Honor>applySchedulers());
    }

    //获取自己的代金券
    public Observable<MyCard> getMyCard(String tel, String pwd){
        return getService().getMyCard(tel, pwd)
                .compose(this.<MyCard>applySchedulers());
    }
}
