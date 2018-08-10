package demo.union.e.qq.com.tv.devices_frgment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jonas.jgraph.graph.JcoolGraph;
import com.jonas.jgraph.models.Jchart;
import com.ruffian.library.RTextView;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.adapter.RecyclerView24Adapter;
import demo.union.e.qq.com.tv.base.MyApplication;
import demo.union.e.qq.com.tv.beans.Details_24;
import demo.union.e.qq.com.tv.beans.Details_ID;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Okhttp;
import demo.union.e.qq.com.tv.ui.SpaceItemDecoration;
import demo.union.e.qq.com.tv.utils.JSONUtils;

/**
 * 设备图表显示
 */
public class DevicesCharts_Fragment extends Fragment {

    View view;
    Unbinder unbinder;
    @BindView(R.id.tv_aqi)
    TextView tvAqi;
    @BindView(R.id.tv_aqivalue)
    TextView tvAqivalue;
    @BindView(R.id.tv_aqidengji)
    RTextView tvAqidengji;
    @BindView(R.id.tv_aqishiwai)
    TextView tvAqishiwai;
    @BindView(R.id.tv_pm25)
    TextView tvPm25;
    @BindView(R.id.tv_pm25value)
    TextView tvPm25value;
    @BindView(R.id.tv_pmdengji)
    RTextView tvpmDengji;
    @BindView(R.id.tv_eyht)
    TextView tvEyht;
    @BindView(R.id.tv_eyhtvalue)
    TextView tvEyhtvalue;
    @BindView(R.id.tv_eyhtdengji)
    RTextView tvEyhtdengji;
    @BindView(R.id.tv_wd)
    TextView tvWd;
    @BindView(R.id.tv_wdvalue)
    TextView tvWdvalue;
    @BindView(R.id.tv_wddengji)
    RTextView tvWddengji;
    @BindView(R.id.tv_tvoc)
    TextView tvTvoc;
    @BindView(R.id.tv_tvocvalue)
    TextView tvTvocvalue;
    @BindView(R.id.tv_tvocdengji)
    RTextView tvTvocdengji;
    @BindView(R.id.tv_sd)
    TextView tvSd;
    @BindView(R.id.tv_sdvalue)
    TextView tvSdvalue;
    @BindView(R.id.tv_sddengji)
    RTextView tvSddengji;
    @BindView(R.id.tv_jq)
    TextView tvJq;
    @BindView(R.id.tv_jqvalue)
    TextView tvJqvalue;
    @BindView(R.id.tv_jqdj)
    RTextView tvJqdj;
    @BindView(R.id.kongzhi)
    Button kongzhi;

    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.im_kg)
    ImageView imKg;
    @BindView(R.id.layout_kg)
    RelativeLayout layoutKg;
    @BindView(R.id.im_jy)
    ImageView imJy;
    @BindView(R.id.layout_jy)
    RelativeLayout layoutJy;
    @BindView(R.id.im_hy)
    ImageView imHy;
    @BindView(R.id.layout_hy)
    RelativeLayout layoutHy;
    @BindView(R.id.im_qx)
    ImageView imQx;
    @BindView(R.id.layout_qx)
    RelativeLayout layoutQx;
    @BindView(R.id.im_zn)
    ImageView imZn;
    @BindView(R.id.layout_zn)
    RelativeLayout layoutZn;
    Unbinder unbinder1;
    @BindView(R.id.quxian)
    Button quxian;
    @BindView(R.id.layout_sbkzs)
    LinearLayout layoutSbkzs;
    @BindView(R.id.layout_qxs)
    LinearLayout layoutQxs;
    @BindView(R.id.im1)
    ImageView im1;

    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.recycle)
    RecyclerView recyclerView;
    @BindView(R.id.vv)
    TextView vv;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_deviceschats, null);
            ButterKnife.bind(this, view);
            EventBus.getDefault().register(this);
            initview();
            showrecycle();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }


        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }

    JcoolGraph mLineChar;


    private void initview() {
        layoutJy.setEnabled(false);
        layoutHy.setEnabled(false);
        layoutQx.setEnabled(false);
        layoutZn.setEnabled(false);
        if (mLineChar == null) {
            mLineChar = (JcoolGraph) view.findViewById(R.id.sug_recode_line);
        }
        List<Jchart> lines = new ArrayList<>();
        for (int i = 0; i < 24; i++) {

            lines.add(new Jchart(50, "" + (i + 1), Color.parseColor("#5F77F6")));

        }


        mLineChar.setShaderAreaColors(Color.parseColor("#4B494B"), Color.TRANSPARENT);
        mLineChar.setLinePointRadio((int) mLineChar.getLineWidth());

        mLineChar.setNormalColor(Color.parseColor("#676567"));
        mLineChar.feedData(lines);
        ((LinearLayout) mLineChar.getParent()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLineChar.postInvalidate();
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public String getDevicesid() {
        return devicesid;
    }

    public void setDevicesid(String devicesid) {
        this.devicesid = devicesid;
    }

    String devicesid;

    /**
     * 处理数据分发
     */
    public void onEventMainThread(MyEvent event) {

        if (!TextUtils.isEmpty(event.getSc())) {
            if (event.getSc().equals("queryDetailByDeviceId")) {
                Log.e("711", "queryDetailByDeviceId" + event.getContent());
                devicesid = event.getContent();
                setDevicesid(devicesid);
                Log.e("设备号1", "xx" + devicesid);
                queryDetailByDeviceId(event.getContent());
                getHistoryData(event.getContent());
            }
        }
    }
    /**
     * 网络请求
     */
    /**
     * 通过ID查设备信息
     *
     * @param id
     */
    public void queryDetailByDeviceId(String id) {
        getHistoryData(id);
        API.queryDetailByDeviceId(id, new Okhttp.Objectcallback() {
            @Override
            public void onsuccess(String st) {
                Log.e("711", "通过ID查设备信息" + st);
                Details_ID details = JSONUtils.parseJSON(st, Details_ID.class);
                int code = details.getCode();
                switch (code) {
                    case 200:
                        String msg = details.getMsg();
                        //Toast.makeText(getActivity(), "获取设备信息成功！", Toast.LENGTH_SHORT).show();//david delete 20180625
                        Details_ID.DataBean data = details.getData();


                        String aqi = data.getAqi();
                        tvAqivalue.setText("" + aqi);


                        //设备名称
                        String DeviceName = data.getDeviceName();
                        //deviceTypeName
                        String DeviceTypeName = data.getDeviceTypeName();
                        //设备ID
                        String DeviceId = data.getDeviceId();

                        tvAqishiwai.setText("室外：" + data.getOuterPm());
//                        *****************
                        //pm值
                        String Pmdata1 = data.getPm().getData();
                        //pm单位
                        String PmUnit = data.getPm().getUnit();
                        //pm质量
                        String PmMass = data.getPm().getMass();

                        if (!TextUtils.isEmpty(Pmdata1)) {
                            tvPm25value.setText(Pmdata1);
                            int Pmdata1int = (int) Double.parseDouble(Pmdata1);
                            if (Pmdata1int > 0 && Pmdata1int <= 35) {
                                //优
                                tvpmDengji.setText("优");
                                tvpmDengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_you));

                                tvAqidengji.setText("优");
                                tvAqidengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_you));
                            } else if (Pmdata1int > 35 && Pmdata1int <= 75) {
                                //良
                                tvpmDengji.setText("良");
                                tvpmDengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_liang));

                                tvAqidengji.setText("良");
                                tvAqidengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_liang));
                            } else if (Pmdata1int > 75 && Pmdata1int <= 115) {
                                //轻度
                                tvpmDengji.setText("轻度");
                                tvpmDengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_qingdu));

                                tvAqidengji.setText("轻度");
                                tvAqidengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_qingdu));
                            } else if (Pmdata1int > 115 && Pmdata1int <= 150) {
                                //中度
                                tvpmDengji.setText("中度");
                                tvpmDengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_zongdu));

                                tvAqidengji.setText("中度");
                                tvAqidengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_zongdu));
                            } else if (Pmdata1int > 150 && Pmdata1int <= 250) {
                                //重度
                                tvpmDengji.setText("重度");
                                tvpmDengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_zhongdus));

                                tvAqidengji.setText("重度");
                                tvAqidengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_zhongdus));
                            } else if (Pmdata1int > 250) {
                                //严重
                                tvpmDengji.setText("严重");
                                tvpmDengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_yanzhong));

                                tvAqidengji.setText("严重");
                                tvAqidengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_yanzhong));
                            }

                        } else {
                            tvPm25value.setText("0");
                            tvpmDengji.setBackgroundColorNormal(getResources().getColor(R.color.dengji_you));
                        }


