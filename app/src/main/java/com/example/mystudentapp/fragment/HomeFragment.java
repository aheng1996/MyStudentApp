package com.example.mystudentapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.activity.GroupEvaluateActivity;
import com.example.mystudentapp.activity.InfoAddActivity;
import com.example.mystudentapp.activity.NoticeInfoActivity;
import com.example.mystudentapp.activity.SportsRankActivity;
import com.example.mystudentapp.activity.StudentEvaluateActivity;
import com.example.mystudentapp.activity.StudyRankActivity;
import com.example.mystudentapp.activity.TeacherEvaluateActivity;
import com.example.mystudentapp.adapter.MyAdapter;
import com.example.mystudentapp.adapter.NoticeAdapter;
import com.example.mystudentapp.base.BaseFragment;
import com.example.mystudentapp.base.BaseMeassage;
import com.example.mystudentapp.base.GlideImageLoader;
import com.example.mystudentapp.bean.Icon;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.bean.GongGao;
import com.example.mystudentapp.db.ctrl.GongGaoCtrl;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    protected String[] titles;
    private Banner banner;
    private ArrayList<Integer> imagePath;
    private ArrayList<Icon> mData = null;

    private Context mContext;
    private GridView grid_photo;
    private BaseAdapter iconAdapter = null;

    private ListView lvNotice;
    private List<GongGao> list;
    private NoticeAdapter adapter;
    private boolean isXiaoZu; //是否小组

    private User user;
    private int type;

    public HomeFragment() {

    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, "");
        args.putString(ARG_PARAM2, "");
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initImage();
        user = BaseMeassage.INSTANCE.getUser();

        initView(inflate);
        initBanner();

        return inflate;
    }

    private void initBanner() {

        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        //资源文件
        imagePath = new ArrayList<>();
        imagePath.add(R.mipmap.loginbg);
        imagePath.add(R.mipmap.imp2);

        banner.setImages(imagePath);
        //banner设置方法全部调用完毕时最后调用
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）

        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void initView(View inflate) {
        //公告
        lvNotice=inflate.findViewById(R.id.lv_notice);
        adapter=new NoticeAdapter(getActivity());
        lvNotice.setAdapter(adapter);
        lvNotice.setOnItemClickListener(this);

        banner = inflate.findViewById(R.id.banner);

        grid_photo = (GridView) inflate.findViewById(R.id.grid_photo);

        iconAdapter = new MyAdapter<Icon>(mData, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
            }


        };
        grid_photo.setAdapter(iconAdapter);
        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                type =user.getType();   //         * 1 管理员
//     * 2 老师
//     * 3 学生

                switch (i) {


                    case 0:
                        if (type==3){
                            gotoActivity(StudentEvaluateActivity.class);

                        }else {
                            showToast("您不是学生，暂无权限");
                        }


                        break;
                    case 1:
                        if (user.isXiaoZu()){
                            gotoActivity(GroupEvaluateActivity.class);

                        }else {
                            showToast("您不是小组成员，暂无权限");
                        }
                        break;
                    case 2:
                        if (type==2){
                            gotoActivity(TeacherEvaluateActivity.class);


                        }else {
                            showToast("您不是教师，暂无权限");
                        }

                        break;
                    case 3:
                        if (type==3){
                            gotoActivity(InfoAddActivity.class);

                        }else {
                            showToast("您不是学生，暂无权限");
                        }



                        break;
                    case 4:
                        gotoActivity(StudyRankActivity.class);
                        break;
                    case 5:
                        gotoActivity(SportsRankActivity.class);

                        break;


                }
            }
        });






    }

    private void initImage() {
        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.mipmap.iv_icon_1, "学生互评"));
        mData.add(new Icon(R.mipmap.iv_icon_2, "小组互评"));
        mData.add(new Icon(R.mipmap.iv_icon_3, "教师评分"));
        mData.add(new Icon(R.mipmap.iv_icon_4, "材料补充"));
        mData.add(new Icon(R.mipmap.iv_icon_5, "学习成绩"));
        mData.add(new Icon(R.mipmap.iv_icon_6, "体测成绩"));




    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //走逻辑跳转，进入公告详情界面

        Intent intent = new Intent(getActivity(), NoticeInfoActivity.class);
        intent.putExtra("id", list.get(i).getId()+"");
        intent.putExtra("title",list.get(i).getTitle());
        intent.putExtra("message",list.get(i).getMessage());
        startActivity(intent);

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();

    }
    private void initData() {

        list=new ArrayList<>();
        list= GongGaoCtrl.select();
        adapter.setList(list);

    }
}
