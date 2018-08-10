package demo.union.e.qq.com.tv.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.Q.QRCode;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.adapter.RecyclerViewFZXZAdapter;
import demo.union.e.qq.com.tv.base.MyApplication;
import demo.union.e.qq.com.tv.beans.Details;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;
import demo.union.e.qq.com.tv.utils.SpfUtils;

/**
 * 设备选择
 */
public class Setting_SBXZS_Activity extends AppCompatActivity {


    @BindView(R.id.list)
    RecyclerView recyclerView_QXZFZ;
    @BindView(R.id.erweima)
    ImageView erweima;
    @BindView(R.id.ll_dialog)
    LinearLayout llDialog;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__sbxzs);
        ButterKnife.bind(this);
        getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        erweima.setImageBitmap(QRCode.createQRCode(API.bind + getimei(), 500));

        initRecyclerViewFZXZAdapter();
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

    /**
     * 初始化请选择分组适配器
     */
    LinearLayoutManager HorizontalLayout;
    RecyclerViewFZXZAdapter recyclerViewFZXZAdapter;
    List<Details.DataBean.GroupDataListBean> groupDataListBean;

    public void initRecyclerViewFZXZAdapter() {
        groupDataListBean = API.GroupDataListBean;


        recyclerViewFZXZAdapter = new RecyclerViewFZXZAdapter();

        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_QXZFZ.setLayoutManager(HorizontalLayout);
        recyclerView_QXZFZ.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView_QXZFZ.setAdapter(recyclerViewFZXZAdapter);
        recyclerViewFZXZAdapter.addll(groupDataListBean);
        recyclerViewFZXZAdapter.setOnItemClickListener(new RecyclerViewFZXZAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                recyclerViewFZXZAdapter.setThisPosition(position);
                //嫑忘记刷新适配器
                recyclerViewFZXZAdapter.notifyDataSetChanged();
                API.Groupposition = position;
                int groupId = groupDataListBean.get(position).getGroupId();
                API.GroupID = "" + groupId;
                SpfUtils.put(Setting_SBXZS_Activity.this, "position", position);

                //刷新所有设备列表

                MyEvent event = new MyEvent();
                event.setSc(Content.EventRefresh_GroupList);
                event.setContent("" + position);
                EventBus.getDefault().post(event);
                Refresh(position);
                finish();
            }
        });

    }

    public void Refresh(int position) {
        Details.DataBean.GroupDataListBean groupDataListBean = API.GroupDataListBean.get(position);
        int groupId = groupDataListBean.getGroupId();
        List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> deviceItemPos = groupDataListBean.getDeviceItemPos();

    }
}
