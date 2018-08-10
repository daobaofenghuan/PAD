package demo.union.e.qq.com.tv.scene_fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.superplayer.library.SuperPlayer;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.beans.Details;
import demo.union.e.qq.com.tv.beans.MyEvent;
import demo.union.e.qq.com.tv.http.API;
import demo.union.e.qq.com.tv.http.Content;
import demo.union.e.qq.com.tv.ui.Setting_DevicesCJ_Activity;
import demo.union.e.qq.com.tv.ui.Setting_GG_Activity;
import demo.union.e.qq.com.tv.ui.Setting_SBXZS_Activity;
import demo.union.e.qq.com.tv.utils.SpfUtils;

/**
 * 场景介绍
 */
public class SceneIntroduce_Fragment extends Fragment implements SuperPlayer.OnNetChangeListener {

    View view;

    @BindView(R.id.view_super_player)
    SuperPlayer viewSuperPlayer;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.textView11)
    TextView textView11;
    Unbinder unbinder;


    private SuperPlayer player;
    private boolean isLive;

    //    地址
    private String url;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_sceneintroduce, null);
            ButterKnife.bind(this, view);
            EventBus.getDefault().register(this);
            initview();
            bannner();
            boolean cj = (boolean) SpfUtils.get(getActivity(), "cj", false);
            if (cj) {
                textView10.setVisibility(View.VISIBLE);
            } else {
                textView10.setVisibility(View.GONE);
            }
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }


        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override

    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();


    }

    void initview() {
        textView10.setText(API.memo);
        Log.e("db_test","介绍的话"+API.memo);

        initData();

        initPlayer();
    }

    /**
     * 初始化相关的信息
     */
    private void initData() {
        isLive = true;
        url = API.videoUrl;

    }

    /**
     * 初始化视图
     */


    /**
     * 初始化播放器
     */
    private void initPlayer() {
        player = (SuperPlayer) view.findViewById(R.id.view_super_player);
        Log.e("db_test","=================================播放组件厨师话==========================");
        Log.e("db_test",url);
       // url="http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8";
     //   url="http://idcfota.oss-cn-hangzhou.aliyuncs.com/DASKLIMA/zuixin35.mp4";

        Log.e("db_test","===========================================================");
       // isLive=false;
        if (isLive) {
            player.setLive(true);//设置该地址是直播的地址
        }
         Log.e("db_test", "是直播么"+String.valueOf(isLive));
        player.setHideControl(true);
        player.setNetChangeListener(true)//设置监听手机网络的变化
                .setOnNetChangeListener(this)//实现网络变化的回调
                .onPrepared(new SuperPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared() {
                        /**
                         * 监听视频是否已经准备完成开始播放。（可以在这里处理视频封面的显示跟隐藏）
                         */
                    }
                }).onComplete(new Runnable() {
            @Override
            public void run() {
                Log.e("722", "播放完成了");
                player.show();
                int time = (int) SpfUtils.get(getActivity(), "jiange", 300);
                API.JG_TIME = time;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        player.start();
                    }
                }, API.JG_TIME);

                /**
                 * 监听视频是否已经播放完成了。（可以在这里处理视频播放完成进行的操作）
                 */
            }
        }).onInfo(new SuperPlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                /**
                 * 监听视频的相关信息。
                 */

            }
        }).onError(new SuperPlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                /**
                 * 监听视频播放失败的回调
                 */

            }
        }).setTitle(url)//设置视频的titleName
                .play(url);//开始播放视频
        player.setScaleType(SuperPlayer.SCALETYPE_FITXY);
        boolean spgg = (boolean) SpfUtils.get(getActivity(), "spgg", false);
        if (!spgg) {
            player.stop();
        }
    }


    /**
     * 网络链接监听类
     */
    @Override
    public void onWifi() {
        Toast.makeText(getActivity(), "当前网络环境是WIFI", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMobile() {
        Toast.makeText(getActivity(), "当前网络环境是手机网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        Toast.makeText(getActivity(), "网络链接断开", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoAvailable() {
        Toast.makeText(getActivity(), "无网络链接", Toast.LENGTH_SHORT).show();
    }


    /**
     * 下面的这几个Activity的生命状态很重要
     */
    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }


    public void onEventMainThread(MyEvent event) {

        if (!TextUtils.isEmpty(event.getSc())) {

            if (event.getSc().equals(Content.CJShow)) {
                SpfUtils.put(getActivity(), "cj", true);
                textView10.setVisibility(View.VISIBLE);
            } else if (event.getSc().equals(Content.CJUnShow)) {
                SpfUtils.put(getActivity(), "cj", false);
                textView10.setVisibility(View.GONE);
            } else if (event.getSc().equals(Content.SPGG)) {
                Log.e("spgg", event.getContent());
                if (event.getContent().equals("SP")) {
                    banner.setVisibility(View.GONE);
                    player.setVisibility(View.VISIBLE);
                    if (player != null && !player.isPlaying()) {
                        player.start();
                    }
                } else {
                    player.setVisibility(View.GONE);
                    banner.setVisibility(View.VISIBLE);
                    if (player != null && player.isPlaying()) {
                        player.stop();
                    }

                }

            }
        }
    }




    Banner banner;

    void bannner() {

        String[] urls = getActivity().getResources().getStringArray(R.array.url);
        String[] tips = getActivity().getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
//        images = new ArrayList(list);
//        List list1 = Arrays.asList(tips);
//        titles = new ArrayList(list1);
        int position = (int) SpfUtils.get(getActivity(), "position", 0);

        List<Details.DataBean.GroupDataListBean> gg = API.GroupDataListBean;
        if (gg != null && gg.size() > 0) {
            if (gg.get(position)!=null) {
                banner = (Banner) view.findViewById(R.id.banner);
                //设置banner样式
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(gg.get(position).getAdImages());
                //设置banner动画效果
                banner.setBannerAnimation(Transformer.Default);
                //设置标题集合（当banner样式有显示title时）

                //设置自动轮播，默认为true
                banner.isAutoPlay(true);
                //设置轮播时间
                banner.setDelayTime(3500);
                //设置指示器位置（当banner模式中有指示器时）
                banner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                banner.start();
            } else {
                Toast.makeText(getActivity(), "没有图片广告！", Toast.LENGTH_SHORT).show();
            }

        } else {

        }

    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {


            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);


        }


    }

}
