package com.example.weilong.gankio.ui.presenter;

import com.cjj.MaterialRefreshLayout;
import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.ui.view.MainRecyclerView;

/**
 * Created by weilong on 2016/12/27.
 */

public interface Fragment_Android_Presenter {
    void showRecycler(MainRecyclerView mainRecyclerView,MaterialRefreshLayout mRefreshAndroid);

    void startActivity(DataList.ResultsBean bean);
}
