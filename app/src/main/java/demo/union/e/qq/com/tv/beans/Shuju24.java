package demo.union.e.qq.com.tv.beans;

import java.util.List;

/**
 * Created by acer on 2018/5/18.
 */

public class Shuju24 {

    /**
     * code : 200
     * msg :
     * data : [{"name":"室内pm值","type":"110","unit":"ug/m3","xdata":["2018-05-18 12:30:00","2018-05-18 13:00:00","2018-05-18 13:30:00","2018-05-18 14:00:00","2018-05-18 14:30:00","2018-05-18 15:00:00","2018-05-18 15:30:00","2018-05-18 16:00:00","2018-05-18 16:30:00","2018-05-18 17:00:00","2018-05-18 17:30:00","2018-05-18 18:00:00","2018-05-18 18:30:00","2018-05-18 19:00:00","2018-05-18 19:30:00","2018-05-18 20:00:00","2018-05-18 20:30:00"],"ydata":["0","0","0","0","0","0","0","34","35","34","31","31","31","31","59","40","35"]},{"name":"室外pm值","type":"111","unit":"ug/m3","xdata":["2018-05-18 12:30:00","2018-05-18 13:00:00","2018-05-18 13:30:00","2018-05-18 14:00:00","2018-05-18 14:30:00","2018-05-18 15:00:00","2018-05-18 15:30:00","2018-05-18 16:00:00","2018-05-18 16:30:00","2018-05-18 17:00:00","2018-05-18 17:30:00","2018-05-18 18:00:00","2018-05-18 18:30:00","2018-05-18 19:00:00","2018-05-18 19:30:00","2018-05-18 20:00:00","2018-05-18 20:30:00"],"ydata":[]},{"name":"室内二氧化碳","type":"120","unit":"PPM","xdata":["2018-05-18 12:30:00","2018-05-18 13:00:00","2018-05-18 13:30:00","2018-05-18 14:00:00","2018-05-18 14:30:00","2018-05-18 15:00:00","2018-05-18 15:30:00","2018-05-18 16:00:00","2018-05-18 16:30:00","2018-05-18 17:00:00","2018-05-18 17:30:00","2018-05-18 18:00:00","2018-05-18 18:30:00","2018-05-18 19:00:00","2018-05-18 19:30:00","2018-05-18 20:00:00","2018-05-18 20:30:00"],"ydata":["0","0","0","0","0","0","0","1372","1555","1545","1451","1364","1303","1347","1327","1269","1155"]},{"name":"室外二氧化碳","type":"121","unit":"PPM","xdata":["2018-05-18 12:30:00","2018-05-18 13:00:00","2018-05-18 13:30:00","2018-05-18 14:00:00","2018-05-18 14:30:00","2018-05-18 15:00:00","2018-05-18 15:30:00","2018-05-18 16:00:00","2018-05-18 16:30:00","2018-05-18 17:00:00","2018-05-18 17:30:00","2018-05-18 18:00:00","2018-05-18 18:30:00","2018-05-18 19:00:00","2018-05-18 19:30:00","2018-05-18 20:00:00","2018-05-18 20:30:00"],"ydata":[]}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 室内pm值
         * type : 110
         * unit : ug/m3
         * xdata : ["2018-05-18 12:30:00","2018-05-18 13:00:00","2018-05-18 13:30:00","2018-05-18 14:00:00","2018-05-18 14:30:00","2018-05-18 15:00:00","2018-05-18 15:30:00","2018-05-18 16:00:00","2018-05-18 16:30:00","2018-05-18 17:00:00","2018-05-18 17:30:00","2018-05-18 18:00:00","2018-05-18 18:30:00","2018-05-18 19:00:00","2018-05-18 19:30:00","2018-05-18 20:00:00","2018-05-18 20:30:00"]
         * ydata : ["0","0","0","0","0","0","0","34","35","34","31","31","31","31","59","40","35"]
         */

        private String name;
        private String type;
        private String unit;
        private List<String> xdata;
        private List<String> ydata;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public List<String> getXdata() {
            return xdata;
        }

        public void setXdata(List<String> xdata) {
            this.xdata = xdata;
        }

        public List<String> getYdata() {
            return ydata;
        }

        public void setYdata(List<String> ydata) {
            this.ydata = ydata;
        }
    }
}