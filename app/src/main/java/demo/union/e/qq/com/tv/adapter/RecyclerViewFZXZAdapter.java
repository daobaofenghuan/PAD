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
 * 设置-分组选择适配器
 */

public class RecyclerViewFZXZAdapter extends RecyclerView.Adapter<RecyclerViewFZXZAdapter.MyView> implements View.OnClickListener {

    private List<Details.DataBean.GroupDataListBean> list = new ArrayList<>();

    public class MyView extends RecyclerView.ViewHolder {

        public TextView textView;
        RelativeLayout rela;

        public MyView(View view) {
            super(view);
            rela = (RelativeLayout) view.findViewById(R.id.re);
            textView = (TextView) view.findViewById(R.id.title);

        }
    }

    public void addll(List<Details.DataBean.GroupDataListBean> dataBeans) {
//        list.clear();
        list.addAll(dataBeans);
        notifyDataSetChanged();
    }


    public RecyclerViewFZXZAdapter() {

    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_f31, parent, false);
        itemView.setOnClickListener(this);
        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {
        holder.itemView.setTag(position);
        holder.textView.setText(list.get(position).getGroupName());
        if (position == getthisPosition()) {
            holder.rela.setBackgroundDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.im_s));
        } else {
            holder.rela.setBackgroundDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.im_q));

        }
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
