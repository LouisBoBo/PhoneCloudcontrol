package com.exc.applibrary.main.model;

import java.util.List;

public class SiteModel {

    /**
     * code : 200
     * data : {"node":["L0034","L0031","LOYDJ","DHTX9","DHTX1","DHTX2","DHTX4","DHTX3","YJC61","YJC62","L0027","L0026","ZLMJ3","ZLMJ2","CSL07","CSL06","CSL04","DHLJ1","FWZX1","CSL03","DHLJ2","CSL01","CSL02","L0505","FX001","FX002","SHDS1","YHJD1","ZL001","LFGJ1","CTCM1","WXYS1","WYYS2","YJC17","YJC11","YJC12","YJC16","XJL01","YJC06","JTD01","WCWEM","LFJD4","LFJD5","LFJD6","ZLMJ1","SDSQ4","CSKJ1","HTY10","HTY11","HTY01","PJCS1","XDXJY","ZYNF1","KXM01","LTSOM","HZJR1","YJC14","YJC10","DHRY3","DHRY1","DHRY2","YGG01","L0024","L0025","L0028","L0029","L0032","L0033","HBNY1","DHZX2","STH01"],"site":{"addr":"1保障线路（开关联机脱机）","buildings":[],"city":"武汉市","description":"1保障线路（开关联机脱机）20190506","district":"武昌区","id":1343,"name":"1号保障线路","nodes":[],"offlineNum":8,"onlineNum":2,"pageNum":1,"pageSize":10,"partitionId":1,"province":"湖北省","siteTypeId":3,"typeName":"联机站点"},"scriptlist":[],"videolist":[{"auditId":"20200929153612303","auditTime":"2020-09-29 15:36:12","auditor":"超级管理员","createTime":"2020-09-24 04:51:45","creator":"超级管理员","duration":"00:10:41.67","frameNumber":15400,"framerate":"24","ftpId":1,"height":"400","id":2327,"isDelete":0,"name":"渐变.mp4","partId":1,"status":1,"type":"mp4","updateTime":"2020-10-29 14:50:54","vidId":"232abf1b-6e62-47cc-9168-e0a480a7e956","width":"550"},{"auditId":"20200929153646590","auditTime":"2020-09-29 15:36:47","auditor":"超级管理员","createTime":"2020-09-24 04:49:24","creator":"超级管理员","duration":"00:00:39.72","frameNumber":993,"framerate":"25","ftpId":1,"height":"1080","id":2326,"isDelete":0,"name":"919红色国旗.mp4","partId":1,"status":1,"type":"mp4","updateTime":"2020-10-29 14:50:54","vidId":"cea009eb-94d1-4003-8ca3-19a5d288c21c","width":"1920"}],"strategyList":[{"description":"ff","id":59,"name":"全市启动","partitionId":1,"sn":111601,"state":1,"type":2}]}
     * message : SUCCESS!
     */

    private int code;
    /**
     * node : ["L0034","L0031","LOYDJ","DHTX9","DHTX1","DHTX2","DHTX4","DHTX3","YJC61","YJC62","L0027","L0026","ZLMJ3","ZLMJ2","CSL07","CSL06","CSL04","DHLJ1","FWZX1","CSL03","DHLJ2","CSL01","CSL02","L0505","FX001","FX002","SHDS1","YHJD1","ZL001","LFGJ1","CTCM1","WXYS1","WYYS2","YJC17","YJC11","YJC12","YJC16","XJL01","YJC06","JTD01","WCWEM","LFJD4","LFJD5","LFJD6","ZLMJ1","SDSQ4","CSKJ1","HTY10","HTY11","HTY01","PJCS1","XDXJY","ZYNF1","KXM01","LTSOM","HZJR1","YJC14","YJC10","DHRY3","DHRY1","DHRY2","YGG01","L0024","L0025","L0028","L0029","L0032","L0033","HBNY1","DHZX2","STH01"]
     * site : {"addr":"1保障线路（开关联机脱机）","buildings":[],"city":"武汉市","description":"1保障线路（开关联机脱机）20190506","district":"武昌区","id":1343,"name":"1号保障线路","nodes":[],"offlineNum":8,"onlineNum":2,"pageNum":1,"pageSize":10,"partitionId":1,"province":"湖北省","siteTypeId":3,"typeName":"联机站点"}
     * scriptlist : []
     * videolist : [{"auditId":"20200929153612303","auditTime":"2020-09-29 15:36:12","auditor":"超级管理员","createTime":"2020-09-24 04:51:45","creator":"超级管理员","duration":"00:10:41.67","frameNumber":15400,"framerate":"24","ftpId":1,"height":"400","id":2327,"isDelete":0,"name":"渐变.mp4","partId":1,"status":1,"type":"mp4","updateTime":"2020-10-29 14:50:54","vidId":"232abf1b-6e62-47cc-9168-e0a480a7e956","width":"550"},{"auditId":"20200929153646590","auditTime":"2020-09-29 15:36:47","auditor":"超级管理员","createTime":"2020-09-24 04:49:24","creator":"超级管理员","duration":"00:00:39.72","frameNumber":993,"framerate":"25","ftpId":1,"height":"1080","id":2326,"isDelete":0,"name":"919红色国旗.mp4","partId":1,"status":1,"type":"mp4","updateTime":"2020-10-29 14:50:54","vidId":"cea009eb-94d1-4003-8ca3-19a5d288c21c","width":"1920"}]
     * strategyList : [{"description":"ff","id":59,"name":"全市启动","partitionId":1,"sn":111601,"state":1,"type":2}]
     */

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

