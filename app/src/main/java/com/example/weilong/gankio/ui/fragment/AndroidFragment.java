package com.example.weilong.gankio.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.example.weilong.gankio.R;
import com.example.weilong.gankio.baseui.fragment.GankFragment;
import com.example.weilong.gankio.ui.presenter.presenterImp.Fragment_Android_PresentImp;
import com.example.weilong.gankio.ui.view.MainRecyclerView;

/**
 * Created by weilong on 2016/12/26.
 */

public class AndroidFragment extends GankFragment {
    private Context mContext;
    private com.example.weilong.gankio.ui.view.MainRecyclerView mRecyFragmentAndroid;
    private Fragment_Android_PresentImp mAndroidPresentImp;
    private MaterialRefreshLayout mRefreshAndroid;

    public AndroidFragment(Context mContext){
        this.mContext=mContext;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_android, container,false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        mRecyFragmentAndroid = (MainRecyclerView) view.findViewById(R.id.recy_fragment_android);
        mRefreshAndroid = (MaterialRefreshLayout) view.findViewById(R.id.refresh_android);
        mRefreshAndroid.setLoadMore(true);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        mRecyFragmentAndroid.setItemAnimator(animator);
//        mRefreshAndroid.finishRefreshLoadMore();
        mAndroidPresentImp=new Fragment_Android_PresentImp(mContext);
        mAndroidPresentImp.showRecycler(mRecyFragmentAndroid,mRefreshAndroid);
        mRefreshAndroid.autoRefresh();
    }

}
