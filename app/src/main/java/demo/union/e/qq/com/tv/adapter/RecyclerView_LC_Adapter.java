package demo.union.e.qq.com.tv.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.base.MyApplication;
import demo.union.e.qq.com.tv.db.bean.GaiLan;
import demo.union.e.qq.com.tv.db.bean.Life_Devices;
import demo.union.e.qq.com.tv.db.bean.Life_Storey;

/**
 * Created by acer on 2018/4/26.
 * 楼层adaptet
 */

public class RecyclerView_LC_Adapter extends RecyclerView.Adapter<RecyclerView_LC_Adapter.MyView> implements View.OnClickListener {

    private List<GaiLan> list = new ArrayList<>();

    public class MyView extends RecyclerView.ViewHolder {

        public TextView textView;
        RelativeLayout rela;
        GridView gv_images;

        public MyView(View view) {
            super(view);
            rela = (RelativeLayout) view.findViewById(R.id.re);
            textView = (TextView) view.findViewById(R.id.tv_title);
            gv_images = (GridView) view.findViewById(R.id.gv_images);

        }
    }

    public void addll(List<GaiLan> dataBeans) {
//        list.clear();
        list.addAll(dataBeans);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();

    }

    public RecyclerView_LC_Adapter() {

    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_lc, parent, false);
        itemView.setOnClickListener(this);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
//        GaiLan gaiLan = list.get(position);
        List<Life_Devices> life_devices = list.get(position).getLife_devices();
        GridViewAdapter gridViewAdapter = new GridViewAdapter(MyApplication.getInstance());
        if (life_devices!=null&&life_devices.size()>0) {
            gridViewAdapter.addll(life_devices);
        }

        holder.gv_images.setAdapter(gridViewAdapter);

        holder.itemView.setTag(position);
        holder.textView.setText(list.get(position).getName());

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
