package com.example.weilong.gankio.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.weilong.gankio.R;
import com.example.weilong.gankio.Source_Type;
import com.example.weilong.gankio.baseui.activity.GankActivity;
import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.ui.presenter.presenterImp.ShowPresenterImp;
import com.liji.excellentwaveview.WaveView;

public class ShowActivity extends GankActivity {
    private WebView mWebShowMessage;
    private WaveView progressShow;
    private ImageView imageView;
    private ProgressBar mProShowProgress;
    private ShowPresenterImp mShowPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        iniToolBar();
        initView();
        initData();
    }

    private void iniToolBar() {
        Toolbar tool= (Toolbar) findViewById(R.id.toolbar_show);
        setSupportActionBar(tool);
    }

    private void initView(){
        mWebShowMessage = (WebView) findViewById(R.id.web_show_message);
        mProShowProgress = (ProgressBar) findViewById(R.id.pro_show_progress);
        WebSettings settings = mWebShowMessage.getSettings();
        //不加载缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //允许和js交互
        settings.setJavaScriptEnabled(true);
        //支持缩放
        settings.setSupportZoom(true);
        mWebShowMessage.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }
    private void initData(){
        System.out.println("进入到showActivity");
        Intent intent = this.getIntent();
        DataList.ResultsBean mRes = (DataList.ResultsBean) intent.getSerializableExtra(Source_Type.GO_SHOW);
        mShowPresenterImp=new ShowPresenterImp(this);
//        mWebShowMessage.loadUrl("https://www.baidu.com/");
        mShowPresenterImp.showWebView(mWebShowMessage,mRes,mProShowProgress);

    }
}
