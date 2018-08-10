package demo.union.e.qq.com.tv.beans;

/**
 * Created by acer on 2018/6/4.
 */

public class UpData {


    /**
     * code : 200
     * msg :
     * data : {"versionCode":"1","apkUrl":"http:/xxxxx","versionName":"测试版本"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * versionCode : 1
         * apkUrl : http:/xxxxx
         * versionName : 测试版本
         */

        private String versionCode;
        private String apkUrl;
        private String versionName;

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }
}
