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
import com.example.weilong.gankio.ui.presenter.presenterImp.Fragment_IOS_PresenterImp;
import com.example.weilong.gankio.ui.view.MainRecyclerView;

/**
 * Created by weilong on 2016/12/26.
 */

public class IOSFragment extends GankFragment {
    private Context mContext;
    private com.example.weilong.gankio.ui.view.MainRecyclerView mRecyFragmentIos;
    private MaterialRefreshLayout mRefreshIos;
    private Fragment_IOS_PresenterImp mIOSPresenterImp;
    public IOSFragment(Context mContext){
        this.mContext=mContext;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_ios, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        mRecyFragmentIos = (MainRecyclerView) view.findViewById(R.id.recy_fragment_ios);
        mRefreshIos = (MaterialRefreshLayout) view.findViewById(R.id.refresh_ios);
        mRefreshIos.setLoadMore(true);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        mRecyFragmentIos.setItemAnimator(animator);
        mIOSPresenterImp=new Fragment_IOS_PresenterImp(mContext);
        mIOSPresenterImp.showRecycler(mRecyFragmentIos,mRefreshIos);

        mRefreshIos.autoRefresh();
    }
}
