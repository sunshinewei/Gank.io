package com.example.weilong.gankio.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weilong on 2016/12/22.
 */

public class DataList implements Serializable{

    /**
     * error : false
     * results : [{"_id":"58560c74421aa97237bca8c3","createdAt":"2016-12-18T12:11:32.877Z","desc":"Facebook 开源文本布局库。","images":["http://img.gank.io/e3b5daff-b726-43f0-a42e-18be5b21c11e"],"publishedAt":"2016-12-19T11:57:16.232Z","source":"chrome","type":"Android","url":"https://github.com/facebookincubator/TextLayoutBuilder","used":true,"who":"Jason"},{"_id":"58568e04421aa9723d29b972","createdAt":"2016-12-18T21:24:20.288Z","desc":"波兰温馨圣诞广告","publishedAt":"2016-12-19T11:57:16.232Z","source":"chrome","type":"休息视频","url":"https://v.qq.com/x/page/s0353eggn8b.html","used":true,"who":"lxxself"},{"_id":"58569ab6421aa97237bca8c5","createdAt":"2016-12-18T22:18:30.807Z","desc":"12-18","publishedAt":"2016-12-19T11:57:16.232Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1favb116hm2j20u00u0gqi.jpg","used":true,"who":"daimajia"},{"_id":"5856c0f6421aa97237bca8c6","createdAt":"2016-12-19T01:01:42.752Z","desc":"高度可定制化日历视图，轻松实现范围选中效果","images":["http://img.gank.io/5195f823-cbbb-4b03-ab9b-f405ebbf9a1b"],"publishedAt":"2016-12-19T11:57:16.232Z","source":"web","type":"iOS","url":"https://github.com/LZTuna/ZYCalendar","used":true,"who":null},{"_id":"58574fb3421aa97237bca8c8","createdAt":"2016-12-19T11:10:43.425Z","desc":"基础深度学习概念备忘录","publishedAt":"2016-12-19T11:57:16.232Z","source":"web","type":"瞎推荐","url":"https://zhuanlan.zhihu.com/p/24436419","used":true,"who":"王下邀月熊(Chevalier)"},{"_id":"585757ec421aa9723d29b974","createdAt":"2016-12-19T11:45:48.555Z","desc":"JakeWharton 开源的新的 Rx 操作符：WindowIfChanged，检测 Window 变化状态，发出通知。","images":["http://img.gank.io/cc9e9cf4-15aa-4dd3-9215-b5f80f557948"],"publishedAt":"2016-12-19T11:57:16.232Z","source":"chrome","type":"Android","url":"https://github.com/JakeWharton/RxWindowIfChanged","used":true,"who":"代码家"},{"_id":"58575867421aa9723d29b976","createdAt":"2016-12-19T11:47:51.254Z","desc":"一个指令，把一个网址，变成一个 Mac App","images":["http://img.gank.io/831c9a52-e2ed-41a2-b1b2-1de060eca385"],"publishedAt":"2016-12-19T11:57:16.232Z","source":"chrome","type":"iOS","url":"https://github.com/jiahaog/nativefier","used":true,"who":"代码家"},{"_id":"58427e8b421aa939bb4637ed","createdAt":"2016-12-03T16:12:59.321Z","desc":"在状态模式（State Pattern）中，类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式。在状态模式中，我们创建表示各种状态的对象和一个行为随着状态对象改变而改变的 context 对象。","publishedAt":"2016-12-16T11:47:53.776Z","source":"web","type":"Android","url":"http://www.haotianyi.win/2016/11/java%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E4%B9%8B%E7%8A%B6%E6%80%81%E6%A8%A1%E5%BC%8F.html","used":true,"who":"HaoTianYi"},{"_id":"58465ce1421aa939bb463806","createdAt":"2016-12-06T14:38:25.801Z","desc":"iOS动画-从不会到熟练应用:一个总结的很好的动画文章~","images":["http://img.gank.io/35fc300d-9d98-4fb9-bafd-b45191bfc821"],"publishedAt":"2016-12-16T11:47:53.776Z","source":"web","type":"iOS","url":"http://www.jianshu.com/p/3f48fabaca19","used":true,"who":"抱紧我的小鲤鱼"},{"_id":"584acd8e421aa963efd90dbd","createdAt":"2016-12-09T23:28:14.631Z","desc":"利用动态加载实现手机淘宝的节日特效","images":["http://img.gank.io/9a9f8833-fc71-42fa-9762-548a179f019a"],"publishedAt":"2016-12-16T11:47:53.776Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/195eb1d8d0de","used":true,"who":"kot32"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Serializable{
        /**
         * _id : 58560c74421aa97237bca8c3
         * createdAt : 2016-12-18T12:11:32.877Z
         * desc : Facebook 开源文本布局库。
         * images : ["http://img.gank.io/e3b5daff-b726-43f0-a42e-18be5b21c11e"]
         * publishedAt : 2016-12-19T11:57:16.232Z
         * source : chrome
         * type : Android
         * url : https://github.com/facebookincubator/TextLayoutBuilder
         * used : true
         * who : Jason
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
