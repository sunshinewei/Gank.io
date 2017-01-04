package com.example.weilong.gankio.ui.presenter.presenterImp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.example.weilong.gankio.dao.DataList;
import com.example.weilong.gankio.net.ApiNet;
import com.example.weilong.gankio.net.ApiService;
import com.example.weilong.gankio.ui.adapter.MainRecyclerAdapter;
import com.example.weilong.gankio.ui.presenter.MainPresenter;
import com.example.weilong.gankio.ui.view.MainRecyclerView;
import com.example.weilong.gankio.ui.viewImp.MainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by weilong on 2016/12/21.
 */

public class MainPresenterImp implements MainPresenter{
    private MainView mMainView;
    private ApiService apiService;
    private MainRecyclerAdapter mMainRecyclerAdapter;
    private Context mContext;
    public MainPresenterImp(Context mContext,MainView mMainView){
        this.mContext=mContext;
        this.mMainView=mMainView;
         apiService = ApiNet.initApiService();
    }

    public void recyclerviewAdapter(final MainRecyclerView mainRecyclerView){
        if (mainRecyclerView!=null){
            apiService.getData().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<DataList>() {
                        @Override
                        public void onCompleted() {
                            System.out.println("响应完成");
                        }
                        @Override
                        public void onError(Throwable e) {
                            System.out.println("网络出现错误");
                        }
                        @Override
                        public void onNext(DataList dataList) {
                            System.out.println("响应数据"+dataList);
                            if (dataList!=null){
                                recyclerViewManager(mainRecyclerView);
                                mMainRecyclerAdapter=new MainRecyclerAdapter(mContext,dataList.getResults());
                                mainRecyclerView.setAdapter(mMainRecyclerAdapter);
                            }

                        }
                    });
        }
    }
    /*设置RecyclerView的布局管理器*/
    private void recyclerViewManager(MainRecyclerView mainRecyclerView){
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    }
    public void getDataList(){
    }

    public void onItemOnClick(){

    }
}
