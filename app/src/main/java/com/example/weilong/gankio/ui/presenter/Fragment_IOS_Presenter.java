package com.example.weilong.gankio.ui.presenter;

import com.cjj.MaterialRefreshLayout;
import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.ui.view.MainRecyclerView;

/**
 * Created by weilong on 2016/12/27.
 */

public interface Fragment_IOS_Presenter {
    void showRecycler(MainRecyclerView mRecyFragmentIos,MaterialRefreshLayout mRefreshIos);
    void startActivity(DataList.ResultsBean mRes);
}
