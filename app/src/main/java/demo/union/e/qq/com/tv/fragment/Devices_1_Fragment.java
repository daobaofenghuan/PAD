package demo.union.e.qq.com.tv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.devices_frgment.DevicesCharts_Fragment;
import demo.union.e.qq.com.tv.devices_frgment.DevicesList_Fragment;

/**
 * 设备Fragment
 */
public class Devices_1_Fragment extends Fragment {

    View view;
    Unbinder unbinder;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.bt_lbxs)
    Button btLbxs;
    @BindView(R.id.bt_tbxs)
    Button btTbxs;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.main_frame_layoutsb)
    FrameLayout mainFrameLayoutsb;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_devices, null);
            ButterKnife.bind(this, view);
            EventBus.getDefault().register(this);
            btLbxs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btLbxs.setTextColor(getResources().getColor(R.color.holo_blue_bright));
                    im2.setVisibility(View.VISIBLE);
                    initFragment2();
                    btTbxs.setTextColor(getResources().getColor(R.color.white));
                    im1.setVisibility(View.GONE);
                }
            });
            btTbxs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btTbxs.setTextColor(getResources().getColor(R.color.holo_blue_bright));
                    im1.setVisibility(View.VISIBLE);
                    initFragment1();
                    btLbxs.setTextColor(getResources().getColor(R.color.white));
                    im2.setVisibility(View.GONE);
                }
            });

            initFragment1();
            btTbxs.setTextColor(getResources().getColor(R.color.holo_blue_bright));
            im1.setVisibility(View.VISIBLE);

            btLbxs.setTextColor(getResources().getColor(R.color.white));
            im2.setVisibility(View.GONE);
        }
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

    @OnClick({R.id.bt_tbxs, R.id.bt_lbxs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_tbxs:

                initFragment1();
                break;
            case R.id.bt_lbxs:

                initFragment2();
                break;
        }
    }

    DevicesCharts_Fragment f1;

    //显示第一个fragment
    private void initFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (f1 == null) {
            f1 = new DevicesCharts_Fragment();
            transaction.add(R.id.main_frame_layoutsb, f1);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f1);
        //提交事务
        transaction.commit();
    }


    DevicesList_Fragment f2;

    private void initFragment2() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (f2 == null) {
            f2 = new DevicesList_Fragment();
            transaction.add(R.id.main_frame_layoutsb, f2);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f2);
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


    }

    /**
     * 处理数据分发
     */
    public void onEventMainThread(MyEvent event) {

        if (!TextUtils.isEmpty(event.getSc())) {
            if (event.getSc().equals("setTianQi")) {
                String mac = event.getMAC();
                textView.setText("MAC:"+mac);
                String weather = event.getWeather();

                String area = event.getAREA();
                textView2.setText(area+","+weather);
                String time = event.getTime();
                textView3.setText(time);

            }
        }
    }


}

