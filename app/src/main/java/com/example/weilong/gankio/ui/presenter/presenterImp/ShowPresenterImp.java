package com.example.weilong.gankio.ui.presenter.presenterImp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.ui.presenter.ShowPresenter;


/**
 * Created by weilong on 2016/12/29.
 */

public class ShowPresenterImp implements ShowPresenter {

    private Context mContext;

    public ShowPresenterImp(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void showWebView(final WebView webView, final DataList.ResultsBean mRes, final ProgressBar mProShowProgress) {
        if (webView!=null){
//            webView.setWebChromeClient(new WebChromeClient());
            webView.loadUrl(mRes.getUrl());

            webView.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    if (newProgress>=100){
                        mProShowProgress.setVisibility(View.GONE);
                    }
                    mProShowProgress.setProgress(newProgress);

                }
            });
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (mRes!=null){
                        webView.loadUrl(mRes.getUrl());
                    }else {
                        Toast.makeText(mContext, "无法访问该网页", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }

            });
        }

    }
}
