package demo.union.e.qq.com.tv.base;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 *
 */
public class MyApplication extends Application {

    private static Application sInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        getokhttp();
        Stetho.initializeWithDefaults(this);
        CrashReport.initCrashReport(getApplicationContext(), "ff26fd2fe5", false);
    }


    public static Application getInstance() {
        return sInstance;
    }
    public void getokhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

}
