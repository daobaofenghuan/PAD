package demo.union.e.qq.com.tv.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.ielse.view.SwitchView;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.adapter.RecyclerView_Bfpl_Adapter;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.http.Content;
import demo.union.e.qq.com.tv.utils.SpfUtils;

public class Setting_GG_Activity extends AppCompatActivity {


    @BindView(R.id.textc)
    TextView textc;
    @BindView(R.id.SwitchView)
    ch.ielse.view.SwitchView SwitchView;
    @BindView(R.id.textc2)
    TextView textc2;
    @BindView(R.id.SwitchView2)
    ch.ielse.view.SwitchView SwitchView2;
    @BindView(R.id.listsetting)
    RecyclerView listV8_BFPL;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    List<String> st = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__gg_);
        ButterKnife.bind(this);
        st.add("无");
        st.add("5 min");
        st.add("10 min");
        st.add("15 min");
        showBfpl_Adapter();
        boolean spgg = (boolean) SpfUtils.get(Setting_GG_Activity.this, "spgg", false);
        if (spgg) {
            SwitchView.setOpened(true);
            SwitchView2.setOpened(false);
        } else {
            SwitchView.setOpened(false);
            SwitchView2.setOpened(true);
        }
        SwitchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isOpened = SwitchView.isOpened();
                if (isOpened) {
                     SpfUtils.put(Setting_GG_Activity.this, "spgg", true);
                    SwitchView2.setOpened(false);
                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.SPGG);
                    tianQi.setContent("SP");
                    EventBus.getDefault().post(tianQi);
                } else {
                    SpfUtils.put(Setting_GG_Activity.this, "spgg", false);
                    SwitchView2.setOpened(true);
                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.SPGG);
                    tianQi.setContent("TP");
                    EventBus.getDefault().post(tianQi);
                }
            }

        });

        SwitchView2.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                boolean isOpened = SwitchView2.isOpened();
                if (isOpened) {
                    SpfUtils.put(Setting_GG_Activity.this, "spgg", false);
                    SwitchView.setOpened(false);
                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.SPGG);
                    tianQi.setContent("TP");
                    EventBus.getDefault().post(tianQi);
                } else {
                    SpfUtils.put(Setting_GG_Activity.this, "spgg", true);
                    SwitchView.setOpened(true);
                    MyEvent tianQi = new MyEvent();
                    tianQi.setSc(Content.SPGG);
                    tianQi.setContent("SP");
                    EventBus.getDefault().post(tianQi);
                }
            }
        });
    }
    RecyclerView_Bfpl_Adapter recyclerView_bfpl_adapter;
    LinearLayoutManager HorizontalLayout;
    public void showBfpl_Adapter() {

        int jiange = (int) SpfUtils.get(Setting_GG_Activity.this, "jiange", 300);
        int posi= jiange / 1000;

        recyclerView_bfpl_adapter = new RecyclerView_Bfpl_Adapter();

        HorizontalLayout = new LinearLayoutManager(Setting_GG_Activity.this, LinearLayoutManager.HORIZONTAL, false);
        listV8_BFPL.setLayoutManager(HorizontalLayout);
        listV8_BFPL.addItemDecoration(new SpaceItemDecoration(10));
        listV8_BFPL.setAdapter(recyclerView_bfpl_adapter);
        recyclerView_bfpl_adapter.addll(st);
        recyclerView_bfpl_adapter.setOnItemClickListener(new RecyclerView_Bfpl_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                recyclerView_bfpl_adapter.setThisPosition(position);
                //嫑忘记刷新适配器
                recyclerView_bfpl_adapter.notifyDataSetChanged();
                Toast.makeText(Setting_GG_Activity.this, "设置播放频率成功！", Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        SpfUtils.put(Setting_GG_Activity.this, "jiange", 1000);

                        break;
                    case 1:
                        settime(5);
                        break;
                    case 2:
                        settime(10);
                        break;
                    case 3:
                        settime(15);
                        break;

                    default:
                        break;
                }

            }
        });
        switch (posi) {
            case 1:
                recyclerView_bfpl_adapter.setThisPosition(0);
                //嫑忘记刷新适配器
                recyclerView_bfpl_adapter.notifyDataSetChanged();
                break;
            case 5:
                recyclerView_bfpl_adapter.setThisPosition(1);
                //嫑忘记刷新适配器
                recyclerView_bfpl_adapter.notifyDataSetChanged();
                break;
            case 10:
                recyclerView_bfpl_adapter.setThisPosition(2);
                //嫑忘记刷新适配器
                recyclerView_bfpl_adapter.notifyDataSetChanged();
                break;
            case 15:
                recyclerView_bfpl_adapter.setThisPosition(3);
                //嫑忘记刷新适配器
                recyclerView_bfpl_adapter.notifyDataSetChanged();
                break;

            default:
                break;
        }

    }
    public void settime(int i) {
        SpfUtils.put(Setting_GG_Activity.this, "jiange", i * 1000);

    }

}
