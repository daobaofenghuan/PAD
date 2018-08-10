package demo.union.e.qq.com.tv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.utils.SpfUtils;


/**
 * 闪屏页面
 */
public class Splash_Activity extends FragmentActivity {


    private static final int GO_HOME = 1000;
    // 延迟3秒
    private static final long SPLASH_DELAY_MILLIS = 3000;
    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:

                    startActivity(new Intent(Splash_Activity.this, MainActivity.class));
                    finish();


                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_splash_);
        //延迟3秒
        mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
    }
}
