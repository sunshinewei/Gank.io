package com.example.weilong.gankio.ui.presenter;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.weilong.gankio.dao.DataList;
import com.liji.excellentwaveview.WaveView;

/**
 * Created by weilong on 2016/12/29.
 */

public interface ShowPresenter {

    void showWebView(WebView webView,DataList.ResultsBean mRes,ProgressBar mProShowProgress);
}
