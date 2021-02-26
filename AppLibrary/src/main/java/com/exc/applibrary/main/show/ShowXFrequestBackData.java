package com.exc.applibrary.main.show;

import java.io.Serializable;
import java.util.List;

public class ShowXFrequestBackData implements Serializable {
    private int returnCode;
    private ReturnMsgBean returnMsg;
    private DataBean data;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public ReturnMsgBean getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(ReturnMsgBean returnMsg) {
        this.returnMsg = returnMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ReturnMsgBean implements Serializable {
        private String errCode;
        private String errDesc;

        public String getErrCode() {
            return errCode;
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        public String getErrDesc() {
            return errDesc;
        }

        public void setErrDesc(String errDesc) {
            this.errDesc = errDesc;
        }
    }

    public static class DataBean {
        private int successNum;
        private int defaultNum;
        private List<String> nums;

        public int getSuccessNum() {
            return successNum;
        }

        public void setSuccessNum(int successNum) {
            this.successNum = successNum;
        }

        public int getDefaultNum() {
            return defaultNum;
        }

        public void setDefaultNum(int defaultNum) {
            this.defaultNum = defaultNum;
        }

        public List<String> getNums() {
            return nums;
        }

        public void setNums(List<String> nums) {
            this.nums = nums;
        }
    }
}
