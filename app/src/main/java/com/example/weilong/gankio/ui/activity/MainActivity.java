package com.example.weilong.gankio.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weilong.gankio.APP_Content;
import com.example.weilong.gankio.R;
import com.example.weilong.gankio.baseui.activity.GankActivity;
import com.example.weilong.gankio.ui.fragment.FirstFragment;
import com.example.weilong.gankio.ui.presenter.presenterImp.MainPresenterImp;
import com.example.weilong.gankio.ui.view.FragmentView;
import com.example.weilong.gankio.ui.view.MainRecyclerView;
import com.example.weilong.gankio.ui.view.MyMenuFragment;
import com.example.weilong.gankio.ui.viewImp.MainView;
import com.mxn.soul.flowingdrawer_core.FlowingView;
import com.mxn.soul.flowingdrawer_core.LeftDrawerLayout;

public class MainActivity extends GankActivity implements MainView {

    private MainRecyclerView mRecyMainContent;
    private MainPresenterImp mMainPresenterImp;
    private LeftDrawerLayout mLeftDrawerLayout;
    private FragmentView mFramMain;
    private Toolbar mToolbarMain;
    private TextView mTvMainTool;

    private FragmentManager fm;
    private FirstFragment mFirstFragment;
    private long isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();

        mFramMain = (FragmentView) findViewById(R.id.fram_main);
        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);
        fm = getSupportFragmentManager();
        MyMenuFragment mMenuFragment = (MyMenuFragment) fm.findFragmentById(R.id.id_container_menu);
        FlowingView mFlowingView = (FlowingView) findViewById(R.id.sv);
        if (mMenuFragment == null) {
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment = new MyMenuFragment(this, fm,mTvMainTool)).commit();
        }
        mLeftDrawerLayout.setFluidView(mFlowingView);
        mLeftDrawerLayout.setMenuFragment(mMenuFragment);

        initFragment();

        mMainPresenterImp = new MainPresenterImp(this, this);
//        initRecyView();
    }

    private void initToolBar() {
        mTvMainTool = (TextView) findViewById(R.id.tv_main_tool);
        mToolbarMain = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbarMain);

    }

    @Override
    public void initRecyView() {
        if (mRecyMainContent != null) {
            mMainPresenterImp.recyclerviewAdapter(mRecyMainContent);
        } else {
            Toast.makeText(this, "当前没有数据", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickItem() {

    }

    private void initFragment() {
        mFirstFragment = new FirstFragment(this);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fram_main, mFirstFragment, APP_Content.Fragment_First);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        long current=System.currentTimeMillis();
        if (current-isBack>2000){
            Toast.makeText(this, "继续点击退出", Toast.LENGTH_SHORT).show();
            isBack=current;
            return;
        }
        super.onBackPressed();
    }
}

