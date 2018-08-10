package demo.union.e.qq.com.tv.utils;

import android.app.Activity;
import android.app.ActivityManager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.lijunhuayc.downloader.downloader.DownloadProgressListener;
import com.lijunhuayc.downloader.downloader.FileDownloader;
import com.lijunhuayc.downloader.downloader.WolfDownloader;

import java.io.File;
import java.util.List;

import demo.union.e.qq.com.tv.base.MyApplication;

/**
 * Created by acer on 2018/6/4.
 */

public class Up_Activity extends Fragment {
    private static final String TAG = "888";

    public void up(String path) {
        WolfDownloader wolfDownloader = new WolfDownloader(getActivity())
                .setThreadNum(3)
                .setDownloadUrl(path)
                .setSaveDir(Environment.getExternalStorageDirectory())
                .addDownloadListener(new DownloadProgressListener() {
                    @Override
                    public void onDownloadTotalSize(int totalSize) {
//                      progressBar.setMax(totalSize);//设置进度条的最大刻度为文件的长度
                    }

                    @Override
                    public void updateDownloadProgress(int size, float percent, float speed) {
//                      StringBuilder sBuilder = new StringBuilder();
//                      sBuilder.append("  ").append(FileDownloader.formatSpeed(speed));
//                      sBuilder.append("  ").append(String.valueOf(percent + "%"));
//                      sBuilder.append("  ").append(FileDownloader.formatSize(size)).append("/")
//                              .append(FileDownloader.formatSize(progressBar.getMax()));
//                      resultView.setText(sBuilder.toString());
//                      progressBar.setProgress(size);
//                        Toast.makeText(getActivity(), "已经下载" + String.valueOf(percent + "%"), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onDownloadSuccess(String apkPath) {
                        Toast.makeText(getActivity(), "下载成功", Toast.LENGTH_SHORT).show();
                        installApk(new File(apkPath));
                    }

                    @Override
                    public void onDownloadFailed() {
                        Toast.makeText(getActivity(), "下载失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPauseDownload() {

                    }

                    @Override
                    public void onStopDownload() {

                    }
                });
        wolfDownloader.startDownload();
    }

    ;


    /**
     * 安装 apk 文件
     *
     * @param apkFile
     */
    public static void installApk(File apkFile) {
       /* Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        installApkIntent.setDataAndType(Uri.fromFile(apkFile), MIME_TYPE_APK);

        if (sApp.getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            sApp.startActivity(installApkIntent);
        }*/
        //Toast.makeText(sApp,apkFile.getPath(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MyApplication.getInstance(), "demo.union.e.qq.com.tv.fileprovider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (MyApplication.getInstance().getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            MyApplication.getInstance().startActivity(intent);
        }
    }

    /**
     * 获取当前应用程序的包名
     *
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
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
