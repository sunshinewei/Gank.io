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
import com.example.weilong.gankio.ui.presenter.presenterImp.FragmentFirstPresenterImp;
import com.example.weilong.gankio.ui.view.MainRecyclerView;

/**
 * Created by weilong on 2016/12/26.
 */

public class FirstFragment extends GankFragment{
    private Context mContext;
    private MainRecyclerView mRecyFragmentFirst;
    private FragmentFirstPresenterImp mFragmentFirstPresenterImp;
    private MaterialRefreshLayout mRefreshFirst;

    public FirstFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_first, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**初始化fragment中的控件
     * @param inflate
     */
    private void initView(View inflate ) {
        mRecyFragmentFirst = (MainRecyclerView) inflate.findViewById(R.id.recy_fragment_first);
        mRefreshFirst = (MaterialRefreshLayout) inflate.findViewById(R.id.refresh_first);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        mRecyFragmentFirst.setItemAnimator(animator);
        mRefreshFirst.setLoadMore(true);
        mFragmentFirstPresenterImp=new FragmentFirstPresenterImp(mContext);
        mFragmentFirstPresenterImp.showRecycler(mRecyFragmentFirst,mRefreshFirst);
//        mRefreshFirst.finishRefreshing();
    }

}
