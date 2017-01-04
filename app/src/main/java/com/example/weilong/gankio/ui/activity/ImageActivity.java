package com.example.weilong.gankio.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.weilong.gankio.R;
import com.example.weilong.gankio.Source_Type;
import com.example.weilong.gankio.baseui.activity.GankActivity;
import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.ui.presenter.presenterImp.ImagePresenterImp;
import com.squareup.picasso.Picasso;

public class ImageActivity extends GankActivity {
    private ImageView mImgImageGirl;
    private ImagePresenterImp mImagePresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbr= (Toolbar) findViewById(R.id.toolbar_image);
        setSupportActionBar(toolbr);
        initView();
        initData();
    }


    private void initView(){
        mImgImageGirl = (ImageView) findViewById(R.id.img_image_girl);
    }
    private void initData(){
        showImage();
//        mImagePresenterImp=new ImagePresenterImp(this);
    }

    private void showImage(){
        Intent intent=this.getIntent();
        DataList.ResultsBean mRes= (DataList.ResultsBean) intent.getSerializableExtra(Source_Type.GO_SHOW);
        Picasso.with(this)
                .load(mRes.getUrl())
                .into(mImgImageGirl);
    }

}
