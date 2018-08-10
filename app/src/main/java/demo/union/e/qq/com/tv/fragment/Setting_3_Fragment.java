package demo.union.e.qq.com.tv.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.siberiadante.customdialoglib.EditDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.Q.QRCode;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.adapter.RecyclerViewFZXZAdapter;
import demo.union.e.qq.com.tv.adapter.RecyclerView_Bfpl_Adapter;
import demo.union.e.qq.com.tv.adapter.Settting_Device_Adapter;
import demo.union.e.qq.com.tv.base.MyApplication;
import demo.union.e.qq.com.tv.beans.Details;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.db.bean.Life_Storey;
import demo.union.e.qq.com.tv.db.bean.Z_Login_Life_DeviceDao;
import demo.union.e.qq.com.tv.db.bean.Z_Login_Life_StoreyDao;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;
import demo.union.e.qq.com.tv.ui.SpaceItemDecoration;
import demo.union.e.qq.com.tv.utils.SpfUtils;

/**
 * 设置Fragment
 */
public class Setting_3_Fragment extends Fragment {

    View view;
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
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.erweima)
    ImageView erweima;
    @BindView(R.id.textView5)
    TextView textView5;
    Unbinder unbinder;
    @BindView(R.id.list_v7)
    RecyclerView recyclerView_QXZFZ;
    @BindView(R.id.list_v8)
    RecyclerView listV8_BFPL;

    /**
     * 数据类
     */
    List<Details.DataBean.GroupDataListBean> groupDataListBean;
    List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> deviceItemPos;
    List<String> st = new ArrayList<>();


    /**
     * 适配器；类
     */
    Settting_Device_Adapter settting_device_adapter;
    Z_Login_Life_StoreyDao z_login_lifeStoreyDao;
    LinearLayoutManager RecyclerViewLayoutManager;
    RecyclerViewFZXZAdapter recyclerViewFZXZAdapter;
    LinearLayoutManager HorizontalLayout;
    RecyclerView_Bfpl_Adapter recyclerView_bfpl_adapter;
    Z_Login_Life_DeviceDao z_login_life_deviceDao;

    @SuppressLint("ResourceType")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_setting, null);
            ButterKnife.bind(this, view);
            z_login_life_deviceDao = new Z_Login_Life_DeviceDao(MyApplication.getInstance());
            erweima.setImageBitmap(QRCode.createQRCode(API.bind + getimei(), 500));
            groupDataListBean = API.GroupDataListBean;
            st.add("无");
            st.add("5 min");
            st.add("10 min");
            st.add("15 min");
            //初始化数据
            initdata();
            //初始化设置-播放频率适配器
            showBfpl_Adapter();
            //初始化设置-设备列表适配器
            initSettting_Device_Adapter();
            //初始化设置-请选择分组适配器
            initRecyclerViewFZXZAdapter();
            int gettime = gettime();
            switch (gettime) {
                case 300:
                    recyclerView_bfpl_adapter.setThisPosition(0);
                    break;
                case 5000:
                    recyclerView_bfpl_adapter.setThisPosition(1);
                    break;
                case 10000:
                    recyclerView_bfpl_adapter.setThisPosition(2);
                    break;
                case 15000:
                    recyclerView_bfpl_adapter.setThisPosition(3);
                    break;

                default:
                    break;
            }


        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initdata() {

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }

    Details.DataBean.GroupDataListBean yy;

    /**
     * 初始化设备列表服务器
     */
    void initSettting_Device_Adapter() {
        groupDataListBean = API.GroupDataListBean;
        if (groupDataListBean != null&&groupDataListBean.size()> 0) {
            yy = groupDataListBean.get(API.Groupposition);
            deviceItemPos = yy.getDeviceItemPos();
        }

        z_login_lifeStoreyDao = new Z_Login_Life_StoreyDao(getActivity());
        List<Life_Storey> quell = z_login_lifeStoreyDao.quell();
        if (quell.size() == 0) {
            z_login_lifeStoreyDao.addUser("1");
        }
        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        settting_device_adapter = new Settting_Device_Adapter(z_login_lifeStoreyDao, z_login_life_deviceDao);
        mRecyclerView_LCXZ.setLayoutManager(HorizontalLayout);
        mRecyclerView_LCXZ.addItemDecoration(new SpaceItemDecoration(10));
        mRecyclerView_LCXZ.setAdapter(settting_device_adapter);
        if (deviceItemPos != null&&groupDataListBean.size()> 0) {
            settting_device_adapter.addll(deviceItemPos, getActivity());
            int groupId = yy.getGroupId();
            addthing(deviceItemPos, "" + groupId);
        }


    }


    /**
     * 初始化请选择分组适配器
     */

    public void initRecyclerViewFZXZAdapter() {
        RecyclerViewLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView_QXZFZ.setLayoutManager(RecyclerViewLayoutManager);


        recyclerViewFZXZAdapter = new RecyclerViewFZXZAdapter();

        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
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

                //刷新所有设备列表

                MyEvent event = new MyEvent();
                event.setSc(Content.EventRefresh_GroupList);
                event.setContent("" + position);
                EventBus.getDefault().post(event);
                Refresh(position);
            }
        });

    }


    /**
     * 初始化播放频率适配器
     */

    public void showBfpl_Adapter() {


        recyclerView_bfpl_adapter = new RecyclerView_Bfpl_Adapter();

        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        listV8_BFPL.setLayoutManager(HorizontalLayout);
        listV8_BFPL.addItemDecoration(new SpaceItemDecoration(10));
        listV8_BFPL.setAdapter(recyclerView_bfpl_adapter);
        recyclerView_bfpl_adapter.addll(st);
        recyclerView_bfpl_adapter.setOnItemClickListener(new RecyclerView_Bfpl_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                recyclerView_bfpl_adapter.setThisPosition(position);
                //嫑忘记刷新适配器
                recyclerView_bfpl_adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "设置播放频率成功！", Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        SpfUtils.put(getActivity(), "jiange", 300);

                        break;
                    case 1:
                        settime(5);
                        break;
                    case 2:
                        settime(10);
                        break;
                    case 3:
                        settime(15);
                        break;

                    default:
                        break;
                }

            }
        });

    }


    public void settime(int i) {
        SpfUtils.put(getActivity(), "jiange", i * 1000);

    }

    public int gettime() {
        int a = (int) SpfUtils.get(getActivity(), "jiange", 1000);
        return a;
    }

    /**
     * EditDialog
     */
    private void showEditDialog() {
        final EditDialog dialog = new EditDialog(getActivity()).builder();
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
                    Toast.makeText(getActivity(), "楼层名已存在不能重复添加", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Life_Storey> quell = z_login_lifeStoreyDao.quell();
                if (quell.size() > 5) {
                    Toast.makeText(getActivity(), "楼层数不能超过5个", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    z_login_lifeStoreyDao.addUser(msg);
                    Toast.makeText(getActivity(), "添加楼层成功", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(), "删除楼层成功！", Toast.LENGTH_SHORT).show();
                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.EventRefresh_StoreyList);
                    EventBus.getDefault().post(tianQi);
                } else {
                    Toast.makeText(getActivity(), "不能删除默认楼层！", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void Refresh(int position) {
        Details.DataBean.GroupDataListBean groupDataListBean = API.GroupDataListBean.get(position);
        int groupId = groupDataListBean.getGroupId();
        List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> deviceItemPos = groupDataListBean.getDeviceItemPos();
        settting_device_adapter.clear();
        settting_device_adapter.addll(deviceItemPos, getActivity());
        addthing(deviceItemPos, "" + groupId);
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

}
