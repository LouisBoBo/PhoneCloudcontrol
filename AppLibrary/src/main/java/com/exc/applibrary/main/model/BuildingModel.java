package com.exc.applibrary.main.model;

import java.util.List;

public class BuildingModel {
    /**
     * code : 200
     * data : [{"addr":"洪山","buildingTypeSn":3,"city":"武汉市","description":"测试建筑物（联机）","district":"洪山区","electricityNodes":[],"id":2710,"isKey":1,"latitude":30.478743,"longitude":114.245671,"masterNodes":[],"name":"测试建筑物（联机）","nodes":[],"num":"LJJZW","partitionId":1,"province":"湖北省","quantity":1,"sites":[]},{"addr":"洪山","buildingTypeSn":6,"city":"武汉市","description":"测试建筑物（动态）","district":"洪山区","electricityNodes":[],"id":2711,"isKey":1,"latitude":30.474743,"longitude":114.243365,"masterNodes":[],"name":"测试建筑物（动态）","nodes":[],"num":"DTJZW","partitionId":1,"province":"湖北省","quantity":0,"sites":[]},{"addr":"洪山","buildingTypeSn":5,"city":"武汉市","description":"测试建筑物（开关）","district":"洪山区","electricityNodes":[],"id":2712,"isKey":1,"latitude":30.470652,"longitude":114.240038,"masterNodes":[],"name":"测试建筑物（开关）","nodes":[],"num":"KGJZW","partitionId":1,"province":"湖北省","quantity":1,"sites":[]},{"addr":"车城北路58号","buildingTypeSn":3,"city":"武汉市","description":"沌口媒体中心","district":"沌口区","electricityNodes":[],"id":3058,"isKey":1,"latitude":30.500166,"longitude":114.171624,"masterNodes":[],"name":"沌口媒体中心","nodes":[],"num":"MTZX1","partitionId":1,"province":"湖北省","quantity":1,"sites":[]},{"addr":"1","buildingTypeSn":3,"city":"北京城区","description":"1","district":"东城区","electricityNodes":[],"id":4291,"isKey":0,"latitude":1,"longitude":1,"masterNodes":[],"name":"1","nodes":[],"num":"78945","partitionId":1,"province":"北京市","quantity":1,"sites":[]},{"addr":"111","buildingTypeSn":6,"city":"吉林市","description":"大道","district":"昌邑区","electricityNodes":[],"id":4293,"isKey":1,"latitude":36.500166,"longitude":116.240038,"masterNodes":[],"name":"5645","nodes":[],"num":"AS123","partitionId":1,"province":"吉林省","quantity":1,"sites":[]},{"addr":"的撒打算","buildingTypeSn":3,"city":"北京城区","description":"sdsa sdas","district":"东城区","electricityNodes":[],"id":4298,"isKey":0,"latitude":8,"longitude":12,"masterNodes":[],"name":"15988888888","nodes":[],"num":"84562","partitionId":1,"province":"北京市","quantity":1,"sites":[]},{"addr":"1","buildingTypeSn":3,"city":"北京城区","description":"1","district":"东城区","electricityNodes":[],"id":4299,"isKey":0,"latitude":30.548531783318307,"longitude":114.30136620998384,"masterNodes":[],"name":"1","nodes":[],"num":"18264","partitionId":1,"province":"北京市","quantity":1,"sites":[]},{"addr":"的撒打算","buildingTypeSn":3,"city":"北京城区","description":"sdsa sdas","district":"东城区","electricityNodes":[],"id":4300,"isKey":1,"latitude":1,"longitude":1,"masterNodes":[],"name":"15988888888","nodes":[],"num":"68845","partitionId":1,"province":"北京市","quantity":1,"sites":[]}]
     * message : SUCCESS!
     */

    private int code;
    private String message;
    /**
     * addr : 洪山
     * buildingTypeSn : 3
     * city : 武汉市
     * description : 测试建筑物（联机）
     * district : 洪山区
     * electricityNodes : []
     * id : 2710
     * isKey : 1
     * latitude : 30.478743
     * longitude : 114.245671
     * masterNodes : []
     * name : 测试建筑物（联机）
     * nodes : []
     * num : LJJZW
     * partitionId : 1
     * province : 湖北省
     * quantity : 1
     * sites : []
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String addr;
        private int buildingTypeSn;
        private String city;
        private String description;
        private String district;
        private int id;
        private int isKey;
        private double latitude;
        private double longitude;
        private String name;
        private String num;
        private int partitionId;
        private String province;
        private int quantity;
        private List<?> electricityNodes;
        private List<?> masterNodes;
        private List<?> nodes;
        private List<?> sites;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getBuildingTypeSn() {
            return buildingTypeSn;
        }

        public void setBuildingTypeSn(int buildingTypeSn) {
            this.buildingTypeSn = buildingTypeSn;
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

        public int getIsKey() {
            return isKey;
        }

        public void setIsKey(int isKey) {
            this.isKey = isKey;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
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

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public List<?> getElectricityNodes() {
            return electricityNodes;
        }

        public void setElectricityNodes(List<?> electricityNodes) {
            this.electricityNodes = electricityNodes;
        }

        public List<?> getMasterNodes() {
            return masterNodes;
        }

        public void setMasterNodes(List<?> masterNodes) {
            this.masterNodes = masterNodes;
        }

        public List<?> getNodes() {
            return nodes;
        }

        public void setNodes(List<?> nodes) {
            this.nodes = nodes;
        }

        public List<?> getSites() {
            return sites;
        }

        public void setSites(List<?> sites) {
            this.sites = sites;
        }
    }
}
