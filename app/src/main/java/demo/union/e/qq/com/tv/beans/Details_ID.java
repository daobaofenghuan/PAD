package demo.union.e.qq.com.tv.beans;

import java.util.List;

/**
 * Created by acer on 2018/4/28.
 */

public class Details_ID {


    /**
     * code : 200
     * msg :
     * data : {"deviceName":null,"deviceTypeName":"探头设备","deviceId":"gh_db8607de9a99_0f376e14fc8ae8b9","pm":{"data":"35","unit":"ug/m3","mass":"优"},"co2":{"data":"871","unit":"PPM"},"tem":{"data":"26","unit":"℃"},"hum":{"data":"57","unit":"%"},"tvoc":{"data":"0.51","unit":"mg/m³"},"hcho":{"data":"0.04","unit":"mg/m³"},"remain":{"data":"0","unit":"秒"},"screen":{"data":"0","unit":"秒"},"screens":null,"modeItem":{"value":"0","type":"210","choice":"0:关机,1:自动模式,2:睡眠模式,3:手动模式,4:超强模式"},"windItems":[],"funcs":[],"timers":[],"outerPm":"138","airQuality":"","outerHum":"82%","outerTem":"23℃","province":"","city":"","mac":"ECFABC1EC3BC","weather":"阴","area":null,"date":"2018年05月28日","aqi":"50","ip":"120.253.148.150","location":"上海,虹口区,城区","childItem":{"value":"0","type":"270","choice":"0:未开,1:已开"}}
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
         * deviceName : null
         * deviceTypeName : 探头设备
         * deviceId : gh_db8607de9a99_0f376e14fc8ae8b9
         * pm : {"data":"35","unit":"ug/m3","mass":"优"}
         * co2 : {"data":"871","unit":"PPM"}
         * tem : {"data":"26","unit":"℃"}
         * hum : {"data":"57","unit":"%"}
         * tvoc : {"data":"0.51","unit":"mg/m³"}
         * hcho : {"data":"0.04","unit":"mg/m³"}
         * remain : {"data":"0","unit":"秒"}
         * screen : {"data":"0","unit":"秒"}
         * screens : null
         * modeItem : {"value":"0","type":"210","choice":"0:关机,1:自动模式,2:睡眠模式,3:手动模式,4:超强模式"}
         * windItems : []
         * funcs : []
         * timers : []
         * outerPm : 138
         * airQuality :
         * outerHum : 82%
         * outerTem : 23℃
         * province :
         * city :
         * mac : ECFABC1EC3BC
         * weather : 阴
         * area : null
         * date : 2018年05月28日
         * aqi : 50
         * ip : 120.253.148.150
         * location : 上海,虹口区,城区
         * childItem : {"value":"0","type":"270","choice":"0:未开,1:已开"}
         */

        private String deviceName;
        private String deviceTypeName;
        private String deviceId;
        private PmBean pm;
        private Co2Bean co2;
        private TemBean tem;
        private HumBean hum;
        private TvocBean tvoc;
        private HchoBean hcho;
        private RemainBean remain;
        private ScreenBean screen;
        private Object screens;
        private ModeItemBean modeItem;
        private String outerPm;
        private String airQuality;
        private String outerHum;
        private String outerTem;
        private String province;
        private String city;
        private String mac;
        private String weather;
        private Object area;
        private String date;
        private String aqi;
        private String ip;
        private String location;
        private ChildItemBean childItem;
        private List<?> windItems;
        private List<?> funcs;
        private List<?> timers;

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceTypeName() {
            return deviceTypeName;
        }

