package demo.union.e.qq.com.tv.db.bean;

import java.util.List;

/**
 * Created by acer on 2018/5/5.
 */

public class GaiLan {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Life_Devices> getLife_devices() {
        return life_devices;
    }

    public void setLife_devices(List<Life_Devices> life_devices) {
        this.life_devices = life_devices;
    }

    public List<Life_Devices> life_devices;
}