//                        *************
                        //Co2值
                        String Co2data1 = data.getCo2().getData();
                        //Co2单位
                        String Co2Unit = data.getCo2().getUnit();


                        if (!TextUtils.isEmpty(Co2data1)) {
                            tvEyhtvalue.setText(Co2data1);
                            int Co2data1int = (int) Double.parseDouble(Co2data1);
                            if (Co2data1int > 0 && Co2data1int <= 450) {
                                //优
                                tvEyhtdengji.setText("清新");
                                tvEyhtdengji.setBackgroundColorNormal(getResources().getColor(R.color.qingxin));
                            } else if (Co2data1int > 450 && Co2data1int <= 1000) {
                                //良
                                tvEyhtdengji.setText("舒适");
                                tvEyhtdengji.setBackgroundColorNormal(getResources().getColor(R.color.shushi));
                            } else if (Co2data1int > 1000 && Co2data1int <= 2000) {
                                //轻度
                                tvEyhtdengji.setText("中等");
                                tvEyhtdengji.setBackgroundColorNormal(getResources().getColor(R.color.zhongdeng));
                            } else if (Co2data1int > 2000) {
                                //中度
                                tvEyhtdengji.setText("差");
                                tvEyhtdengji.setBackgroundColorNormal(getResources().getColor(R.color.cha));
                            }

                        } else {
                            tvEyhtdengji.setText("清新");
                            tvEyhtdengji.setBackgroundColorNormal(getResources().getColor(R.color.qingxin));
                        }


