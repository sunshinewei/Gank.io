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
import com.example.weilong.gankio.ui.presenter.presenterImp.Fragment_UI_PresenterImp;
import com.example.weilong.gankio.ui.view.MainRecyclerView;

/**
 * Created by weilong on 2016/12/26.
 */

public class UIFragment extends GankFragment {
    private Context mContext;
    private MainRecyclerView mRecyFragmentUI;
    private MaterialRefreshLayout mRefreshUi;

    private Fragment_UI_PresenterImp mUIPresenterImp;
    public UIFragment(Context mContext){
        this.mContext=mContext;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_ui, container, false);
        initView(inflate);
        return inflate;
    }
    private void initView(View view){
        mRecyFragmentUI = (MainRecyclerView) view.findViewById(R.id.recy_fragment_ui);
        mRefreshUi = (MaterialRefreshLayout) view.findViewById(R.id.refresh_ui);
        mRefreshUi.setLoadMore(true);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        mRecyFragmentUI.setItemAnimator(animator);
        mUIPresenterImp=new Fragment_UI_PresenterImp(mContext);
        mUIPresenterImp.showRecycler(mRecyFragmentUI,mRefreshUi);

        mRefreshUi.autoRefresh();
    }
}
