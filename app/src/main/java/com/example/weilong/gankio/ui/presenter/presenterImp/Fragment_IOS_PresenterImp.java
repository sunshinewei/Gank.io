package com.example.weilong.gankio.ui.presenter.presenterImp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.weilong.gankio.R;
import com.example.weilong.gankio.Source_Type;
import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.net.ApiNet;
import com.example.weilong.gankio.net.ApiService;
import com.example.weilong.gankio.ui.activity.ShowActivity;
import com.example.weilong.gankio.ui.adapter.IOSFragmentAdapter;
import com.example.weilong.gankio.ui.listener.OnClickListener;
import com.example.weilong.gankio.ui.listener.OnLongClickListener;
import com.example.weilong.gankio.ui.presenter.Fragment_IOS_Presenter;
import com.example.weilong.gankio.ui.view.MainRecyclerView;
import com.example.weilong.gankio.ui.view.SpaceItemDecoration;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weilong on 2016/12/27.
 */

public class Fragment_IOS_PresenterImp implements Fragment_IOS_Presenter {
    private Context mContext;
    private ApiService mApiService;
    private RecyclerView.LayoutManager mLayoutManager;
    private IOSFragmentAdapter mIOSFragmentAdapter;
    private SpaceItemDecoration mSpaceItemDecoration;
    private int mPage = 0;
    public Fragment_IOS_PresenterImp(Context mContext){
        this.mContext=mContext;
        mApiService= ApiNet.initApiService();
        mSpaceItemDecoration=new SpaceItemDecoration((int) mContext.getResources().getDimension(R.dimen.space));
    }
    @Override
    public void showRecycler(final MainRecyclerView mRecyFragmentIos, final MaterialRefreshLayout mRefreshIos) {
        mRefreshIos.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mPage=0;
                netRequest(mRecyFragmentIos,mRefreshIos);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                mPage++;
                netRequest(mRecyFragmentIos,mRefreshIos);
            }
        });
    }

    private void netRequest(final MainRecyclerView mRecyFragmentIos, final MaterialRefreshLayout mRefreshIos){
        mApiService.getTypeList("iOS",mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataList>() {
                    @Override
                    public void onCompleted() {
                        if (mPage==0){
                            mRefreshIos.finishRefresh();
                        }else {
                            mRefreshIos.finishRefreshLoadMore();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, "网络有误", Toast.LENGTH_SHORT).show();
                        if (mPage==0){
                            mRefreshIos.finishRefresh();
                        }else {
                            mRefreshIos.finishRefreshLoadMore();
                        }
                    }

                    @Override
                    public void onNext(DataList list) {
                        setLayoutManager(mRecyFragmentIos,list);
                    }
                });
    }

    @Override
    public void startActivity(DataList.ResultsBean mRes) {
        Intent intent=new Intent(mContext, ShowActivity.class);
        intent.putExtra(Source_Type.GO_SHOW, mRes);
        mContext.startActivity(intent);
    }

    private void setLayoutManager(MainRecyclerView mRecyFragmentIos, final DataList list){
        mRecyFragmentIos.addItemDecoration(mSpaceItemDecoration);
        mLayoutManager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        mRecyFragmentIos.setLayoutManager(mLayoutManager);
        mRecyFragmentIos.setHasFixedSize(true);
        if (mIOSFragmentAdapter==null || mPage==0){
            mIOSFragmentAdapter=new IOSFragmentAdapter(mContext,list.getResults());
        }else {
            mIOSFragmentAdapter.setData(list.getResults());
        }

        mRecyFragmentIos.setAdapter(mIOSFragmentAdapter);
        mIOSFragmentAdapter.setItemOnClickListener(new OnClickListener() {
            @Override
            public void setItemOnClickListener(View view, int position) {
                DataList.ResultsBean bean = mIOSFragmentAdapter.getData().get(position);
                startActivity(bean);
            }
        });
        mIOSFragmentAdapter.setItemLongOnClickListener(new OnLongClickListener() {
            @Override
            public void setItemLongClickListener(View view, int pos) {
                DataList.ResultsBean bean = mIOSFragmentAdapter.getData().get(pos);
                showShare(bean);
            }
        });
    }
    /**
     * 分享
     */
    private void showShare(DataList.ResultsBean bean) {
        ShareSDK.initSDK(mContext);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(bean.getDesc());
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(bean.getUrl());
// text是分享文本，所有平台都需要这个字段
        oks.setText(bean.getDesc());
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mContext.getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(mContext);
    }
}
