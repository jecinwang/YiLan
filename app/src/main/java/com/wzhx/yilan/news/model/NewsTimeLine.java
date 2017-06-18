package com.wzhx.yilan.news.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wzhx on 2017/6/17.
 */

public class NewsTimeLine implements Serializable{

    /**
     * date : 20170617
     * stories : [{"images":["https://pic4.zhimg.com/v2-114d6b48fa8190bae9edc582a9ed3ce3.jpg"],"type":0,"id":9478713,"ga_prefix":"061713","title":"拍照是否减少了旅行的乐趣？"},{"images":["https://pic3.zhimg.com/v2-53818aa1cba5d50697e07a057675e906.jpg"],"type":0,"id":9477757,"ga_prefix":"061712","title":"大误 · 这红毯明明是绿的"},{"images":["https://pic4.zhimg.com/v2-0a9699511a1577e079e61d6d8530d3e7.jpg"],"type":0,"id":9480229,"ga_prefix":"061711","title":"怎样才能找到真正靠谱又会拍的自由摄影师？"},{"images":["https://pic2.zhimg.com/v2-74d8b2183dd176fc1eea7022827436b9.jpg"],"type":0,"id":9477247,"ga_prefix":"061711","title":"用《中华小当家》里出现的这口锅炖鸡，味道鲜美无比"},{"images":["https://pic3.zhimg.com/v2-e21f0bfe787108d9c91d4ed71f7e81f2.jpg"],"type":0,"id":9472100,"ga_prefix":"061710","title":"论文附上了引用出处，可这样依然是「学术不端」"},{"images":["https://pic4.zhimg.com/v2-5d0f262b77032fbcba5c5927731ba2e7.jpg"],"type":0,"id":9474154,"ga_prefix":"061708","title":"走进这个满是魔幻气息的岛屿，就像活在了史诗级大片里"},{"images":["https://pic2.zhimg.com/v2-0e18e3cbf739b2a4520ae7180b032681.jpg"],"type":0,"id":9479943,"ga_prefix":"061707","title":"不要律师，我要找一位经济学家为我上庭辩护"},{"images":["https://pic1.zhimg.com/v2-b74f9ebff5767cd184971e07f2fe0788.jpg"],"type":0,"id":9479445,"ga_prefix":"061707","title":"每当想起《仙剑奇侠传四》的结局，便是一声叹息"},{"images":["https://pic1.zhimg.com/v2-75177bebae1a940cb46990247d7b5858.jpg"],"type":0,"id":9479653,"ga_prefix":"061707","title":"精神病院只是冰山一角，还有大量病人和家属正处于煎熬中"},{"images":["https://pic1.zhimg.com/v2-7b033d14415c741601a6b81037b8f028.jpg"],"type":0,"id":9478842,"ga_prefix":"061706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-a370035be6057153f887564745a7c5df.jpg","type":0,"id":9479445,"ga_prefix":"061707","title":"每当想起《仙剑奇侠传四》的结局，便是一声叹息"},{"image":"https://pic2.zhimg.com/v2-1384cae182fc2b7b1c6e2923254f5ac5.jpg","type":0,"id":9479653,"ga_prefix":"061707","title":"精神病院只是冰山一角，还有大量病人和家属正处于煎熬中"},{"image":"https://pic3.zhimg.com/v2-95f0018381cf27dca4914c47173fd53a.jpg","type":0,"id":9478027,"ga_prefix":"061607","title":"看到别人有危险你会救吗？如果不救，该负法律责任吗？"},{"image":"https://pic1.zhimg.com/v2-5d3857ed92339ef8709bfbf682a94e9c.jpg","type":0,"id":9477457,"ga_prefix":"061607","title":"葬爱家族，人人都知道他们的存在，但没人知道他们在哪"},{"image":"https://pic1.zhimg.com/v2-da4ad90f253abd5c577566031c6f6e44.jpg","type":0,"id":9477654,"ga_prefix":"061520","title":"发生爆炸事故，我们该如何自救与急救？"}]
     */

    private String date;
    private List<StoriesModel> stories;
    private List<TopStoriesModel> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesModel> getStories() {
        return stories;
    }

    public void setStories(List<StoriesModel> stories) {
        this.stories = stories;
    }

    public List<TopStoriesModel> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesModel> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesModel implements Serializable{
        /**
         * images : ["https://pic4.zhimg.com/v2-114d6b48fa8190bae9edc582a9ed3ce3.jpg"]
         * type : 0
         * id : 9478713
         * ga_prefix : 061713
         * title : 拍照是否减少了旅行的乐趣？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesModel implements Serializable{
        /**
         * image : https://pic4.zhimg.com/v2-a370035be6057153f887564745a7c5df.jpg
         * type : 0
         * id : 9479445
         * ga_prefix : 061707
         * title : 每当想起《仙剑奇侠传四》的结局，便是一声叹息
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
