package demo.union.e.qq.com.tv.http;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import demo.union.e.qq.com.tv.base.MyApplication;
import okhttp3.Call;

/**
 * Created by 他的猫 on 2017/5/10.
 */

public class Okhttp {


    public static void post(String url, Map<String, String> paramters,
                            final Objectcallback callback) {
        String getimei = getimei();
        try {
            OkHttpUtils
                    .post()
                    .url(url)
                    .addHeader("Ticket", getimei)
                    .params(paramters)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("888", "失败！" + e.toString());
                            callback.onFalia(id, e.toString());


                        }

                        @Override
                        public void onResponse(String responseString, int id) {
                            if (callback != null) {
                                callback.onsuccess(responseString);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void get(String url, Map<String, String> paramters,
                           final Objectcallback callback) {
        String getimei = getimei();
        try {
            OkHttpUtils
                    .get()
                    .url(url)
                    .params(paramters)
                    .addHeader("Ticket", getimei)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            callback.onFalia(id, e.toString());
                        }

                        @Override
                        public void onResponse(String responseString, int id) {
                            if (callback != null) {
                                callback.onsuccess(responseString);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public interface Objectcallback {
        void onsuccess(String st);

        void onFalia(int code, String errst);

    }

    public static String getimei() {
        String id;
        //android.telephony.TelephonyManager
        TelephonyManager mTelephony = (TelephonyManager) MyApplication.getInstance().
                getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephony.getDeviceId() != null) {
            id = mTelephony.getDeviceId();
        } else {
            //android.provider.Settings;
            id = Settings.Secure.getString(MyApplication.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return id;
    }
}
