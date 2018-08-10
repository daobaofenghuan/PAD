package demo.union.e.qq.com.tv.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.LinearLayout;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.ielse.view.SwitchView;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;

/**
 * 设备显示
 */
public class Setting_SBXS_Activity extends AppCompatActivity {

    @BindView(R.id.textc)
    TextView textc;
    @BindView(R.id.SwitchView)
    SwitchView switchView;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__sbxz_);
        ButterKnife.bind(this);
        if (API.APIBoolen==true) {
            switchView.setOpened(true);
        } else {
            switchView.setOpened(false);
        }
        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isOpened = switchView.isOpened();
                if (isOpened) {
                    API.APIBoolen=true;

                } else {
                    API.APIBoolen=false;

                }


            }
        });
    }


}
