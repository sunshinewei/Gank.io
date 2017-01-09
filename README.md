# Gank.io
##### 本项目采用mvp设计模式，利用每日一推开源接口获取网络数据
1.使用Rxjava+OkHttp+Retrofit进行网络请求；
2.使用RecyclerView呈现数据，利用Android5.0新特性meaterial desigin；
3.实现了下拉刷新和上拉加载更多；
4.使用了侧滑，通过不同类别进行呈现；
5.集成了第三方分享和登录，有QQ、微信、微博登录；
6.对于条目长按即可分享。
##### 添加依赖：
<pre><code>
    compile 'com.android.support:appcompat-v7:25.+'
    testCompile 'junit:junit:4.12'
    compile project(':gank')
    compile 'com.android.support:recyclerview-v7:25.1.0'
    //下拉刷新上拉加载更多
    compile 'com.cjj.materialrefeshlayout:library:1.3.0'
    compile 'com.squareup.picasso:picasso:2.5.2'
    compile 'com.mxn.soul:flowingdrawer-core:1.2.5'
    compile 'com.nineoldandroids:library:2.4.0'
    compile 'com.android.support:cardview-v7:25.1.0'
    compile 'com.android.support:design:25.1.0'
    compile 'com.squareup.okhttp3:okhttp:3.3.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.2.0'
    compile 'com.squareup.retrofit2:retrofit:2.0.2'
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.2'
    compile 'io.reactivex:rxandroid:1.2.1'
    compile 'liji.library.dev:excellentwaveview:0.4.0'
    compile 'com.jakewharton:butterknife:6.1.0'
    //butterKnife
    compile files('libs/MobCommons-2016.1222.1756.jar')
    compile files('libs/MobTools-2016.1222.1756.jar')
    compile files('libs/ShareSDK-Core-2.8.0.jar')
    compile files('libs/ShareSDK-QQ-2.8.0.jar')
    compile files('libs/ShareSDK-QZone-2.8.0.jar')
    compile files('libs/ShareSDK-SinaWeibo-2.8.0.jar')
    compile files('libs/ShareSDK-Wechat-2.8.0.jar')
    compile files('libs/ShareSDK-Wechat-Core-2.8.0.jar')
    compile files('libs/ShareSDK-Wechat-Favorite-2.8.0.jar')
    compile files('libs/ShareSDK-Wechat-Moments-2.8.0.jar')
    compile 'org.greenrobot:eventbus:3.0.0'
</code></pre>

##### 实现结果呈现：

![](https://github.com/sunshinewei/Gank.io/blob/master/cutimage/ios.gif)![](https://github.com/sunshinewei/Gank.io/blob/master/cutimage/girl.gif)![](https://github.com/sunshinewei/Gank.io/blob/master/cutimage/left.gif)
![](https://github.com/sunshinewei/Gank.io/blob/master/cutimage/login.gif)![](https://github.com/sunshinewei/Gank.io/blob/master/cutimage/ui.gif)![](https://github.com/sunshinewei/Gank.io/blob/master/cutimage/share.gif)

