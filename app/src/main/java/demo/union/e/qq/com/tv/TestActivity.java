package demo.union.e.qq.com.tv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.adapter.RecyclerViewAdapter;
import demo.union.e.qq.com.tv.beans.Details;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.fragment.Devices_1_Fragment;
import demo.union.e.qq.com.tv.fragment.Scene_2_Fragment;
import demo.union.e.qq.com.tv.fragment.Setting_4_Fragment;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;
import demo.union.e.qq.com.tv.http.Okhttp;
import demo.union.e.qq.com.tv.ui.SpaceItemDecoration;
import demo.union.e.qq.com.tv.utils.JSONUtils;
import demo.union.e.qq.com.tv.utils.SpfUtils;

/**
 * 主界面
 */
public class TestActivity extends FragmentActivity {


}
