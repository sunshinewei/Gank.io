package com.example.weilong.gankio.baseui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gank.Base.BaseFragment;

/**
 * Created by weilong on 2016/12/20.
 */

public abstract class GankFragment extends BaseFragment {

    @Nullable
    @Override
    public abstract View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) ;
}