    public static class DataBean {
        /**
         * addr : 1保障线路（开关联机脱机）
         * buildings : []
         * city : 武汉市
         * description : 1保障线路（开关联机脱机）20190506
         * district : 武昌区
         * id : 1343
         * name : 1号保障线路
         * nodes : []
         * offlineNum : 8
         * onlineNum : 2
         * pageNum : 1
         * pageSize : 10
         * partitionId : 1
         * province : 湖北省
         * siteTypeId : 3
         * typeName : 联机站点
         */

        private SiteBean site;
        private List<String> node;
        private List<?> scriptlist;
        /**
         * auditId : 20200929153612303
         * auditTime : 2020-09-29 15:36:12
         * auditor : 超级管理员
         * createTime : 2020-09-24 04:51:45
         * creator : 超级管理员
         * duration : 00:10:41.67
         * frameNumber : 15400
         * framerate : 24
         * ftpId : 1
         * height : 400
         * id : 2327
         * isDelete : 0
         * name : 渐变.mp4
         * partId : 1
         * status : 1
         * type : mp4
         * updateTime : 2020-10-29 14:50:54
         * vidId : 232abf1b-6e62-47cc-9168-e0a480a7e956
         * width : 550
         */

        private List<VideolistBean> videolist;
        /**
         * description : ff
         * id : 59
         * name : 全市启动
         * partitionId : 1
         * sn : 111601
         * state : 1
         * type : 2
         */

        private List<StrategyListBean> strategyList;

        public SiteBean getSite() {
            return site;
        }

        public void setSite(SiteBean site) {
            this.site = site;
        }

        public List<String> getNode() {
            return node;
        }

        public void setNode(List<String> node) {
            this.node = node;
        }

        public List<?> getScriptlist() {
            return scriptlist;
        }

        public void setScriptlist(List<?> scriptlist) {
            this.scriptlist = scriptlist;
        }

        public List<VideolistBean> getVideolist() {
            return videolist;
        }

        public void setVideolist(List<VideolistBean> videolist) {
            this.videolist = videolist;
        }

        public List<StrategyListBean> getStrategyList() {
            return strategyList;
        }

        public void setStrategyList(List<StrategyListBean> strategyList) {
            this.strategyList = strategyList;
        }

        public static class SiteBean {
            private String addr;
            private String city;
            private String description;
            private String district;
            private int id;
            private String name;
            private int offlineNum;
            private int onlineNum;
            private int pageNum;
            private int pageSize;
            private int partitionId;
            private String province;
            private int siteTypeId;
            private String typeName;
            private List<?> buildings;
            private List<?> nodes;

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOfflineNum() {
                return offlineNum;
            }

            public void setOfflineNum(int offlineNum) {
                this.offlineNum = offlineNum;
            }

            public int getOnlineNum() {
                return onlineNum;
            }

            public void setOnlineNum(int onlineNum) {
                this.onlineNum = onlineNum;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPartitionId() {
                return partitionId;
            }

            public void setPartitionId(int partitionId) {
                this.partitionId = partitionId;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getSiteTypeId() {
                return siteTypeId;
            }

            public void setSiteTypeId(int siteTypeId) {
                this.siteTypeId = siteTypeId;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public List<?> getBuildings() {
                return buildings;
            }

            public void setBuildings(List<?> buildings) {
                this.buildings = buildings;
            }

            public List<?> getNodes() {
                return nodes;
            }

            public void setNodes(List<?> nodes) {
                this.nodes = nodes;
            }
        }

        public static class VideolistBean {
            private String auditId;
            private String auditTime;
            private String auditor;
            private String createTime;
            private String creator;
            private String duration;
            private int frameNumber;
            private String framerate;
            private int ftpId;
            private String height;
            private int id;
            private int isDelete;
            private String name;
            private int partId;
            private int status;
            private String type;
            private String updateTime;
            private String vidId;
            private String width;

            public String getAuditId() {
                return auditId;
            }

            public void setAuditId(String auditId) {
                this.auditId = auditId;
            }

            public String getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(String auditTime) {
                this.auditTime = auditTime;
            }

            public String getAuditor() {
                return auditor;
            }

            public void setAuditor(String auditor) {
                this.auditor = auditor;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

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

            public String getFramerate() {
                return framerate;
            }

            public void setFramerate(String framerate) {
                this.framerate = framerate;
            }

            public int getFtpId() {
                return ftpId;
            }

            public void setFtpId(int ftpId) {
                this.ftpId = ftpId;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(int isDelete) {
                this.isDelete = isDelete;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPartId() {
                return partId;
            }

            public void setPartId(int partId) {
                this.partId = partId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getVidId() {
                return vidId;
            }

            public void setVidId(String vidId) {
                this.vidId = vidId;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }
        }

        public static class StrategyListBean {
            private String description;
            private int id;
            private String name;
            private int partitionId;
            private int sn;
            private int state;
            private int type;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPartitionId() {
                return partitionId;
            }

            public void setPartitionId(int partitionId) {
                this.partitionId = partitionId;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