        public void setDeviceTypeName(String deviceTypeName) {
            this.deviceTypeName = deviceTypeName;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public PmBean getPm() {
            return pm;
        }

        public void setPm(PmBean pm) {
            this.pm = pm;
        }

        public Co2Bean getCo2() {
            return co2;
        }

        public void setCo2(Co2Bean co2) {
            this.co2 = co2;
        }

        public TemBean getTem() {
            return tem;
        }

        public void setTem(TemBean tem) {
            this.tem = tem;
        }

        public HumBean getHum() {
            return hum;
        }

        public void setHum(HumBean hum) {
            this.hum = hum;
        }

        public TvocBean getTvoc() {
            return tvoc;
        }

        public void setTvoc(TvocBean tvoc) {
            this.tvoc = tvoc;
        }

        public HchoBean getHcho() {
            return hcho;
        }

        public void setHcho(HchoBean hcho) {
            this.hcho = hcho;
        }

        public RemainBean getRemain() {
            return remain;
        }

        public void setRemain(RemainBean remain) {
            this.remain = remain;
        }

        public ScreenBean getScreen() {
            return screen;
        }

        public void setScreen(ScreenBean screen) {
            this.screen = screen;
        }

        public Object getScreens() {
            return screens;
        }

        public void setScreens(Object screens) {
            this.screens = screens;
        }

        public ModeItemBean getModeItem() {
            return modeItem;
        }

        public void setModeItem(ModeItemBean modeItem) {
            this.modeItem = modeItem;
        }

        public String getOuterPm() {
            return outerPm;
        }

        public void setOuterPm(String outerPm) {
            this.outerPm = outerPm;
        }

        public String getAirQuality() {
            return airQuality;
        }

        public void setAirQuality(String airQuality) {
            this.airQuality = airQuality;
        }

        public String getOuterHum() {
            return outerHum;
        }

        public void setOuterHum(String outerHum) {
            this.outerHum = outerHum;
        }

        public String getOuterTem() {
            return outerTem;
        }

        public void setOuterTem(String outerTem) {
            this.outerTem = outerTem;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public ChildItemBean getChildItem() {
            return childItem;
        }

        public void setChildItem(ChildItemBean childItem) {
            this.childItem = childItem;
        }

        public List<?> getWindItems() {
            return windItems;
        }

        public void setWindItems(List<?> windItems) {
            this.windItems = windItems;
        }

        public List<?> getFuncs() {
            return funcs;
        }

        public void setFuncs(List<?> funcs) {
            this.funcs = funcs;
        }

        public List<?> getTimers() {
            return timers;
        }

        public void setTimers(List<?> timers) {
            this.timers = timers;
        }

        public static class PmBean {
            /**
             * data : 35
             * unit : ug/m3
             * mass : 优
             */

            private String data;
            private String unit;
            private String mass;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getMass() {
                return mass;
            }

            public void setMass(String mass) {
                this.mass = mass;
            }
        }

        public static class Co2Bean {
            /**
             * data : 871
             * unit : PPM
             */

            private String data;
            private String unit;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }

        public static class TemBean {
            /**
             * data : 26
             * unit : ℃
             */

            private String data;
            private String unit;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }

        public static class HumBean {
            /**
             * data : 57
             * unit : %
             */

            private String data;
            private String unit;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }

        public static class TvocBean {
            /**
             * data : 0.51
             * unit : mg/m³
             */

            private String data;
            private String unit;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }

        public static class HchoBean {
            /**
             * data : 0.04
             * unit : mg/m³
             */

            private String data;
            private String unit;

            public String getData() {
               return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }

        public static class RemainBean {
            /**
             * data : 0
             * unit : 秒
             */

            private String data;
            private String unit;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }

        public static class ScreenBean {
            /**
             * data : 0
             * unit : 秒
             */

            private String data;
            private String unit;

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }

        public static class ModeItemBean {
            /**
             * value : 0
             * type : 210
             * choice : 0:关机,1:自动模式,2:睡眠模式,3:手动模式,4:超强模式
             */

            private String value;
            private String type;
            private String choice;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getChoice() {
                return choice;
            }

            public void setChoice(String choice) {
                this.choice = choice;
            }
        }

        public static class ChildItemBean {
            /**
             * value : 0
             * type : 270
             * choice : 0:未开,1:已开
             */

            private String value;
            private String type;
            private String choice;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getChoice() {
                return choice;
            }

            public void setChoice(String choice) {
                this.choice = choice;
            }
        }
    }
}
