package com.exc.applibrary.main.model;

import java.io.Serializable;
import java.util.List;

import zuo.biao.library.util.StringUtil;

public class ShowListData implements Serializable {
    /**
     * code : 200
     * data : {"node":[{"height":100,"id":728,"ip":"192.168.112.142","name":"W1006","num":"10063","width":100,"x":"0","xMax":"1","y":"0","yMax":"1"}],"list":[{"duration":"00:00:39.77","frameNumber":993,"ftpId":1,"name":"1023红色国旗74210063.mp4","nodeNum":"10063","siteId":742,"srcvId":2341,"vidId":"c7c65405-17da-4644-bd59-cdee16128832","vidName":"1023红色国旗.mp4"}]}
     * message : SUCCESS!
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable {
        private List<NodeBean> node;
        private List<ListBean> list;

        public List<NodeBean> getNode() {
            return node;
        }

        public void setNode(List<NodeBean> node) {
            this.node = node;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class NodeBean implements Serializable {
            /**
             * height : 100
             * id : 728
             * ip : 192.168.112.142
             * name : W1006
             * num : 10063
             * width : 100
             * x : 0
             * xMax : 1
             * y : 0
             * yMax : 1
             */

            private int height;
            private int id;
            private String ip;
            private String name;
            private String num;
            private int width;
            private String x;
            private String xMax;
            private String y;
            private String yMax;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public String getX() {
                return x;
            }

            public void setX(String x) {
                this.x = x;
            }

            public String getXMax() {
                return xMax;
            }

            public void setXMax(String xMax) {
                this.xMax = xMax;
            }

            public String getY() {
                return y;
            }

            public void setY(String y) {
                this.y = y;
            }

            public String getYMax() {
                return yMax;
            }

            public void setYMax(String yMax) {
                this.yMax = yMax;
            }
        }

        public static class ListBean implements Serializable {
            /**
             * duration : 00:00:39.77
             * frameNumber : 993
             * ftpId : 1
             * name : 1023红色国旗74210063.mp4
             * nodeNum : 10063
             * siteId : 742
             * srcvId : 2341
             * vidId : c7c65405-17da-4644-bd59-cdee16128832
             * vidName : 1023红色国旗.mp4
             */

            private String duration;
            private int frameNumber;
            private int ftpId;
            private String name;
            private String nodeNum;
            private int siteId;
            private int srcvId;
            private String vidId;
            private String type;


            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }





            private String vidName;

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public int getFrameNumber() {
                return frameNumber;
            }

            public void setFrameNumber(int frameNumber) {
                this.frameNumber = frameNumber;
            }

            public int getFtpId() {
                return ftpId;
            }

            public void setFtpId(int ftpId) {
                this.ftpId = ftpId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNodeNum() {
                return nodeNum;
            }

            public void setNodeNum(String nodeNum) {
                this.nodeNum = nodeNum;
            }

            public int getSiteId() {
                return siteId;
            }

            public void setSiteId(int siteId) {
                this.siteId = siteId;
            }

            public int getSrcvId() {
                return srcvId;
            }

            public void setSrcvId(int srcvId) {
                this.srcvId = srcvId;
            }

            public String getVidId() {
                return vidId;
            }

            public void setVidId(String vidId) {
                this.vidId = vidId;
            }

            public String getVidName() {
                return vidName;
            }

            public void setVidName(String vidName) {
                this.vidName = vidName;
            }
        }
    }
}
