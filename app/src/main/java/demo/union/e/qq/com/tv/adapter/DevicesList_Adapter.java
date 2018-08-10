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

import java.util.List;

import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.base.CommonRecyclerViewAdapter;
import demo.union.e.qq.com.tv.base.CommonRecyclerViewHolder;
import demo.union.e.qq.com.tv.beans.Details;
import demo.union.e.qq.com.tv.data.ItemBean;


public class DevicesList_Adapter extends CommonRecyclerViewAdapter<Details.DataBean.GroupDataListBean.DeviceItemPosBean> {
    private boolean isV7;

    public DevicesList_Adapter(Context context, boolean isV7) {
        super(context);
        this.isV7 = isV7;
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_deviceslist;
    }

    @Override
    public void onBindItemHolder(CommonRecyclerViewHolder helper, Details.DataBean.GroupDataListBean.DeviceItemPosBean item, int position) {
        helper.getHolder().setText(R.id.tv_fj,item.getDeviceName()  );

        helper.getHolder().setText(R.id.tv_mc, item.getDeviceTypeName());

         helper.getHolder().setText(R.id.tv_pm25, item.getPm());
        helper.getHolder().setText(R.id.tv_co, item.getCo2());
        helper.getHolder().setText(R.id.tv_tvoc, item.getTvoc());
        helper.getHolder().setText(R.id.tv_jq, item.getHcho());

        helper.getHolder().setText(R.id.tv_wd, item.getTem());
        helper.getHolder().setText(R.id.ID, item.getDeviceId());

        helper.getHolder().setText(R.id.tv_sd, item.getHum());


    }



}