//                       ********** 温度
                        //Tem值
                        String Temdata1 = data.getTem().getData();
                        //Tem单位
                        String TemUnit = data.getTem().getUnit();


                        if (!TextUtils.isEmpty(Temdata1)) {
                            tvWdvalue.setText(Temdata1);
                            int Temdata1int = (int) Double.parseDouble(Temdata1);
                            if (Temdata1int <= 18) {
                                //优
                                tvWddengji.setText("冷");
                                tvWddengji.setBackgroundColorNormal(getResources().getColor(R.color.leng));
                            } else if (Temdata1int > 18 && Temdata1int <= 23) {
                                //良
                                tvWddengji.setText("舒适");
                                tvWddengji.setBackgroundColorNormal(getResources().getColor(R.color.wdshushi));
                            } else if (Temdata1int > 23 && Temdata1int <= 28) {
                                //轻度
                                tvWddengji.setText("偏热");
                                tvWddengji.setBackgroundColorNormal(getResources().getColor(R.color.pianre));
                            } else if (Temdata1int > 28) {
                                //中度
                                tvWddengji.setText("热");
                                tvWddengji.setBackgroundColorNormal(getResources().getColor(R.color.re));
                            }

                        } else {
                            tvWddengji.setText("舒适");
                            tvWddengji.setBackgroundColorNormal(getResources().getColor(R.color.wdshushi));
                        }


