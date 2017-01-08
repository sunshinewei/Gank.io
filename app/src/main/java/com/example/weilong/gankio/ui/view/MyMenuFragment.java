package com.example.weilong.gankio.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weilong.gankio.APP_Content;
import com.example.weilong.gankio.R;
import com.example.weilong.gankio.dao.User;
import com.example.weilong.gankio.ui.fragment.AndroidFragment;
import com.example.weilong.gankio.ui.fragment.ExtendFragment;
import com.example.weilong.gankio.ui.fragment.FirstFragment;
import com.example.weilong.gankio.ui.fragment.GirlFragment;
import com.example.weilong.gankio.ui.fragment.IOSFragment;
import com.example.weilong.gankio.ui.fragment.RedioFragment;
import com.example.weilong.gankio.ui.fragment.UIFragment;
import com.example.weilong.gankio.utils.CircleTransformation;
import com.mob.tools.utils.UIHandler;
import com.mxn.soul.flowingdrawer_core.MenuFragment;
import com.squareup.picasso.Picasso;



import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

import static okhttp3.Protocol.get;


public class MyMenuFragment extends MenuFragment implements View.OnClickListener{
    private Context mContext;
    private NavigationView mNavigation;
    private TextView mTextView;
    private TextView headerView;
    private ImageView imageHead;

