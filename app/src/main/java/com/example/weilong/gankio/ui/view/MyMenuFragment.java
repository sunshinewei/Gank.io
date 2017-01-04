package com.example.weilong.gankio.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weilong.gankio.APP_Content;
import com.example.weilong.gankio.R;
import com.example.weilong.gankio.ui.fragment.AndroidFragment;
import com.example.weilong.gankio.ui.fragment.ExtendFragment;
import com.example.weilong.gankio.ui.fragment.FirstFragment;
import com.example.weilong.gankio.ui.fragment.GirlFragment;
import com.example.weilong.gankio.ui.fragment.IOSFragment;
import com.example.weilong.gankio.ui.fragment.RedioFragment;
import com.example.weilong.gankio.ui.fragment.UIFragment;
import com.example.weilong.gankio.utils.CircleTransformation;
import com.mxn.soul.flowingdrawer_core.MenuFragment;
import com.squareup.picasso.Picasso;


public class MyMenuFragment extends MenuFragment {
    private Context mContext;
    private NavigationView mNavigation;
    private TextView mTextView;

    private FragmentManager mFragmentManager;
    private FirstFragment mFirstFragment;
    private AndroidFragment mAndroidFragment;
    private IOSFragment mIOSFragment;
    private UIFragment mUIFragment;
    private RedioFragment mRedioFragment;
    private GirlFragment mGirlFragment;
    private ExtendFragment mExtendFragment;

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
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view= inflater.inflate(R.layout.fragment_menu, container,
                false);

        mNavigation= (NavigationView) view.findViewById(R.id.vNavigation);
        TextView headerView = (TextView) mNavigation.findViewById(R.id.tv_fragment_head);
        View headView = mNavigation.getHeaderView(0);
        ImageView imageHead= (ImageView) headView.findViewById(R.id.ivMenuUserProfilePhoto);
        if (imageHead!=null) {
            Picasso.with(mContext).load(R.drawable.photo)
                    .transform(new CircleTransformation())
                    .into(imageHead);
        }
//        = (TextView) mNavigation.getHeaderView(R.id.tv_fragment_head);
        onClickMenu();
//        if (view!=null){
//            System.out.println("实例化");
//        }
//        ivMenuUserProfilePhoto= (ImageView) view.findViewById(R.id.ivMenuUserProfilePhoto);
//        if(ivMenuUserProfilePhoto==null){
//            System.out.println("没有实例化");
//        }else {
//            System.out.println("实例化成功");
//        }
//        setupHeader();
        return  setupReveal(view) ;
    }

    private void setupHeader() {
        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
        String profilePhoto = getResources().getString(R.string.user_profile_photo);
//        Picasso.with(getActivity())
//                .load(profilePhoto)
//                .placeholder(R.drawable.img_circle_placeholder)
//                .resize(avatarSize, avatarSize)
//                .centerCrop()
//                .transform(new CircleTransformation())
//                .into(ivMenuUserProfilePhoto);
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

//    public void onOpenMenu(){
//        Toast.makeText(getActivity(),"onOpenMenu",Toast.LENGTH_SHORT).show();
//    }
    public void onCloseMenu(){
        Toast.makeText(getActivity(),"onCloseMenu",Toast.LENGTH_SHORT).show();
    }

}
