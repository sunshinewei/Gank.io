package com.example.weilong.gankio.net;



import com.example.weilong.gankio.dao.DataList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import static android.R.attr.category;

/**
 * Created by weilong on 2016/12/22.
 */

public interface ApiService {
    @GET("http://gank.io/api/data/{type}/{moth}/{day}")
    Observable<DataList> getDataList(@Path("type") String type,
                                     @Path("moth") String moth,
                                     @Path("day") String day);
    @GET("api/data/Android/10/1")
    Observable<DataList> getData();

    @GET("http://gank.io/api/history/content/2/1")
    Observable GetHistory();


    /**获取不同类型的清单
     * @return
     */
    @GET("api/search/query/listview/category/{type}/count/10/page/{mPage}")
    Observable<DataList> getTypeList(@Path("type") String type,@Path("mPage") int mPage);

}
