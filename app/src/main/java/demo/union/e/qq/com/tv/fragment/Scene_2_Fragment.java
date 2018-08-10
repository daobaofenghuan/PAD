package demo.union.e.qq.com.tv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.union.e.qq.com.tv.R;
import demo.union.e.qq.com.tv.base.ContentKey;
import demo.union.e.qq.com.tv.scene_fragment.SceneIntroduce_Fragment;
import demo.union.e.qq.com.tv.scene_fragment.SceneOverview_Fragment;

/**
 * 场景Fragment
 */
public class Scene_2_Fragment extends Fragment {

    View view;
    Unbinder unbinder;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.main_frame_layouts)
    FrameLayout mainFrameLayouts;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.im2)
    ImageView im2;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getActivity(), R.layout.fragment_scene, null);
            ButterKnife.bind(this, view);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button.setTextColor(getResources().getColor(R.color.holo_blue_bright));
                    im1.setVisibility(View.VISIBLE);
                    initFragment1();
                    button2.setTextColor(getResources().getColor(R.color.white));
                    im2.setVisibility(View.GONE);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button2.setTextColor(getResources().getColor(R.color.holo_blue_bright));
                    im2.setVisibility(View.VISIBLE);
                    initFragment2();
                    button.setTextColor(getResources().getColor(R.color.white));
                    im1.setVisibility(View.GONE);
                }
            });

            initview();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }


        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    void initview() {

        button.setTextColor(getResources().getColor(R.color.holo_blue_bright));
        im1.setVisibility(View.VISIBLE);
        initFragment1();
        button2.setTextColor(getResources().getColor(R.color.white));
        im2.setVisibility(View.GONE);
    }


    @OnClick({R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
//                Toast.makeText(getActivity(), "1111", Toast.LENGTH_SHORT).show();
                initFragment1();
                break;
            case R.id.button2:
//                Toast.makeText(getActivity(), "2222", Toast.LENGTH_SHORT).show();
                initFragment2();
                break;
        }
    }

    SceneIntroduce_Fragment f1;

    //显示第一个fragment
    private void initFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (f1 == null) {
            f1 = new SceneIntroduce_Fragment();
            transaction.add(R.id.main_frame_layouts, f1);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f1);
        //提交事务
        transaction.commit();
    }


    SceneOverview_Fragment f2;

    private void initFragment2() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (f2 == null) {
            f2 = new SceneOverview_Fragment();
            transaction.add(R.id.main_frame_layouts, f2);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f2);
        //提交事务
        transaction.commit();
    }


    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            transaction.hide(f1);
        }
        if (f2 != null) {
            transaction.hide(f2);
        }


    }
}
