package demo.union.e.qq.com.tv.db.bean;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.util.List;
import java.util.UUID;

import demo.union.e.qq.com.tv.base.Z_DataBaseHelper;


/**
 * Created by luoliwen on 16/4/28.
 * 用户注册信息
 */
public class Z_Login_Life_DeviceDao {
    private Context context;
    private Dao<Life_Devices, Integer> userDao;
    private Z_DataBaseHelper helper;

    public Z_Login_Life_DeviceDao(Context context) {
        this.context = context;
        helper = Z_DataBaseHelper.getInstance(context);
        try {
            userDao = helper.getDao(Life_Devices.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addDevices(String devicename, String Deviceid, String Storid,String Parent
    ) {
        try {

            Life_Devices user = new Life_Devices();
            user.setId(UUID.randomUUID().toString());
            user.setDevicename(devicename);
            user.setDeviceid(Deviceid);
            user.setStorid(Storid);
            user.setParent(Parent);

            userDao.create(user);
            Log.e("0388", "添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("0388", "添加失敗" + e.toString());
        }
    }


    public boolean shifoucunzai(String account) {
        try {
            List<Life_Devices> account1 = userDao.queryBuilder().where().eq("deviceid", account).query();
            if (account1.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(String account) {
        try {
            Life_Devices account1 = userDao.queryBuilder().where().eq("storid", account).query().get(0);
            userDao.delete(account1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    List<Life_Devices> getId;

    public List<Life_Devices> getID(String account,String fenzu) {
        try {
            getId = userDao.queryBuilder().where().eq("storid", account).and().eq("Parent", fenzu).query();
            return getId;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    List<Life_Devices> account1;

    public List<Life_Devices> quell() {
        try {
            account1 = userDao.queryForAll();
            return account1;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("1111", "xx2222" + e.toString());
        }
        return null;
    }

    /**
     * 通过设备号设置楼层
     */
    public void updateColumnValue(String deviceid, String storid) {
        try {
            UpdateBuilder<Life_Devices, Integer> life_devicesIntegerUpdateBuilder = userDao.updateBuilder();
            life_devicesIntegerUpdateBuilder.updateColumnValue("storid", storid).where().eq("deviceid", deviceid);
            life_devicesIntegerUpdateBuilder.update();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
