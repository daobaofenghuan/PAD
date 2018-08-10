package demo.union.e.qq.com.tv.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadingDialogListener;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.base.MyApplication;
import demo.union.e.qq.com.tv.beans.UpData;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Okhttp;
import demo.union.e.qq.com.tv.ui.Setting_DevicesCJ_Activity;
import demo.union.e.qq.com.tv.ui.Setting_GG_Activity;
import demo.union.e.qq.com.tv.ui.Setting_SBXS_Activity;
import demo.union.e.qq.com.tv.ui.Setting_SBXZS_Activity;
import demo.union.e.qq.com.tv.utils.JSONUtils;
import demo.union.e.qq.com.tv.utils.Up_Activity;
import demo.union.e.qq.com.tv.view.BaseDialog;

/**
 * 设置Fragment
 */
public class Setting_4_Fragment extends Up_Activity {

    View view;
    Unbinder unbinder;


    @SuppressLint("ResourceType")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_setting4, null);
            ButterKnife.bind(this, view);


        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private DownloadBuilder builder;

    @OnClick({R.id.sbxs, R.id.ggsz, R.id.cjsz, R.id.sbxz, R.id.rjsj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sbxs:
                startActivity(new Intent(getActivity(), Setting_SBXS_Activity.class));

                break;
            case R.id.ggsz:
                startActivity(new Intent(getActivity(), Setting_GG_Activity.class));

                break;
            case R.id.cjsz:
                startActivity(new Intent(getActivity(), Setting_DevicesCJ_Activity.class));

                break;
            case R.id.sbxz:
                startActivity(new Intent(getActivity(), Setting_SBXZS_Activity.class));
                break;
            case R.id.rjsj:
                API.obtainApk(new Okhttp.Objectcallback() {
                    @Override
                    public void onsuccess(String st) {
                        Log.e("result", "st" + st);
                        UpData upData = JSONUtils.parseJSON(st, UpData.class);
                        int code = upData.getCode();
                        if (code == 200) {
                            int versionCode1 = getVersionCode(getActivity());
                            String versionCode = upData.getData().getVersionCode();
                            int fuwuqi = Integer.parseInt(versionCode);
                            if (fuwuqi > versionCode1) {

                                String apkUrl = upData.getData().getApkUrl();
                                BaseDialog baseDialog = new BaseDialog(getActivity(), R.style.BaseDialog, R.layout.custom_dialog_two_layout);
                                TextView textView = baseDialog.findViewById(R.id.tv_msg);
                                textView.setText("当前版本："+versionCode1+".0  服务器版本："+fuwuqi+".0");
                                Button button = baseDialog.findViewById(R.id.versionchecklib_version_dialog_commit);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        baseDialog.dismiss();
                                       Toast.makeText(getActivity(), "文件在后台静默升级！" +
                                              "", Toast.LENGTH_SHORT).show();
                                        up(apkUrl);

                                    }
                                });
                                baseDialog.setCanceledOnTouchOutside(true);
                                baseDialog.show();




                            } else {
                                Toast.makeText(getActivity(), "当前已是最新版本!", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(getActivity(), "服务器异常", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFalia(int code, String errst) {
                        Toast.makeText(getActivity(), "版本升级网络错误！", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
    }






    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
