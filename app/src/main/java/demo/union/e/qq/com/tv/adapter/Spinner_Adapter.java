package demo.union.e.qq.com.tv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.base.Base_Z_Adapter;
import demo.union.e.qq.com.tv.base.MyApplication;
import demo.union.e.qq.com.tv.db.bean.Life_Storey;


/**
 * Created by 他的猫 on 2016/12/3.
 * Spinner适配器
 */

public class Spinner_Adapter extends Base_Z_Adapter {
    Spinner_Adapter.Holder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 查找控件
        if (null == convertView) {
            convertView = LayoutInflater.from(MyApplication.getInstance())
                    .inflate(R.layout.tab_item_1_work, null);
            holder = new Spinner_Adapter.Holder();
            holder.tv_rname = (TextView) convertView
                    .findViewById(R.id.tv_tv);


            // 缓存Holder
            convertView.setTag(holder);
        } else {
            holder = (Spinner_Adapter.Holder) convertView.getTag();
        }
        Life_Storey item = (Life_Storey) getItem(position);
        holder.tv_rname.setText(item.getAccount());


        return convertView;
    }

    public class Holder {
        TextView tv_rname ;


    }
}
