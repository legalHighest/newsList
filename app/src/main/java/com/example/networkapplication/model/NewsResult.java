package com.example.networkapplication.model;

import com.example.networkapplication.entity.News;

import java.util.List;

public class NewsResult extends BaseBean{


    /**
     * reason : success
     * result : {"stat":"1","data":[{"uniquekey":"db61b977d9fabd0429c6d0c671aeb30e","title":"\u201c新时代女性的自我关爱\u201d主题沙龙暨双山街道福泰社区妇儿活动家园启动仪式举行","date":"2021-03-08 13:47:00","category":"头条","author_name":"鲁网","url":"https://mini.eastday.com/mobile/210308134708834241845.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134708_d0216565f1d6fe1abdfa03efb4f3e23c_0_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134708_d0216565f1d6fe1abdfa03efb4f3e23c_1_mwpm_03201609.png","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134708_d0216565f1d6fe1abdfa03efb4f3e23c_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"7d246cbfa9000fb5ac42fb3bb934a592","title":"滴滴发布女司机数据：2020年新增女性网约车司机超26万","date":"2021-03-08 13:40:00","category":"头条","author_name":"国青年网","url":"https://mini.eastday.com/mobile/210308134023641877777.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134023_7a9ca0543b00332147c42e1ee4146908_0_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134023_7a9ca0543b00332147c42e1ee4146908_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"c0611bea6eb961a57b21a0d1008bbe2e","title":"点赞！东海县公安局学雷锋见行动","date":"2021-03-08 13:38:00","category":"头条","author_name":"江南时报","url":"https://mini.eastday.com/mobile/210308133849892734209.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210308/20210308133849_b9f3d069a1ab400bf2d87fcc15793ca5_1_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210308/20210308133849_b9f3d069a1ab400bf2d87fcc15793ca5_2_mwpm_03201609.png","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210308/20210308133849_b9f3d069a1ab400bf2d87fcc15793ca5_3_mwpm_03201609.png","is_content":"1"}],"page":"1","pageSize":"3"}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }


    public  void setNewsResult(NewsResult newsResult){
        setReason(newsResult.getReason());
        setResult(newsResult.getResult());
        setError_code(newsResult.getError_code());
    }
    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"db61b977d9fabd0429c6d0c671aeb30e","title":"\u201c新时代女性的自我关爱\u201d主题沙龙暨双山街道福泰社区妇儿活动家园启动仪式举行","date":"2021-03-08 13:47:00","category":"头条","author_name":"鲁网","url":"https://mini.eastday.com/mobile/210308134708834241845.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134708_d0216565f1d6fe1abdfa03efb4f3e23c_0_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134708_d0216565f1d6fe1abdfa03efb4f3e23c_1_mwpm_03201609.png","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134708_d0216565f1d6fe1abdfa03efb4f3e23c_2_mwpm_03201609.png","is_content":"1"},{"uniquekey":"7d246cbfa9000fb5ac42fb3bb934a592","title":"滴滴发布女司机数据：2020年新增女性网约车司机超26万","date":"2021-03-08 13:40:00","category":"头条","author_name":"国青年网","url":"https://mini.eastday.com/mobile/210308134023641877777.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134023_7a9ca0543b00332147c42e1ee4146908_0_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210308/20210308134023_7a9ca0543b00332147c42e1ee4146908_1_mwpm_03201609.jpeg","is_content":"1"},{"uniquekey":"c0611bea6eb961a57b21a0d1008bbe2e","title":"点赞！东海县公安局学雷锋见行动","date":"2021-03-08 13:38:00","category":"头条","author_name":"江南时报","url":"https://mini.eastday.com/mobile/210308133849892734209.html","thumbnail_pic_s":"https://dfzximg02.dftoutiao.com/news/20210308/20210308133849_b9f3d069a1ab400bf2d87fcc15793ca5_1_mwpm_03201609.png","thumbnail_pic_s02":"https://dfzximg02.dftoutiao.com/news/20210308/20210308133849_b9f3d069a1ab400bf2d87fcc15793ca5_2_mwpm_03201609.png","thumbnail_pic_s03":"https://dfzximg02.dftoutiao.com/news/20210308/20210308133849_b9f3d069a1ab400bf2d87fcc15793ca5_3_mwpm_03201609.png","is_content":"1"}]
         * page : 1
         * pageSize : 3
         */

        private String stat;
        private String page;
        private String pageSize;
        private List<News> data;

        public  void setResultBean(ResultBean resultBean){
            setStat(resultBean.getStat());
            setPage(resultBean.getPage());
            setPageSize(resultBean.getPageSize());
            setData(resultBean.getData());
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public List<News> getData() {
            return data;
        }

        public void setData(List<News> data) {
            this.data = data;
        }


    }
}
