package demo.union.e.qq.com.tv.scene_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.adapter.RecyclerView_LC_Adapter;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.db.bean.GaiLan;
import demo.union.e.qq.com.tv.db.bean.Life_Devices;
import demo.union.e.qq.com.tv.db.bean.Life_Storey;
import demo.union.e.qq.com.tv.db.bean.Z_Login_Life_DeviceDao;
import demo.union.e.qq.com.tv.db.bean.Z_Login_Life_StoreyDao;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;
import demo.union.e.qq.com.tv.ui.SpaceItemDecoration;

/**
 * 场景概览图
 */
public class SceneOverview_Fragment extends Fragment {

    View view;

    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder1;

    Z_Login_Life_StoreyDao z_login_life_storeyDao;
    Z_Login_Life_DeviceDao zLoginLifeDeviceDao;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_sceneoverview, null);
            ButterKnife.bind(this, view);
            EventBus.getDefault().register(this);
            z_login_life_storeyDao = new Z_Login_Life_StoreyDao(getActivity());
            List<Life_Storey> quell = z_login_life_storeyDao.quell();
            if (quell.size() == 0) {
                z_login_life_storeyDao.addUser("1");
            }

            zLoginLifeDeviceDao = new Z_Login_Life_DeviceDao(getActivity());
            List<Life_Devices> quellS = zLoginLifeDeviceDao.quell();

            initview();


        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }


        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();


    }

    RecyclerView_LC_Adapter recyclerView_lc_adapter;
    LinearLayoutManager HorizontalLayout;


    void initview() {


        recyclerView_lc_adapter = new RecyclerView_LC_Adapter();

        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        HorizontalLayout.setStackFromEnd(true);//列表再底部开始展示，反转后由上面开始展示
        HorizontalLayout.setReverseLayout(true);//列表翻转

        list.setLayoutManager(HorizontalLayout);
        list.addItemDecoration(new SpaceItemDecoration(10));
        list.setAdapter(recyclerView_lc_adapter);
        question();
    }

    private void question() {
        List<Life_Storey> quell = z_login_life_storeyDao.quell();
        Log.e("888", "" + quell.size());
        for (int i = 0; i < quell.size(); i++) {
            Life_Storey life_storey = quell.get(i);
            String account = life_storey.getAccount();
            List<Life_Devices> devices = zLoginLifeDeviceDao.getID(account, API.GroupID);
//            String id = z_login_life_storeyDao.getID(life_storey.getId());
            //根据ID查楼层
            initDevicesAndStory(account, devices);
        }

        recyclerView_lc_adapter.clear();
        recyclerView_lc_adapter.addll(ccList);
    }

    List<GaiLan> ccList = new ArrayList<GaiLan>();

    void initDevicesAndStory(String Story, List<Life_Devices> devices) {
        GaiLan gaiLan = new GaiLan();
        gaiLan.setName(Story);
        gaiLan.setLife_devices(devices);
        ccList.add(gaiLan);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 处理数据分发
     */
    public void onEventMainThread(MyEvent event) {

        if (!TextUtils.isEmpty(event.getSc())) {
            if (event.getSc().equals(Content.EventRefresh_StoreyList)) {
                ccList.clear();
                question();

            } else if (event.getSc().equals(Content.EventRefresh_GroupList)) {
                ccList.clear();
                question();
            }
        }
    }
}
