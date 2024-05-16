package com.example.networkapplication.model;

import com.example.networkapplication.entity.Detail;

public class NewsDetailResult extends BaseBean {
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

    public static class ResultBean {
        /**
         * uniquekey : 07854164017978cbc7fa8fd266c60ddd
         * detail : {"title":"江苏徐州供电构建青年员工量化管理机制","date":"2024-05-10 12:49:00","category":"头条","author_name":"每日看点快看","url":"https://mini.eastday.com/mobile/240510124911471269532.html","thumbnail_pic_s":"","thumbnail_pic_s02":"","thumbnail_pic_s03":""}
         * content : <p >本文转自：中国电力报</p><p >孙鹏</p><p >本报讯 （孙鹏）“我们的目标是让每一位青年员工都能找到适合自己的成长路径，对个人职业规划发展有更清晰的认知。”4月3日，国网江苏徐州供电公司人力资源部负责人介绍。面对青年员工职业发展路径不明确、上升通道受限等现状，该公司构建了量化管理机制，有效激发青年员工成长动力。</p><p >据了解，该量化管理机制主要包括职业发展、能力评估、责任落实三个方面。首先构建专业管理人员、班组长、专家三类人才素质匹配模型，对青年员工逐一进行发展潜力匹配分析，为青年员工明确发展方向。同时，建立涵盖竞赛、创新、课程、论文等7个领域的青年员工综合积分模型，依托自主开发的“知事识人”数字化平台，自动计算青年员工积分结果，评估青年员工能力、潜力，为人才的储备、选拔提供数据支撑。</p><p >培养责任落实方面，该公司制定了基层单位人才培养主体责任清单，并设置了培养计划覆盖率、职称技能通过率等多项量化指标，对青年员工培养成效进行科学、客观评价，评价结果纳入负责人业绩考核。</p><p >自青年员工成长量化管理机制实施以来，徐州供电新聘各级青年专家15名、四级副职12名，青年员工在省级以上竞赛调考中获奖33名、高级职称及以上专业技术资格占比上升35%，成效明显。</p>
         */

        private String uniquekey;
        private Detail detail;
        private String content;

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

        public Detail getDetail() {
            return detail;
        }

        public void setDetail(Detail detail) {
            this.detail = detail;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


    }
}
