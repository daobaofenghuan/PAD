package demo.union.e.qq.com.tv.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.adapter.RecyclerViewAdapter;
import demo.union.e.qq.com.tv.beans.Details;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.fragment.Devices_1_Fragment;
import demo.union.e.qq.com.tv.fragment.Scene_2_Fragment;
import demo.union.e.qq.com.tv.fragment.Setting_4_Fragment;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;
import demo.union.e.qq.com.tv.http.Okhttp;
import demo.union.e.qq.com.tv.utils.JSONUtils;
import demo.union.e.qq.com.tv.utils.SpfUtils;

/**
 * 主界面
 */
public class MainActivity extends FragmentActivity {

    private static final String TAG = "8888";
    @BindView(R.id.list_v7)
    RecyclerView recyclerView;


    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.bt_devices)
    ImageButton btDevices;
    @BindView(R.id.bt_scene)
    ImageButton btScene;
    @BindView(R.id.bt_set)
    ImageButton btSet;
    @BindView(R.id.erweima)
    ImageView erweima;
    @BindView(R.id.main_frame_layout)
    FrameLayout mainFrameLayout;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    RecyclerViewAdapter RecyclerViewHorizontalAdapter;
    LinearLayoutManager HorizontalLayout;


    //    List<Details.DataBean> data = new ArrayList<>();
    List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> data;
    @BindView(R.id.tverweima)
    TextView tverweima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e(TAG, "getimei:" + API.bind + getimei());
        EventBus.getDefault().register(this);
        btDevices.setSelected(true);
        boolean erweima = (boolean) SpfUtils.get(MainActivity.this, "erweima", false);
        if (erweima){
            shows();
        }else {
            unshow();
        }
        initFragment1();
        queryDeviceList();
        // 测试

        btDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initFragment1();
                btDevices.setSelected(true);
                btScene.setSelected(false);
                btSet.setSelected(false);
            }

        });
        btScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initFragment2();
                btScene.setSelected(true);
                btDevices.setSelected(false);
                btSet.setSelected(false);
            }


        });
        btSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                initFragment3();
                btSet.setSelected(true);
                btDevices.setSelected(false);
                btScene.setSelected(false);

            }

        });


        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(RecyclerViewLayoutManager);


        RecyclerViewHorizontalAdapter = new RecyclerViewAdapter();

        HorizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);
        RecyclerViewHorizontalAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                RecyclerViewHorizontalAdapter.setThisPosition(position);
                //嫑忘记刷新适配器
                RecyclerViewHorizontalAdapter.notifyDataSetChanged();
                String deviceId = RecyclerViewHorizontalAdapter.getList().get(position).getDeviceId();
                Log.e("3333", "原始" + deviceId);

                MyEvent event = new MyEvent();
                event.setSc("queryDetailByDeviceId");
                event.setContent(deviceId);
                EventBus.getDefault().post(event);
            }
        });


    }


    Devices_1_Fragment f1;

    //显示第一个fragment
    private void initFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (f1 == null) {
            f1 = new Devices_1_Fragment();
            transaction.add(R.id.main_frame_layout, f1);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f1);
        //提交事务
        transaction.commit();
    }


    Scene_2_Fragment f2;

    private void initFragment2() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (f2 == null) {
            f2 = new Scene_2_Fragment();
            transaction.add(R.id.main_frame_layout, f2);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f2);
        //提交事务
        transaction.commit();
    }

    Setting_4_Fragment f3;

    private void initFragment3() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (f3 == null) {
            f3 = new Setting_4_Fragment();
            transaction.add(R.id.main_frame_layout, f3);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f3);
        //提交事务
        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            transaction.hide(f1);
        }
        if (f2 != null) {
            transaction.hide(f2);
        }
        if (f3 != null) {
            transaction.hide(f3);
        }

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    /*****************************
     * 网络请求
     *
     */
    String Qrcode;
    /**
     * 获取设备列表
     */
    public void queryDeviceList() {
        API.queryDeviceList(new Okhttp.Objectcallback() {
            @Override
            public void onsuccess(String st) {
                Log.e("711", "获取设备列表" + st);
                Details details = JSONUtils.parseJSON(st, Details.class);
                int code = details.getCode();
                switch (code) {
                    case 200:
                        String msg = details.getMsg();
                        //david delete 20180625
                        //Toast.makeText(MainActivity.this, "获取组数据成功！", Toast.LENGTH_SHORT).show();

                        //获取到datas字段
                        Details.DataBean datas = details.getData();

                        //获取到组列表
                        List<Details.DataBean.GroupDataListBean> groupDataList = datas.getGroupDataList();

                        int position = (int) SpfUtils.get(MainActivity.this, "position", 0);
                        API.GroupDataListBean = details.getData().getGroupDataList();
                        Log.e("db_test","===========================================================");
                        Log.e("db_test",details.getData().getGroupDataList().get(0).getVideoUrl());
                        Log.e("db_test","===========================================================");
                        if (groupDataList != null && groupDataList.size() > 0) {
                            if (groupDataList.get(position)==null) {
                                data = details.getData().getGroupDataList().get(0).getDeviceItemPos();
                                API.groupName = details.getData().getGroupDataList().get(0).getGroupName();
                                API.icon = details.getData().getGroupDataList().get(0).getIcon();
                                API.GroupID = "" + details.getData().getGroupDataList().get(0).getGroupId();
                                API.videoUrl = details.getData().getGroupDataList().get(0).getVideoUrl();

                                API.videoCover = details.getData().getGroupDataList().get(0).getVideoCover();
                                API.memo = details.getData().getGroupDataList().get(0).getMemo();
                                  Qrcode = details.getData().getGroupDataList().get(0).getQrcode();
                            } else {
                                data = details.getData().getGroupDataList().get(position).getDeviceItemPos();
                                API.groupName = details.getData().getGroupDataList().get(position).getGroupName();
                                API.icon = details.getData().getGroupDataList().get(position).getIcon();
                                API.GroupID = "" + details.getData().getGroupDataList().get(position).getGroupId();
                                API.videoUrl = details.getData().getGroupDataList().get(position).getVideoUrl();
                                API.videoCover = details.getData().getGroupDataList().get(position).getVideoCover();
                                API.memo = details.getData().getGroupDataList().get(position).getMemo();
                                Qrcode = details.getData().getGroupDataList().get(position).getQrcode();
                            }


                            Glide.with(MainActivity.this).load(Qrcode).into(erweima);
                            RecyclerViewHorizontalAdapter.addll(data);
                        }

                        setting();

                        if (data != null && data.size() > 0) {
                            //第一台默认的设备ID
                            String DeviceId = data.get(0).getDeviceId();
                            API.currentDevice = DeviceId;
                            if (!TextUtils.isEmpty(DeviceId)) {
                                MyEvent event = new MyEvent();
                                event.setSc("queryDetailByDeviceId");
                                event.setContent(DeviceId);
                                EventBus.getDefault().post(event);

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        i++;
                                        if (RecyclerViewHorizontalAdapter.getList().size() < i) {
                                            if (API.APIBoolen) {
                                                Log.e(TAG, "xx" + i);
                                                xuanze(i);
                                            }
                                        } else {

                                            i = 0;
                                            Log.e(TAG, "xx" + i);
                                        }

                                        mHandler.postDelayed(r, 100);//延时100毫秒
                                    }
                                }, 10000);
                            } else {
                                Toast.makeText(MainActivity.this, "设备ID为空！", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(MainActivity.this, "组数据列表为空！", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case 601:
                        Toast.makeText(MainActivity.this, "暂未绑定设备，请扫描二维码绑定设备！", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

            }

            @Override
            public void onFalia(int code, String errst) {
                Log.e("8881", errst.toString());
                Toast.makeText(MainActivity.this, "获取设备列表网络错误" + errst.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//*************************************


    public String getimei() {
        String id;
        //android.telephony.TelephonyManager
        TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephony.getDeviceId() != null) {
            id = mTelephony.getDeviceId();
        } else {
            //android.provider.Settings;
            id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return id;
    }


    public void setting() {
        textView5.setText(API.groupName);
        Glide.with(MainActivity.this).load(API.icon).into(imageView);


    }

    List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> deviceItemPos;

    /**
     * 处理数据分发
     */
    public void onEventMainThread(MyEvent event) {

        if (!TextUtils.isEmpty(event.getSc())) {

            if (event.getSc().equals(Content.EventRefresh_GroupList)) {
                int position = Integer.parseInt(event.getContent());
                Details.DataBean.GroupDataListBean groupDataListBean = API.GroupDataListBean.get(position);

                deviceItemPos = groupDataListBean.getDeviceItemPos();
                RecyclerViewHorizontalAdapter.clear();
                RecyclerViewHorizontalAdapter.addll(deviceItemPos);
                RecyclerViewHorizontalAdapter.notifyDataSetChanged();
                API.groupName = groupDataListBean.getGroupName();
                API.icon = groupDataListBean.getIcon();
                API.videoUrl = groupDataListBean.getVideoUrl();
                setting();
            } else if (event.getSc().equals(Content.EventRefresh_DeviceList)) {
                Details.DataBean.GroupDataListBean groupDataListBean = API.GroupDataListBean.get(API.Groupposition);
                deviceItemPos = groupDataListBean.getDeviceItemPos();
                RecyclerViewHorizontalAdapter.clear();
                RecyclerViewHorizontalAdapter.addll(deviceItemPos);
                RecyclerViewHorizontalAdapter.notifyDataSetChanged();
            } else if (event.getSc().equals(Content.Show)) {
                shows();
            } else if (event.getSc().equals(Content.UnShow)) {
                unshow();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void shows() {
        erweima.setVisibility(View.VISIBLE);
        tverweima.setVisibility(View.VISIBLE);
        SpfUtils.put(MainActivity.this,"erweima",true);
    }

    public void unshow() {
        erweima.setVisibility(View.GONE);
        tverweima.setVisibility(View.GONE);
        SpfUtils.put(MainActivity.this,"erweima",false);
    }

    int i = 0;
    Handler mHandler = new Handler();
    Runnable r = new Runnable() {

        @Override
        public void run() {
            //do something
            //每隔1s循环执行run方法
            mHandler.postDelayed(this, 10000);
            i++;
            if (RecyclerViewHorizontalAdapter.getList() != null && RecyclerViewHorizontalAdapter.getList().size() > i) {
                Log.e(TAG, "xx" + i);
                if (API.APIBoolen) {
                    xuanze(i);
                }
            } else {
                i = 0;
                xuanze(i);
                Log.e(TAG, "xx" + i);
            }


        }
    };

    public void xuanze(int position) {

        RecyclerViewHorizontalAdapter.setThisPosition(position);
        //嫑忘记刷新适配器
        RecyclerViewHorizontalAdapter.notifyDataSetChanged();
        if (RecyclerViewHorizontalAdapter.getList().get(position)!=null) {
            String deviceId = RecyclerViewHorizontalAdapter.getList().get(position).getDeviceId();
            Log.e("3333", "原始" + deviceId);

            MyEvent event = new MyEvent();
            event.setSc("queryDetailByDeviceId");
            event.setContent(deviceId);
            EventBus.getDefault().post(event);
        } else {

        }



    }

}
