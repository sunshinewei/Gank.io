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
import com.example.weilong.gankio.ui.presenter.presenterImp.Fragment_Extend_PresenterImp;
import com.example.weilong.gankio.ui.view.MainRecyclerView;

/**
 * Created by weilong on 2016/12/26.
 */

public class ExtendFragment extends GankFragment {
    private Context mContext;
    private com.example.weilong.gankio.ui.view.MainRecyclerView mRecyFragmentExtend;
    private Fragment_Extend_PresenterImp mExtendPresenterImp;
    private MaterialRefreshLayout mRefreshExtend;

    public ExtendFragment(Context mContext){
        this.mContext=mContext;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_extend, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        mRefreshExtend = (MaterialRefreshLayout) view.findViewById(R.id.refresh_extend);
        mRecyFragmentExtend = (MainRecyclerView) view.findViewById(R.id.recy_fragment_extend);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        mRecyFragmentExtend.setItemAnimator(animator);
        mRefreshExtend.setLoadMore(true);
        mExtendPresenterImp=new Fragment_Extend_PresenterImp(mContext);
        mExtendPresenterImp.showRecycler(mRecyFragmentExtend);
    }
}
