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
import com.example.weilong.gankio.ui.activity.ImageActivity;
import com.example.weilong.gankio.ui.activity.ShowActivity;
import com.example.weilong.gankio.ui.adapter.MainRecyclerAdapter;
import com.example.weilong.gankio.ui.listener.OnClickListener;
import com.example.weilong.gankio.ui.listener.OnLongClickListener;
import com.example.weilong.gankio.ui.presenter.FragmentFirstPresenter;
import com.example.weilong.gankio.ui.view.MainRecyclerView;
import com.example.weilong.gankio.ui.view.SpaceItemDecoration;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weilong on 2016/12/26.
 */

public class FragmentFirstPresenterImp implements FragmentFirstPresenter {
    private RecyclerView.LayoutManager mLayoutManager;
    private MainRecyclerAdapter mMainRecyclerAdapter;
    private ApiService mApiService;
    private Context mContext;
    private SpaceItemDecoration mSpaceItemDecoration;
    private int mPage = 1;

    public FragmentFirstPresenterImp(Context mContext) {
        this.mContext = mContext;
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mApiService = ApiNet.initApiService();
        mSpaceItemDecoration=new SpaceItemDecoration((int) mContext.getResources().getDimension(R.dimen.space));
    }

    public void showRecycler(final MainRecyclerView mainRecyclerView, MaterialRefreshLayout mRefreshFirst) {
        mainRecyclerView.setLayoutManager(mLayoutManager);
        refreshContext(mainRecyclerView, mRefreshFirst);

    }


    /**
     * 刷新
     * @param mainRecyclerView
     * @param mRefreshFirst
     */
    private void refreshContext(final MainRecyclerView mainRecyclerView, final MaterialRefreshLayout mRefreshFirst) {
        mRefreshFirst.setMaterialRefreshListener(new MaterialRefreshListener(){
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mPage=1;
                System.out.println("进入刷新");
                netRequest(mainRecyclerView,mRefreshFirst);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                mPage++;
                netRequest(mainRecyclerView,mRefreshFirst);
            }
        });

    }


    public void netRequest(final MainRecyclerView mainRecyclerView, final MaterialRefreshLayout mRefreshFirst) {
        mApiService.getTypeList("all", mPage).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataList>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("刷新完成");
                        if (mPage==1){
                            mRefreshFirst.finishRefresh();
                        }else {
                            mRefreshFirst.finishRefreshLoadMore();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(mContext, "网络有误", Toast.LENGTH_SHORT).show();
                        if (mPage==1){
                            mRefreshFirst.finishRefresh();
                        }else {
                            mRefreshFirst.finishRefreshLoadMore();
                        }
                    }

                    @Override
                    public void onNext(DataList list) {
                        if (list != null) {
                            setLayoutManager(mainRecyclerView,list);
                        }
                    }
                });
    }
    private void setLayoutManager(MainRecyclerView mainRecyclerView, final DataList list) {
        mainRecyclerView.addItemDecoration(mSpaceItemDecoration);
        if (mMainRecyclerAdapter == null || mPage == 1) {
            mMainRecyclerAdapter = new MainRecyclerAdapter(mContext, list.getResults());
            mainRecyclerView.setAdapter(mMainRecyclerAdapter);
        } else {
            mMainRecyclerAdapter.setData(list.getResults());
        }

        mainRecyclerView.setHasFixedSize(true);
        mMainRecyclerAdapter.setItemOnclickListener(new OnClickListener() {
            @Override
            public void setItemOnClickListener(View view, int position) {
                DataList.ResultsBean bean = mMainRecyclerAdapter.getData().get(position);
                //all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
//                if (bean.getType().equalsIgnoreCase("android") || bean.getType().equalsIgnoreCase("ios")
//                        || bean.getType().equals("前端")){
//                    startActivity(bean);
//                }else if (bean.getType().equals("福利")){
//
//                }
                startActivity(bean);
            }
        });
        mMainRecyclerAdapter.setItemLongOnClickListener(new OnLongClickListener() {
            @Override
            public void setItemLongClickListener(View view, int pos) {
                DataList.ResultsBean bean = mMainRecyclerAdapter.getData().get(pos);
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

    public void startActivity(DataList.ResultsBean bean) {
        Intent intent;
        if (bean.getType().equals("福利")) {
            intent = new Intent(mContext, ImageActivity.class);
        } else {
            intent = new Intent(mContext, ShowActivity.class);
        }
        intent.putExtra(Source_Type.GO_SHOW, bean);
        mContext.startActivity(intent);
    }
}
