package demo.union.e.qq.com.tv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.db.bean.Life_Devices;
import demo.union.e.qq.com.tv.db.bean.Life_Storey;


public class GridViewAdapter extends BaseAdapter {

    public Context context;
    public List<Life_Devices> list=new ArrayList<>();

    private int wh;

    public GridViewAdapter(Context context) {
        this.context = context;
//		this.wh=(SysUtils.getScreenWidth(context)-SysUtils.Dp2Px(context, 99))/3;


    }

    public void addll(List<Life_Devices> dataBeans) {
//        list.clear();
        list.addAll(dataBeans);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }


    @Override
    public View getView(final int position, View view, ViewGroup arg2) {
        Holder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_gridview, null);
            holder = new Holder();
            holder.imageView = (TextView) view.findViewById(R.id.imageView);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        Life_Devices item = (Life_Devices) getItem(position);
        holder.imageView.setText(item.getDevicename());
//        AbsListView.LayoutParams param = new AbsListView.LayoutParams(wh,wh);
//        view.setLayoutParams(param);
        return view;
    }

    class Holder {
        TextView imageView;
    }


}
