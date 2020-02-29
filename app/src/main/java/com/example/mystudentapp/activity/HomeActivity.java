package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.example.mystudentapp.R;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.fragment.HomeFragment;
import com.example.mystudentapp.fragment.MineFragment;
import com.example.mystudentapp.fragment.ResultFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private FrameLayout mFlContent;
    private BottomBarLayout mBottomBarLayout;
    private RotateAnimation mRotateAnimation;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        //首页
        HomeFragment homeFragment =new HomeFragment();
        mFragmentList.add(homeFragment);

        //测评结果
        ResultFragment resultFragment =new ResultFragment();
        mFragmentList.add(resultFragment);

        //我的
        MineFragment myFragment =new MineFragment();
        mFragmentList.add(myFragment);

        changeFragment(0); //默认显示第一页


    }

    private void initView() {
        mFlContent = (FrameLayout) findViewById(R.id.fl_content);
        mBottomBarLayout = (BottomBarLayout) findViewById(R.id.bbl);
    }

    private void changeFragment(int currentPosition) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, mFragmentList.get(currentPosition));
        transaction.commit();
    }

    private void initListener() {
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final BottomBarItem bottomBarItem, int previousPosition, final int currentPosition) {

                changeFragment(currentPosition);


                if (currentPosition == 0) {
                    //如果是第一个，即首页
                    if (previousPosition == currentPosition) {
                        //如果是在原来位置上点击,更换首页图标并播放旋转动画
                        if (mRotateAnimation != null && !mRotateAnimation.hasEnded()){
                            //如果当前动画正在执行
                            return;
                        }

                        bottomBarItem.setSelectedIconResourceId(R.mipmap.tab_loading);//更换成加载图标 setResId

                        //播放旋转动画
                        if (mRotateAnimation == null) {
                            mRotateAnimation = new RotateAnimation(0, 360,
                                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                                    0.5f);
                            mRotateAnimation.setDuration(800);
                            mRotateAnimation.setRepeatCount(-1);

                        }
                        ImageView bottomImageView = bottomBarItem.getImageView();
                        bottomImageView.setAnimation(mRotateAnimation);
                        bottomImageView.startAnimation(mRotateAnimation);//播放旋转动画

                        //模拟数据刷新完毕
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                boolean tabNotChanged = mBottomBarLayout.getCurrentItem() == currentPosition; //是否还停留在当前页签
                                bottomBarItem.setSelectedIconResourceId(R.mipmap.tab_home_selected);//更换成首页原来选中图标
                                cancelTabLoading(bottomBarItem);

                                showToast("刷新完毕");
                            }
                        }, 2000);
                        return;
                    }
                }


                //如果点击了其他条目
                BottomBarItem bottomItem = mBottomBarLayout.getBottomItem(0);
                bottomItem.setSelectedIconResourceId(R.mipmap.tab_home_selected);//更换为原来的图标
                cancelTabLoading(bottomItem);//停止旋转动画
            }
        });

//        mBottomBarLayout.setUnread(0, 20);//设置第一个页签的未读数为20
        mBottomBarLayout.setUnread(1, 0);//设置第二个页签的未读数
//        mBottomBarLayout.showNotify(2);//设置第三个页签显示提示的小红点
////        mBottomBarLayout.setMsg(3, "NEW");//设置第四个页签显示NEW提示文字
    }

    /**
     * 停止首页页签的旋转动画
     */
    private void cancelTabLoading(BottomBarItem bottomItem) {
        Animation animation = bottomItem.getImageView().getAnimation();
        if (animation != null) {
            animation.cancel();
        }
    }
}
