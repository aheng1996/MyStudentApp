package com.example.mystudentapp.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.activity.ChangePasswordActivity;
import com.example.mystudentapp.activity.LoginActivity;
import com.example.mystudentapp.base.ActivityManager;
import com.example.mystudentapp.base.BaseFragment;
import com.example.mystudentapp.base.BaseMeassage;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.bean.Teacher;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;


public class MineFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout llChangPwd;
    private LinearLayout llGoOut;

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ImageView ivHeadPic;

    private TextView tvName;
    private TextView tvBianhao;
    private User user;



    public MineFragment() {
        // Required empty public constructor
    }


    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, "");
        args.putString(ARG_PARAM2, "");
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        user = BaseMeassage.INSTANCE.getUser();

        initView(inflate);

        initListener();
        return inflate;
    }

    private void initListener() {
        llChangPwd.setOnClickListener(this);
        llGoOut.setOnClickListener(this);
        ivHeadPic.setOnClickListener(this);
    }

    private void initView(View inflate) {
        llChangPwd=inflate.findViewById(R.id.ll_chang_pwd);
        llGoOut=inflate.findViewById(R.id.ll_go_out);
        ivHeadPic=inflate.findViewById(R.id.iv_head_pic);

        tvBianhao=inflate.findViewById(R.id.tv_user_name);
        tvName=inflate.findViewById(R.id.tv_name);
        tvBianhao.setText("账号："+user.getBianHao());
        tvName.setText(user.getName());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_chang_pwd:
                gotoActivity(ChangePasswordActivity.class);
                break;
            case R.id.ll_go_out:
                showDialog();
                break;
            case R.id.iv_head_pic:
                showBottomDialog();
                break;
        }
    }

    private void showBottomDialog() {
        String[] stringItems = {"拍照", "从手机相册选择"};
       final ActionSheetDialog dialog = new ActionSheetDialog(getActivity(), stringItems, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //权限判断

                        dialog.dismiss();
                        break;
                    case 1:

                        //权限判断

                        dialog.dismiss();
                        break;



                }
            }
        });
    }

    private void showDialog() {
        final NormalDialog dialog = new NormalDialog(getActivity());
        dialog.content("是否退出登录?")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        //注销登录，清空 SharedPreferences
//                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.clear();
//                        editor.commit();
//                        String token = sharedPreferences.getString("name", "");
////            CookieSyncManager.createInstance(getActivity());
////            CookieManager.getInstance().removeAllCookie();
//
//                        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
//                        ((CookieJarImpl) cookieJar).getCookieStore().removeAll();

                        gotoActivity(LoginActivity.class);
                        //清楚其他的activity
                        ActivityManager.INSTANCE.clearOtherActivity();
                        dialog.dismiss();

                    }
                });
    }
}
