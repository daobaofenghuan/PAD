package demo.union.e.qq.com.tv.db.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by 他的猫 on 2017/1/7.
 * 用戶注册表
 */
@DatabaseTable(tableName = "Life_Devices")
public class Life_Devices implements Serializable {
    /**
     * 主键ID
     */
    @DatabaseField(id = true)
    protected String id;
    @DatabaseField
    protected String deviceid;
    @DatabaseField
    private String storid;
    @DatabaseField
    private String devicename;

    public String getParent() {
        return Parent;
    }

    public void setParent(String parent) {
        Parent = parent;
    }

    @DatabaseField
    private String Parent;
    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }


    private static final long serialVersionUID = 6337104618534280060L;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }


    public String getStorid() {
        return storid;
    }

    public void setStorid(String storid) {
        this.storid = storid;
    }


}
