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
import com.example.weilong.gankio.ui.presenter.presenterImp.Fragment_Girl_PresentImp;
import com.example.weilong.gankio.ui.view.MainRecyclerView;

/**
 * Created by weilong on 2016/12/26.
 */

public class GirlFragment extends GankFragment {
    private Context mContext;
    private com.example.weilong.gankio.ui.view.MainRecyclerView mRecyFragmentGirl;
    private Fragment_Girl_PresentImp mGirlPresentImp;
    private MaterialRefreshLayout mRefreshGirl;

    public GirlFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_girl, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        mRecyFragmentGirl = (MainRecyclerView) view.findViewById(R.id.recy_fragment_girl);
        mRefreshGirl = (MaterialRefreshLayout) view.findViewById(R.id.refresh_girl);
        mRefreshGirl.setLoadMore(true);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        mRecyFragmentGirl.setItemAnimator(animator);
        mGirlPresentImp = new Fragment_Girl_PresentImp(mContext);
        mGirlPresentImp.showRecycler(mRecyFragmentGirl, mRefreshGirl);

        mRefreshGirl.autoRefresh();
    }
}
