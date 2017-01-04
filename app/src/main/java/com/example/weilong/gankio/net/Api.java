package com.example.weilong.gankio.net;

/**
 * Created by weilong on 2016/12/20.
 */
public class Api {
    public static final String BASE_URL="http://gank.io/";
    public static final String MAIN_URL=BASE_URL+"api/data/Android/10/1";
    public static final String TYPE_URL="http://gank.io/api/search/query/listview/category/Android/count/10/page/1";
//    category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
//    count 最大 50
    public static final String TIME_DATA="http://gank.io/api/history/content/2/1";
//    注： 2 代表 2 个数据，1 代表：取第一页数据

//    获取发过干货日期接口:方式 GET
    public static final String HISTORY="http://gank.io/api/day/history";

//    方式: POST  支持提交干货到审核区:
    public static final String SUBMIT="https://gank.io/api/add2gank";


}