    private FragmentManager mFragmentManager;
    private FirstFragment mFirstFragment;
    private AndroidFragment mAndroidFragment;
    private IOSFragment mIOSFragment;
    private UIFragment mUIFragment;
    private RedioFragment mRedioFragment;
    private GirlFragment mGirlFragment;
    private ExtendFragment mExtendFragment;
    private LoginDialog mLoginDialog;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           Bundle bundle= msg.getData();
            User user= (User) bundle.get("user");
            if (headerView!=null && imageHead!=null){

                System.out.println("handler "+user.getUrl());
                headerView.setText(user.getName());
                Picasso.with(mContext).load(user.getUrl())
                        .error(R.drawable.photo)
                        .placeholder(R.drawable.photo)
                        .transform(new CircleTransformation())
                        .into(imageHead);
            }
        }
    };



    public MyMenuFragment(Context mContext, FragmentManager fragmentManager,TextView mText) {
        this.mContext = mContext;
        this.mFragmentManager=fragmentManager;
        this.mTextView=mText;

        mFirstFragment=new FirstFragment(mContext);
        mAndroidFragment=new AndroidFragment(mContext);
        mIOSFragment=new IOSFragment(mContext);
        mUIFragment=new UIFragment(mContext);
        mRedioFragment=new RedioFragment(mContext);
        mGirlFragment=new GirlFragment(mContext);
        mExtendFragment=new ExtendFragment(mContext);
        mLoginDialog=new LoginDialog(mContext);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view= inflater.inflate(R.layout.fragment_menu, container,
                false);

        mNavigation= (NavigationView) view.findViewById(R.id.vNavigation);
        View headView = mNavigation.getHeaderView(0);
        headerView= (TextView) headView.findViewById(R.id.tv_fragment_head);
        imageHead= (ImageView) headView.findViewById(R.id.ivMenuUserProfilePhoto);
        if (imageHead!=null) {
            Picasso.with(mContext).load(R.drawable.photo)
                    .transform(new CircleTransformation())
                    .into(imageHead);
        }

        if (headerView!=null && imageHead!=null){
            headView.setOnClickListener(this);
            imageHead.setOnClickListener(this);
        }
        onClickMenu();
        return  setupReveal(view) ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    private void setupHeader() {
        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
        String profilePhoto = getResources().getString(R.string.user_profile_photo);
    }
    public void onClickMenu(){

        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (mFragmentManager!=null){
                    switch (item.getItemId()){
                        case R.id.menu_main_all:
                            fragmentItemShow(item);
                            break;
                        case R.id.menu_main_android:
                            fragmentItemShow(item);
                            break;
                        case R.id.menu_main_ios:
                            fragmentItemShow(item);
                            break;
                        case R.id.menu_main_love:
                            fragmentItemShow(item);
                            break;
                        case R.id.menu_main_more:
                            fragmentItemShow(item);
                            break;
                        case R.id.menu_main_ui:
                            fragmentItemShow(item);
                            break;
                        case R.id.menu_main_vedio:
                            fragmentItemShow(item);
                            break;
                    }
                }
                return true;
            }
        });
    }

    private void fragmentItemShow(MenuItem item){
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        switch (item.getItemId()){
            case R.id.menu_main_all:
                mTextView.setText("主页");
                transaction.replace(R.id.fram_main,mFirstFragment, APP_Content.Fragment_First);
                break;
            case R.id.menu_main_android:
                mTextView.setText("Android");
                transaction.replace(R.id.fram_main,mAndroidFragment,APP_Content.Fragment_android);
                break;
            case R.id.menu_main_ios:
                mTextView.setText("IOS");
                transaction.replace(R.id.fram_main,mIOSFragment,APP_Content.Fragment_ios);
                break;
            case R.id.menu_main_love:
                mTextView.setText("图片");
                transaction.replace(R.id.fram_main,mGirlFragment,APP_Content.Fragment_girl);
                break;
            case R.id.menu_main_more:
                mTextView.setText("拓展资源");
                transaction.replace(R.id.fram_main,mExtendFragment,APP_Content.Fragment_Extend);
                break;
            case R.id.menu_main_ui:
                mTextView.setText("前端");
                transaction.replace(R.id.fram_main,mUIFragment,APP_Content.Fragment_UI);
                break;
            case R.id.menu_main_vedio:
                mTextView.setText("视频分享");
                transaction.replace(R.id.fram_main,mRedioFragment,APP_Content.Fragment_redio);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
       if (view.getId()==R.id.tv_fragment_head || view.getId()==R.id.ivMenuUserProfilePhoto) {
           mLoginDialog.setTitle("登录");
           mLoginDialog.show();
       }
    }

    public class LoginDialog extends Dialog implements View.OnClickListener, Handler.Callback, PlatformActionListener {
        private LayoutInflater mLayoutInflater;
        private Context mContext;
        private ImageView mIvQQ;
        private ImageView mIvWeChat;
        private ImageView mIvSinaWeibo;
        private Button mBtnRemoveMsg;

        private static final int MSG_USERID_FOUND = 1;
        private static final int MSG_LOGIN = 2;
        private static final int MSG_AUTH_CANCEL = 3;
        private static final int MSG_AUTH_ERROR = 4;
        private static final int MSG_AUTH_COMPLETE = 5;

        public LoginDialog(Context context) {
            super(context);
            this.mContext = context;
            mLayoutInflater = LayoutInflater.from(mContext);
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            initView();
        }

        private void initView() {
            View inflate = mLayoutInflater.inflate(R.layout.dialog_login, null, false);
            setContentView(inflate);
            mIvQQ = (ImageView) inflate.findViewById(R.id.ivQQ);
            mIvWeChat = (ImageView) inflate.findViewById(R.id.ivWeChat);
            mIvSinaWeibo = (ImageView) inflate.findViewById(R.id.ivSinaWeibo);
            mBtnRemoveMsg = (Button) inflate.findViewById(R.id.btnRemoveMsg);

            mIvQQ.setOnClickListener(this);
            mIvSinaWeibo.setOnClickListener(this);
            mIvWeChat.setOnClickListener(this);
            mBtnRemoveMsg.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivQQ:
                    //执行授权,获取用户信息
                    System.out.println("点击了QQ登录");
                    authorize(new QQ(mContext));
                    this.dismiss();
                    break;
                case R.id.ivWeChat:
                    authorize(new Wechat(mContext));
                    dismiss();
                    break;
                case R.id.ivSinaWeibo:
                    authorize(new SinaWeibo(mContext));
                    dismiss();
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
                    dismiss();

                    if (imageHead!=null && headerView!=null) {
                        headerView.setText("个人信息");
                        Picasso.with(mContext).load(R.drawable.photo)
                                .transform(new CircleTransformation())
                                .into(imageHead);
                    }



                    break;
            }

        }

        @Override
        public void dismiss() {
            super.dismiss();
//        EventBus.getDefault().unregister(this);
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
//            EventBus.getDefault().post(new User(headerName,head_url));

                Message obtain = Message.obtain();
                Bundle bundle=new Bundle();
                bundle.putSerializable("user",new User(nickname,head_url));
                obtain.setData(bundle);
                mHandler.sendMessage(obtain);

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

}