//                        *********** 湿度

                        //Hum值
                        String Humdata1 = data.getHum().getData();
                        //Hum单位
                        String HumUnit = data.getHum().getUnit();

                        if (!TextUtils.isEmpty(Humdata1)) {
                            tvSdvalue.setText(Humdata1);
                            int Humdata1int = (int) Double.parseDouble(Humdata1);
                            if (Humdata1int <= 40) {
                                //优
                                tvSddengji.setText("干燥");
                                tvSddengji.setBackgroundColorNormal(getResources().getColor(R.color.ganzao));
                            } else if (Humdata1int > 40 && Humdata1int <= 50) {
                                //良
                                tvSddengji.setText("舒适");
                                tvSddengji.setBackgroundColorNormal(getResources().getColor(R.color.sdshushi));
                            } else if (Humdata1int > 50 && Humdata1int <= 60) {
                                //轻度
                                tvSddengji.setText("怡人");
                                tvSddengji.setBackgroundColorNormal(getResources().getColor(R.color.yiren));
                            } else if (Humdata1int > 60) {
                                //中度
                                tvSddengji.setText("潮湿");
                                tvSddengji.setBackgroundColorNormal(getResources().getColor(R.color.chaoshi));
                            }

                        }


//                        *********************Tvoc
                        //Tvoc值
                        String Tvocdata1 = data.getTvoc().getData();
                        //Tvoc单位
                        String TvocUnit = data.getTvoc().getUnit();

                        if (!TextUtils.isEmpty(Tvocdata1)) {
                            tvTvocvalue.setText(Tvocdata1);
                            double Tvocdata1int = Double.parseDouble(Tvocdata1)/100; //david change /100 20180626
                            if (Tvocdata1int <= 0.3) {
                                //优
                                tvTvocdengji.setText("健康");
                                tvTvocdengji.setBackgroundColorNormal(getResources().getColor(R.color.jiankang));
                            } else if (Tvocdata1int > 0.3 && Tvocdata1int <= 0.6) {
                                //良
                                tvTvocdengji.setText("安全");
                                tvTvocdengji.setBackgroundColorNormal(getResources().getColor(R.color.anquan));
                            } else if (Tvocdata1int > 0.6 && Tvocdata1int <= 1) {
                                //轻度
                                tvTvocdengji.setText("污染");
                                tvTvocdengji.setBackgroundColorNormal(getResources().getColor(R.color.wuran));
                            } else if (Tvocdata1int > 1) {
                                //中度
                                tvTvocdengji.setText("严重污染");
                                tvTvocdengji.setBackgroundColorNormal(getResources().getColor(R.color.yanzhongwuran));
                            }

                        } else {
                            tvTvocdengji.setText("健康");
                            tvTvocdengji.setBackgroundColorNormal(getResources().getColor(R.color.jiankang));
                        }


//                        *************** 甲醛
                        //Hcho值
                        String Hchodata1 = data.getHcho().getData();
                        //Hcho单位
                        String hchoUnit = data.getHcho().getUnit();

                        if (!TextUtils.isEmpty(Hchodata1)) {
                            tvJqvalue.setText(Hchodata1);
                            double Hchodata1int = Double.parseDouble(Hchodata1)/100; //david change /100 20180626
                            if (Hchodata1int <= 0.06) {
                                //优
                                tvJqdj.setText("健康");
                                tvJqdj.setBackgroundColorNormal(getResources().getColor(R.color.jiankang));
                            } else if (Hchodata1int > 0.06 && Hchodata1int <= 0.08) {
                                //良
                                tvJqdj.setText("安全");
                                tvJqdj.setBackgroundColorNormal(getResources().getColor(R.color.anquan));
                            } else if (Hchodata1int > 0.08 && Hchodata1int <= 0.10) {
                                //轻度
                                tvJqdj.setText("污染");
                                tvJqdj.setBackgroundColorNormal(getResources().getColor(R.color.wuran));
                            } else if (Hchodata1int > 0.10) {
                                //中度
                                tvJqdj.setText("严重污染");
                                tvJqdj.setBackgroundColorNormal(getResources().getColor(R.color.yanzhongwuran));
                            }

                        } else {
                            tvJqdj.setText("健康");
                            tvJqdj.setBackgroundColorNormal(getResources().getColor(R.color.jiankang));
                        }


