package com.example.weilong.gankio.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weilong.gankio.R;
import com.example.weilong.gankio.baseui.fragment.GankFragment;
import com.example.weilong.gankio.ui.presenter.presenterImp.Fragment_Redio_presenterImp;
import com.example.weilong.gankio.ui.view.MainRecyclerView;

/**
 * Created by weilong on 2016/12/26.
 */

public class RedioFragment extends GankFragment {
    private Context mContext;
    private com.example.weilong.gankio.ui.view.MainRecyclerView mRecyFragmentRedio;
private Fragment_Redio_presenterImp mRedioPresenterImp;
    public RedioFragment(Context mContext){
        this.mContext=mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_redio, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        mRecyFragmentRedio = (MainRecyclerView) view.findViewById(R.id.recy_fragment_redio);
        mRedioPresenterImp=new Fragment_Redio_presenterImp(mContext);
        DefaultItemAnimator animator=new DefaultItemAnimator();
        mRecyFragmentRedio.setItemAnimator(animator);
        mRedioPresenterImp.showRecycler(mRecyFragmentRedio);
    }
}
