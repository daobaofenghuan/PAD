package demo.union.e.qq.com.tv.base;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Adapter基类
 *
 *
 * @version 1.0
 */
public abstract class Base_Z_Adapter extends android.widget.BaseAdapter {

    /**
     * 数据存储集合
     **/
    public List<Object> mDataList = new ArrayList<Object>();
    /**
     * Context上下文
     **/


    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    public Base_Z_Adapter() {

    }


    @Override
    public int getCount() {
        Log.e("777","77"+ mDataList.size());
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < mDataList.size())
            return mDataList.get(position);
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 添加数据
     */
    public boolean addItem(Object object) {
        return mDataList.add(object);
    }

    /**
     * 在指定索引位置添加数据
     *
     * @param location 索引
     * @param object   数据
     */
    public void addItem(int location, Object object) {
        mDataList.add(location, object);
    }

    /**
     * 集合方式添加数据
     *
     * @param collection 集合
     */
    public boolean addItem(Collection<? extends Object> collection) {
        boolean b = mDataList.addAll(collection);
        notifyDataSetChanged();
        return b;

    }

    public List<Object> getdate() {

        return mDataList;

    }

    /**
     * 在指定索引位置添加数据集合
     *
     * @param location   索引
     * @param collection 数据集合
     */
    public boolean addItem(int location, Collection<? extends Object> collection) {
        return mDataList.addAll(location, collection);
    }

    /**
     * 移除指定对象数据
     *
     * @param object 移除对象
     * @return 是否移除成功
     */
    public boolean removeItem(Object object) {
        return mDataList.remove(object);
    }

    /**
     * 移除指定索引位置对象
     *
     * @param location 删除对象索引位置
     * @return 被删除的对象
     */
    public Object removeItem(int location) {
        return mDataList.remove(location);
    }

    /**
     * 移除指定集合对象
     *
     * @param collection 待移除的集合
     * @return 是否移除成功
     */
    public boolean removeAll(Collection<? extends Object> collection) {
        return mDataList.removeAll(collection);
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDataList.clear();
    }


}
