package demo.union.e.qq.com.tv.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.base.MyApplication;
import demo.union.e.qq.com.tv.beans.Details;

/**
 * Created by acer on 2018/4/26.
 * 设备-设备列表
 */

public class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewListAdapter.MyView> implements View.OnClickListener {

    private List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> list = new ArrayList<>();

    public class MyView extends RecyclerView.ViewHolder {

        public TextView textView,tv_fj,tv_mc,tv_pm25,tv_co,tv_tvoc,tv_jq,tv_wd,ID,tv_sd;
        RelativeLayout rela;
        TextView tv_value0;
        public MyView(View view) {
            super(view);
            rela = (RelativeLayout) view.findViewById(R.id.re);
            textView = (TextView) view.findViewById(R.id.title);

            tv_fj = (TextView) view.findViewById(R.id.tv_fj);
            tv_mc = (TextView) view.findViewById(R.id.tv_mc);
            tv_pm25 = (TextView) view.findViewById(R.id.tv_pm25);
            tv_co = (TextView) view.findViewById(R.id.tv_co);
            tv_tvoc = (TextView) view.findViewById(R.id.tv_tvoc);
            tv_jq = (TextView) view.findViewById(R.id.tv_jq);

            tv_wd = (TextView) view.findViewById(R.id.tv_wd);
            ID = (TextView) view.findViewById(R.id.ID);
            tv_sd = (TextView) view.findViewById(R.id.tv_sd);
            tv_value0 = (TextView) view.findViewById(R.id.tv_value0);

        }
    }

    public void addll(List<Details.DataBean.GroupDataListBean.DeviceItemPosBean> dataBeans) {
//        list.clear();
        list.addAll(dataBeans);
        notifyDataSetChanged();
    }

    public void clear( ) {
        list.clear();


    }
    public RecyclerViewListAdapter() {

    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deviceslist, parent, false);
        itemView.setOnClickListener(this);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
//        holder.itemView.setTag(Groupposition);







        holder.tv_fj.setText(list.get(position).getDeviceName());


        holder.tv_mc.setText(list.get(position).getDeviceTypeName());


        holder.tv_pm25.setText(list.get(position).getPm());


        holder.tv_co.setText(list.get(position).getCo2());


        holder.tv_tvoc.setText(list.get(position).getTvoc());


        holder.tv_jq.setText(list.get(position).getHcho());


        holder.tv_wd.setText(list.get(position).getTem());

        holder.ID.setText(list.get(position).getDeviceId());


        holder.tv_sd.setText(list.get(position).getHum());
        holder.tv_value0.setText(""+(position+1));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    //先声明一个int成员变量
    private int thisPosition;

    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }
}
