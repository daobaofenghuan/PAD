package demo.union.e.qq.com.tv.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.siberiadante.customdialoglib.EditDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ielse.view.SwitchView;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.adapter.Settting_Device_Adapter;
import demo.union.e.qq.com.tv.base.MyApplication;
import demo.union.e.qq.com.tv.beans.Details;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.db.bean.Life_Storey;
import demo.union.e.qq.com.tv.db.bean.Z_Login_Life_DeviceDao;
import demo.union.e.qq.com.tv.db.bean.Z_Login_Life_StoreyDao;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;
import demo.union.e.qq.com.tv.utils.SpfUtils;

public class Setting_DevicesCJ_Activity extends AppCompatActivity {

    @BindView(R.id.tv_value1)
    TextView tvValue1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.listsetting)
    RecyclerView mRecyclerView_LCXZ;
    @BindView(R.id.bt_tjlc)
    Button btTjlc;
    @BindView(R.id.bt_sclc)
    Button btSclc;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    Z_Login_Life_DeviceDao z_login_life_deviceDao;
    @BindView(R.id.textc)
    TextView textc;
    @BindView(R.id.SwitchView)
    ch.ielse.view.SwitchView SwitchView;
    @BindView(R.id.textc2)
    TextView textc2;
    @BindView(R.id.SwitchView2)
    ch.ielse.view.SwitchView SwitchView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__devices_);
        ButterKnife.bind(this);
        z_login_life_deviceDao = new Z_Login_Life_DeviceDao(MyApplication.getInstance());
        initSettting_Device_Adapter();
        boolean cj = (boolean) SpfUtils.get(Setting_DevicesCJ_Activity.this, "cj", false);
        if (cj) {
            SwitchView.setOpened(true);
        } else {
            SwitchView.setOpened(false);
        }
        boolean erweima = (boolean) SpfUtils.get(Setting_DevicesCJ_Activity.this, "erweima", false);
        if (erweima) {
            SwitchView2.setOpened(true);
        } else {
            SwitchView2.setOpened(false);
        }
        SwitchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isOpened = SwitchView.isOpened();
                if (isOpened) {

                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.CJShow);
                    EventBus.getDefault().post(tianQi);
                } else {

                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.CJUnShow);
                    EventBus.getDefault().post(tianQi);
                }
            }

        });

        SwitchView2.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                boolean isOpened = SwitchView2.isOpened();
                if (isOpened) {

                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.Show);
                    EventBus.getDefault().post(tianQi);
                } else {

                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.UnShow);
                    EventBus.getDefault().post(tianQi);
                }
            }
        });


    }


    List<Details.DataBean.GroupDataListBean> groupDataListBean;
    Details.DataBean.GroupDataListBean yy;
    List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> deviceItemPos;
    Z_Login_Life_StoreyDao z_login_lifeStoreyDao;
    LinearLayoutManager HorizontalLayout;
    Settting_Device_Adapter settting_device_adapter;

    /**
     * 初始化设备列表服务器
     */
    void initSettting_Device_Adapter() {
        groupDataListBean = API.GroupDataListBean;
        if (groupDataListBean != null && groupDataListBean.size() > 0) {
            yy = groupDataListBean.get(API.Groupposition);
            deviceItemPos = yy.getDeviceItemPos();
        }

        z_login_lifeStoreyDao = new Z_Login_Life_StoreyDao(this);
        List<Life_Storey> quell = z_login_lifeStoreyDao.quell();
        if (quell.size() == 0) {
            z_login_lifeStoreyDao.addUser("1");
        }
        HorizontalLayout = new LinearLayoutManager(Setting_DevicesCJ_Activity.this, LinearLayoutManager.VERTICAL, false);
        settting_device_adapter = new Settting_Device_Adapter(z_login_lifeStoreyDao, z_login_life_deviceDao);
        mRecyclerView_LCXZ.setLayoutManager(HorizontalLayout);
        mRecyclerView_LCXZ.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerView_LCXZ.setAdapter(settting_device_adapter);
        if (deviceItemPos != null && groupDataListBean.size() > 0) {
            settting_device_adapter.addll(deviceItemPos, Setting_DevicesCJ_Activity.this);
            int groupId = yy.getGroupId();
            addthing(deviceItemPos, "" + groupId);
        }


    }

    public void addthing(List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> deviceItemPos, String groupId) {
        for (int i = 0; i < deviceItemPos.size(); i++) {

            String deviceId = deviceItemPos.get(i).getDeviceId();
            boolean shifoucunzai = z_login_life_deviceDao.shifoucunzai(deviceId);
            if (shifoucunzai) {

            } else {
                z_login_life_deviceDao.addDevices(deviceItemPos.get(i).getDeviceName(), deviceItemPos.get(i).getDeviceId(), "1", groupId);
            }
        }
    }

    @OnClick({R.id.bt_tjlc, R.id.bt_sclc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_tjlc:
                showEditDialog();
                break;
            case R.id.bt_sclc:
                List<Life_Storey> quell = z_login_lifeStoreyDao.quell();
                int size = quell.size();
                if (size > 1) {
                    z_login_lifeStoreyDao.delete(quell.get(size - 1).getAccount());
                    settting_device_adapter.show();
                    settting_device_adapter.notifyDataSetChanged();
                    Toast.makeText(Setting_DevicesCJ_Activity.this, "删除楼层成功！", Toast.LENGTH_SHORT).show();
                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.EventRefresh_StoreyList);
                    EventBus.getDefault().post(tianQi);
                } else {
                    Toast.makeText(Setting_DevicesCJ_Activity.this, "不能删除默认楼层！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    /**
     * EditDialog
     */
    private void showEditDialog() {
        final EditDialog dialog = new EditDialog(Setting_DevicesCJ_Activity.this).builder();
        dialog.setTitle("请输入楼层名");
        dialog.setCancelable(false);
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg = dialog.getMsg();
//                Toast.makeText(getActivity(), "输入内容为：" + msg, Toast.LENGTH_LONG).show();
                boolean shifoucunzai = z_login_lifeStoreyDao.shifoucunzai(msg);
                if (shifoucunzai) {
                    Toast.makeText(Setting_DevicesCJ_Activity.this, "楼层名已存在不能重复添加", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Life_Storey> quell = z_login_lifeStoreyDao.quell();
                if (quell.size() > 5) {
                    Toast.makeText(Setting_DevicesCJ_Activity.this, "楼层数不能超过5个", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    z_login_lifeStoreyDao.addUser(msg);
                    Toast.makeText(Setting_DevicesCJ_Activity.this, "添加楼层成功", Toast.LENGTH_SHORT).show();
                    settting_device_adapter.show();
                    settting_device_adapter.notifyDataSetChanged();
                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.EventRefresh_StoreyList);
                    EventBus.getDefault().post(tianQi);
                }

                dialog.dismiss();

            }
        });
        dialog.show();
    }


}
