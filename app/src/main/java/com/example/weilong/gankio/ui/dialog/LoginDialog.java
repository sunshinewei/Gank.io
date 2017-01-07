package com.example.weilong.gankio.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weilong.gankio.R;
import com.example.weilong.gankio.ui.activity.MainActivity;
import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by weilong on 2017/1/7.
 */

public class LoginDialog extends Dialog implements View.OnClickListener, Handler.Callback, PlatformActionListener {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ImageView mIvQQ;
    private ImageView mIvWeChat;
    private ImageView mIvSinaWeibo;
    private Button mBtnRemoveMsg;
    private Button mBtnShare;

    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;

    public LoginDialog(Context context) {
        super(context);
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        View inflate = mLayoutInflater.inflate(R.layout.dialog_login, null, false);
        setContentView(inflate);
        mIvQQ = (ImageView) inflate.findViewById(R.id.ivQQ);
        mIvWeChat = (ImageView) inflate.findViewById(R.id.ivWeChat);
        mIvSinaWeibo = (ImageView) inflate.findViewById(R.id.ivSinaWeibo);
        mBtnRemoveMsg = (Button) inflate.findViewById(R.id.btnRemoveMsg);
        mBtnShare = (Button) inflate.findViewById(R.id.btnShare);

        mIvQQ.setOnClickListener(this);
        mIvSinaWeibo.setOnClickListener(this);
        mIvWeChat.setOnClickListener(this);
        mBtnShare.setOnClickListener(this);
        mBtnRemoveMsg.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivQQ:
                //执行授权,获取用户信息
                System.out.println("点击了QQ登录");
                authorize(new QQ(mContext));
                break;
            case R.id.ivWeChat:
                authorize(new Wechat(mContext));
                break;
            case R.id.ivSinaWeibo:
                authorize(new SinaWeibo(mContext));
                break;
            case R.id.btnRemoveMsg:
                Platform qq = ShareSDK.getPlatform(mContext, QQ.NAME);
                Platform wechat = ShareSDK.getPlatform(mContext, Wechat.NAME);
                Platform weibo = ShareSDK.getPlatform(mContext, SinaWeibo.NAME);
                if (qq.isValid()) {
                    qq.removeAccount();
                }
                if (wechat.isValid()) {
                    wechat.removeAccount();
                }
                if (weibo.isValid()) {
                    weibo.removeAccount();
                }
                break;
        }
    }

    //执行授权,获取用户信息
    private void authorize(Platform plat) {
        if (plat.isValid()) {
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                login(plat.getName(), userId, null);
                return;
            }
        }
        plat.setPlatformActionListener(this);
        //true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(false);
        plat.showUser(null);
    }

    //发送登陆信息
    private void login(String plat, String userId, HashMap<String, Object> userInfo) {
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //释放资源
        ShareSDK.stopSDK(mContext);

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_USERID_FOUND: {
                Toast.makeText(mContext, R.string.userid_found, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_LOGIN: {
                String text = mContext.getResources().getString(R.string.logining, msg.obj);
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_CANCEL: {
                Toast.makeText(mContext, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_ERROR: {
                Toast.makeText(mContext, R.string.auth_error, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_AUTH_COMPLETE: {
                Toast.makeText(mContext, R.string.auth_complete, Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            //登录成功,获取需要的信息
            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);
            login(platform.getName(), platform.getDb().getUserId(), res);
            Log.e("asd", "platform.getName():" + platform.getName());
            Log.e("asd", "platform.getDb().getUserId()" + platform.getDb().getUserId());
            String openid = platform.getDb().getUserId() + "";
            String gender = platform.getDb().getUserGender();
            String head_url = platform.getDb().getUserIcon();
            String nickname = platform.getDb().getUserName();

            Log.e("asd", "openid:" + openid);
            Log.e("asd", "gender:" + gender);
            Log.e("asd", "head_url:" + head_url);
            Log.e("asd", "nickname:" + nickname);
        }
    }

    @Override
    public void onError(Platform platform, int action, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, this);
        }
        t.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
        }
    }
}
