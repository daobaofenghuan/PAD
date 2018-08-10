package demo.union.e.qq.com.tv.devices_frgment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.owen.tvrecyclerview.widget.SimpleOnItemListener;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.adapter.RecyclerViewListAdapter;
import demo.union.e.qq.com.tv.beans.Details;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.focus.FocusBorder;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;

/**
 * 设备列表显示
 */
public class DevicesList_Fragment extends Fragment {

    View view;
    Unbinder unbinder;
    protected FocusBorder mFocusBorder;
    @BindView(R.id.list)
    TvRecyclerView mRecyclerView_h;
    RecyclerViewListAdapter mAdapter;
    List<Details.DataBean.GroupDataListBean> groupDataListBean;
    List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> deviceItemPos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_devcieslist, null);
            ButterKnife.bind(this, view);
            EventBus.getDefault().register(this);
            groupDataListBean = API.GroupDataListBean;
            if (groupDataListBean != null && groupDataListBean.size() > 0) {
                Details.DataBean.GroupDataListBean yy = groupDataListBean.get(API.Groupposition);
                deviceItemPos = yy.getDeviceItemPos();
            }


            initview();
        }
        inittt();
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }


    void initview() {


        mRecyclerView_h.setSpacingWithMargins(0, 10);
        mAdapter = new RecyclerViewListAdapter();

        mRecyclerView_h.setAdapter(mAdapter);
        mRecyclerView_h.setSelectedItemAtCentered(true);
        mRecyclerView_h.setOnItemListener(new SimpleOnItemListener() {

            @Override
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {

            }

            @Override
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {

                Toast.makeText(getActivity(), "onItemClick::" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void inittt() {
        if (mAdapter != null&&deviceItemPos!=null) {
            mAdapter.addll(deviceItemPos);
            mAdapter.notifyDataSetChanged();
        }


    }

    public void onEventMainThread(MyEvent event) {

        if (!TextUtils.isEmpty(event.getSc())) {
            if (event.getSc().equals(Content.EventRefresh_GroupList)) {
                int position = Integer.parseInt(event.getContent());
                Details.DataBean.GroupDataListBean groupDataListBean = API.GroupDataListBean.get(position);
                List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> deviceItemPos = groupDataListBean.getDeviceItemPos();
                mAdapter.clear();
                mAdapter.addll(deviceItemPos);
            } else if (event.getSc().equals(Content.EventRefresh_DeviceList)) {
                Details.DataBean.GroupDataListBean groupDataListBean = API.GroupDataListBean.get(API.Groupposition);
                List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> deviceItemPos = groupDataListBean.getDeviceItemPos();
                mAdapter.clear();
                mAdapter.addll(deviceItemPos);
            }
        }
    }
}
