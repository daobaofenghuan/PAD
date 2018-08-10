/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.union.e.qq.com.tv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siberiadante.customdialoglib.EditDialog;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.base.MyApplication;
import demo.union.e.qq.com.tv.beans.Code;
import demo.union.e.qq.com.tv.beans.Details;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.db.bean.Life_Storey;
import demo.union.e.qq.com.tv.db.bean.Z_Login_Life_DeviceDao;
import demo.union.e.qq.com.tv.db.bean.Z_Login_Life_StoreyDao;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;
import demo.union.e.qq.com.tv.http.Okhttp;
import demo.union.e.qq.com.tv.utils.JSONUtils;

/**
 * 设置 -设备列表适配器
 */
public class Settting_Device_Adapter extends RecyclerView.Adapter<Settting_Device_Adapter.MyView> implements View.OnClickListener {


    private List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> list = new ArrayList<>();
    Context context;

    @Override
    public void onClick(View view) {

    }

    Spinner_Adapter class_adapter;
    boolean idshua = false;

    public class MyView extends RecyclerView.ViewHolder {

        public TextView device_id, device_mc, device_bm;
        Spinner tv_spinner;
        Z_Login_Life_StoreyDao z_login_lifeStoreyDao;

        public MyView(View view) {
            super(view);

            device_id = (TextView) view.findViewById(R.id.device_id);
            device_mc = (TextView) view.findViewById(R.id.device_mc);
            device_bm = (TextView) view.findViewById(R.id.device_bm);
            tv_spinner = (Spinner) view.findViewById(R.id.tv_spinner);

        }
    }

    public void addll(List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> dataBeans, Context context) {

        this.context = context;
        list.addAll(dataBeans);
        notifyDataSetChanged();
    }

    public void clear() {


        list.clear();
    }


    Z_Login_Life_StoreyDao z_login_lifeStoreyDao;
    List<Life_Storey> quell;
    Z_Login_Life_DeviceDao z_login_life_deviceDao;

    public Settting_Device_Adapter(Z_Login_Life_StoreyDao z_login_lifeStoreyDao, Z_Login_Life_DeviceDao z_login_life_deviceDao) {
        this.z_login_lifeStoreyDao = z_login_lifeStoreyDao;
        this.z_login_life_deviceDao = z_login_life_deviceDao;
    }

    @Override
    public Settting_Device_Adapter.MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_setting, parent, false);
        itemView.setOnClickListener(this);
        return new Settting_Device_Adapter.MyView(itemView);
    }

    @Override
    public void onBindViewHolder(MyView holder, final int position) {

        class_adapter = new Spinner_Adapter();
        holder.tv_spinner.setAdapter(class_adapter);
        show();
        holder.itemView.setTag(position);
        holder.device_id.setText(list.get(position).getDeviceId());
        holder.device_mc.setText(list.get(position).getDeviceTypeName());
        holder.device_bm.setText(list.get(position).getDeviceName());
        holder.device_bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyApplication.getInstance(), "更改名字", Toast.LENGTH_SHORT).show();
                String deviceId = list.get(position).getDeviceId();
                showEditDialog(deviceId, position);
            }
        });
        holder.tv_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int positions, long l) {
//                if (idshua) {
                //楼层
                Life_Storey life_storey = quell.get(positions);
                //设备号
                String deviceId = list.get(position).getDeviceId();
                String account = life_storey.getAccount();
                Log.e("选择楼层", "account" + account + "positions" + deviceId);
                //更新设备号楼层

                z_login_life_deviceDao.updateColumnValue(deviceId, life_storey.getAccount());
                //更新设备所在楼层
                MyEvent tianQi = new MyEvent();
                tianQi.setSc(Content.EventRefresh_StoreyList);
                EventBus.getDefault().post(tianQi);

//                }else {
//                    idshua = true;
//                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    /**
     * EditDialog
     */
    private void showEditDialog(final String deviceId, final int position) {
        final EditDialog dialog = new EditDialog(context).builder();
        dialog.setTitle("更改名字");
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
                Toast.makeText(MyApplication.getInstance(), "输入内容为：" + msg, Toast.LENGTH_LONG).show();
                API.editDevice(deviceId, msg, new Okhttp.Objectcallback() {
                    @Override
                    public void onsuccess(String st) {
                        Code code = JSONUtils.parseJSON(st, Code.class);
                        if (code.getCode() == 200) {
                            Log.e("更改", "xx" + st);
                            list.get(position).setDeviceName(msg);
                            notifyDataSetChanged();
                            MyEvent event = new MyEvent();
                            event.setSc(Content.EventRefresh_DeviceList);
                            EventBus.getDefault().post(event);
                            Toast.makeText(MyApplication.getInstance(), "更改名称成功", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFalia(int code, String errst) {
                        Log.e("更改", "xx" + errst.toString());
                    }
                });
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void show() {
        quell = z_login_lifeStoreyDao.quell();
//        Log.e("711", "大小" + quell.size());
        if (class_adapter != null) {
            class_adapter.clear();
            class_adapter.addItem(quell);
            class_adapter.notifyDataSetChanged();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private RecyclerViewFZXZAdapter.OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(RecyclerViewFZXZAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