//                     *****************
                        //Remain值
                        String Remaindata1 = data.getRemain().getData();
                        //Remain单位
                        String RemainUnit = data.getRemain().getUnit();


//                      ******************
                        //Screen值
                        String Screendata1 = data.getScreen().getData();
                        //Screen单位
                        String ScreenUnit = data.getScreen().getUnit();
//                      ******************
                        setTianQi(data.getMac(), data.getWeather() + " " + data.getOuterTem(), data.getLocation(), data.getDate());
                        break;
                    case 601:
                        String msg2 = details.getMsg();
                        Toast.makeText(getActivity(), "暂未绑定设备，请扫描二维码绑定设备！", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

            }

            @Override
            public void onFalia(int code, String errst) {
                Toast.makeText(MyApplication.getInstance(), "通过ID查设备网络错误" + errst.toString(), Toast.LENGTH_SHORT).show();
                Log.e("8881", errst.toString());
            }
        });
    }

    /**
     * 获取24小时数据
     */
    public void getHistoryData(String id) {
        API.getHistoryData(id, new Okhttp.Objectcallback() {
            @Override
            public void onsuccess(String st) {


                e("722", "获取24小时数据" + st);
                Details_24 details = JSONUtils.parseJSON(st, Details_24.class);
                int code = details.getCode();
                switch (code) {
                    case 200:
                        String msg = details.getMsg();

                        //Toast.makeText(getActivity(), "数据获取成功！", Toast.LENGTH_SHORT).show(); ///david delete 20180625
                        List<Details_24.DataBean> Datas = details.getData();
                        if (Datas!=null&&Datas.size()>0) {
                            RecyclerViewHorizontalAdapter.clear();
                            RecyclerViewHorizontalAdapter.addll(Datas);
                            List<String> xdata = RecyclerViewHorizontalAdapter.getList().get(0).getXdata();
                            List<String> ydata = RecyclerViewHorizontalAdapter.getList().get(0).getYdata();
                            String Unit = RecyclerViewHorizontalAdapter.getList().get(0).getUnit();
                            vv.setText(Unit);
                            if (mLineChar == null) {
                                mLineChar = (JcoolGraph) view.findViewById(R.id.sug_recode_line);
                            }

                            List<Jchart> lines = new ArrayList<>();
                            lines.clear();
                            for (int i = 0; i < xdata.size(); i++) {
                                String s = ydata.get(i);
                                float v = Float.parseFloat(s);
                                lines.add(new Jchart(v, "" + (i + 1), Color.parseColor("#5F77F6")));

                            }


                            mLineChar.setShaderAreaColors(Color.parseColor("#4B494B"), Color.TRANSPARENT);
                            mLineChar.setLinePointRadio((int) mLineChar.getLineWidth());

                            mLineChar.setNormalColor(Color.parseColor("#676567"));
                            mLineChar.feedData(lines);
                        } else {
                            Toast.makeText(getActivity(), "24小时数据为空！", Toast.LENGTH_SHORT).show();
                        }




                        break;
                    case 601:
                        String msg2 = details.getMsg();
                        Toast.makeText(getActivity(), "暂未绑定设备，请扫描二维码绑定设备！", Toast.LENGTH_SHORT).show();
                        break;
                    case 500:
                        String msg3 = details.getMsg();
                        Toast.makeText(getActivity(), msg3, Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }

            }

            @Override
            public void onFalia(int code, String errst) {
                Log.e("722", "获取24小时数据" + errst.toString());
                Toast.makeText(getActivity(), "获取24小时历史网络错误" + errst.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void e(String tag, String msg) {
        if (true) {
            if (msg.length() > 9000) {
                for (int i = 0; i < msg.length(); i += 9000) {
                    if (i + 9000 < msg.length()) {
                        Log.e(tag, msg.substring(i, i + 9000));
                    } else {
                        Log.e(tag, msg.substring(i, msg.length()));
                    }
                }
            } else {
                Log.e(tag, msg);
            }
        }
    }

    public void setTianQi(String mac, String weather, String area, String date) {
        MyEvent tianQi = new MyEvent();
        tianQi.setMAC(mac);
        tianQi.setWeather(weather);
        tianQi.setAREA(area);
        tianQi.setTime(date);
        tianQi.setSc("setTianQi");
        EventBus.getDefault().post(tianQi);
    }

    boolean layout_kgs = false;
    boolean layout_jys = false;
    boolean layout_hys = false;
    boolean layout_qxs = false;
    boolean layout_zns = false;

    @OnClick({R.id.layout_kg, R.id.layout_jy, R.id.layout_hy, R.id.layout_qx, R.id.layout_zn, R.id.kongzhi, R.id.quxian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_kg:
                if (layout_kgs) {
                    Log.e("999", "1");
                    //关
                    imKg.setImageResource(R.drawable.kgs);
                    Log.e("设备号2", "xx");
                    setdangwei(getDevicesid(), "1");
                    layout_kgs = false;
                    layout_qxs = false;
                    layout_zns = false;
                    layout_hys = false;
                    layout_jys = false;
                    imQx.setImageResource(R.drawable.qxs);
                    imZn.setImageResource(R.drawable.zns);
                    imHy.setImageResource(R.drawable.hys);
                    imJy.setImageResource(R.drawable.jys);
                    layoutJy.setEnabled(false);
                    layoutHy.setEnabled(false);
                    layoutQx.setEnabled(false);
                    layoutZn.setEnabled(false);
                } else {
                    layoutJy.setEnabled(true);
                    layoutHy.setEnabled(true);
                    layoutQx.setEnabled(true);
                    layoutZn.setEnabled(true);
                    Log.e("999", "2");
                    setdangwei(getDevicesid(), "5");
                    //开
                    imKg.setImageResource(R.drawable.kgq);
                    layout_kgs = true;
                    imJy.setImageResource(R.drawable.jyq);
                    layout_jys = true;
                    setdangwei(getDevicesid(), "2");
                }

                break;
            case R.id.layout_jy:
//                if (layout_jys) {
//                    Log.e("999", "静音G");
//                    //关
//                    imJy.setImageResource(R.drawable.jys);
//
//                    layout_jys = false;
//                } else {
                setdangwei(getDevicesid(), "2");
                Log.e("999", "静音k");
                //开
                imJy.setImageResource(R.drawable.jyq);
                layout_jys = true;

                imZn.setImageResource(R.drawable.zns);
                layout_zns = false;
                imQx.setImageResource(R.drawable.qxs);
                layout_qxs = false;
                imHy.setImageResource(R.drawable.hys);
                layout_hys = false;
//                }
                break;
            case R.id.layout_hy:
//                if (layout_hys) {
//                    Log.e("999", "会议G");
//                    //关
//                    imHy.setImageResource(R.drawable.hys);
//                    layout_hys = false;
//                } else {
                setdangwei(getDevicesid(), "3");
                Log.e("999", "会议K");
                //开
                imHy.setImageResource(R.drawable.hyq);
                layout_hys = true;

                imZn.setImageResource(R.drawable.zns);
                layout_zns = false;
                imQx.setImageResource(R.drawable.qxs);
                layout_qxs = false;
                imJy.setImageResource(R.drawable.jys);
                layout_jys = false;
//                }
                break;
            case R.id.layout_qx:
//                if (layout_qxs) {
//                    Log.e("999", "清新G");
//                    //关
//                    imQx.setImageResource(R.drawable.qxs);
//                    layout_qxs = false;
//                } else {
                setdangwei(getDevicesid(), "4");
                Log.e("999", "清新K");
                //开
                imQx.setImageResource(R.drawable.qxq);
                layout_qxs = true;

                imZn.setImageResource(R.drawable.zns);
                layout_zns = false;
                imHy.setImageResource(R.drawable.hys);
                layout_hys = false;
                imJy.setImageResource(R.drawable.jys);
                layout_jys = false;
//                }
                break;
            case R.id.layout_zn:
//                if (layout_zns) {
//                    Log.e("999", "智能G");
//                    //关
//                    imZn.setImageResource(R.drawable.zns);
//                    layout_zns = false;
//                } else {
                setdangwei(getDevicesid(), "6");
                Log.e("999", "智能K");
                //开
                imZn.setImageResource(R.drawable.znq);
                layout_zns = true;

                imQx.setImageResource(R.drawable.qxs);
                layout_qxs = false;
                imHy.setImageResource(R.drawable.hys);
                layout_hys = false;
                imJy.setImageResource(R.drawable.jys);
                layout_jys = false;
//                }
                break;
            case R.id.kongzhi:
                layoutSbkzs.setVisibility(View.VISIBLE);
                layoutQxs.setVisibility(View.GONE);
                kongzhi.setTextColor(getResources().getColor(R.color.holo_blue_bright));
                im1.setVisibility(View.VISIBLE);

                quxian.setTextColor(getResources().getColor(R.color.white));
                im2.setVisibility(View.GONE);
                break;
            case R.id.quxian:
                layoutSbkzs.setVisibility(View.GONE);
                layoutQxs.setVisibility(View.VISIBLE);
                quxian.setTextColor(getResources().getColor(R.color.holo_blue_bright));
                im2.setVisibility(View.VISIBLE);
                kongzhi.setTextColor(getResources().getColor(R.color.white));
                im1.setVisibility(View.GONE);
                break;
        }
    }

    public void setdangwei(String deviceId, String funcId) {
        Log.e("333", "deviceId" + deviceId);
        API.setgear(deviceId, funcId, new Okhttp.Objectcallback() {
            @Override
            public void onsuccess(String st) {
                Log.e("722", "setdangwei" + st);

            }

            @Override
            public void onFalia(int code, String errst) {
                Log.e("722", "errst" + errst.toString());
                Toast.makeText(getActivity(), "控制设备网络错误" + errst.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean bian = false;
    LinearLayoutManager HorizontalLayout;
    RecyclerView24Adapter RecyclerViewHorizontalAdapter;

    public void showrecycle() {


        RecyclerViewHorizontalAdapter = new RecyclerView24Adapter();

        HorizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);
        RecyclerViewHorizontalAdapter.setOnItemClickListener(new RecyclerView24Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                RecyclerViewHorizontalAdapter.setThisPosition(position);
                //嫑忘记刷新适配器
                RecyclerViewHorizontalAdapter.notifyDataSetChanged();
                List<String> xdata = RecyclerViewHorizontalAdapter.getList().get(position).getXdata();
                List<String> ydata = RecyclerViewHorizontalAdapter.getList().get(position).getYdata();
                vv.setText(""+RecyclerViewHorizontalAdapter.getList().get(position).getUnit());
                for (int e = 0; e < 2; e++) {

                    List<Jchart> lines = new ArrayList<>();
                    lines.clear();
                    for (int i = 0; i < xdata.size(); i++) {
                        String s = ydata.get(i);
                        float v = Float.parseFloat(s);
                        lines.add(new Jchart(v, "" + (i + 1), Color.parseColor("#5F77F6")));

                    }

                    String max = Collections.max(ydata);
                    String min = Collections.min(ydata);
                    Log.e("888", "xx" + ((int) Float.parseFloat(max) + 2));
                    if (bian) {
                        bian = true;
                        mLineChar.setYaxisValues(0, (int) Float.parseFloat(max) + 1, 2);

                    } else {
                        bian = false;
                        mLineChar.setYaxisValues(1, (int) Float.parseFloat(max) + 2, 2);
                    }


                    mLineChar.postInvalidate();
                    mLineChar.setShaderAreaColors(Color.parseColor("#4B494B"), Color.TRANSPARENT);
                    mLineChar.setLinePointRadio((int) mLineChar.getLineWidth());

                    mLineChar.setNormalColor(Color.parseColor("#676567"));
                    mLineChar.aniChangeData(lines);
                }


            }
        });
    }
}
