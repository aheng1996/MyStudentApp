package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mystudentapp.R;
import com.example.mystudentapp.adapter.ImageRecyclerAdapter;
import com.example.mystudentapp.adapter.OnRecyclerItemClickListener;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.base.BaseMeassage;
import com.example.mystudentapp.bean.User;
import com.example.mystudentapp.db.ctrl.CaiLiaoCtrl;
import com.jelly.mango.ImageSelectListener;
import com.jelly.mango.Mango;
import com.jelly.mango.MultiplexImage;

import java.util.ArrayList;
import java.util.List;

public class LookPhotoActivity extends BaseActivity {

    //图片集
    private RecyclerView rv;
    private ImageRecyclerAdapter adapter;
    private List<MultiplexImage> images;
    private User user;
    //类型
    private int type;
    private TextView tvTitle;  //标题
    private String path;
    private String xh;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_photo);
        type=getIntent().getIntExtra("type",0);
        xh=getIntent().getStringExtra("xh");
        user = BaseMeassage.INSTANCE.getUser();
        initView();
        initList();
    }




    private void initList() {

        path = CaiLiaoCtrl.getPicPath(xh, type);
        if (path == null) {
            path = "";
        }
        String[] s = path.split(";;;");

        //遍历
        if (path != null && path.length() > 5) {
            images=new ArrayList<>();
            for (int i = 0; i < s.length; i++) {
                images.add(new MultiplexImage(s[i],MultiplexImage.ImageType.NORMAL));
            }

        }
        initRecycler();
    }

    private void initRecycler() {
        if(images == null || images.size() == 0) return;
        if(adapter == null){

            rv.setLayoutManager(new GridLayoutManager(this,4));
            rv.setItemAnimator(new DefaultItemAnimator());

            adapter = new ImageRecyclerAdapter(this,images);

            adapter.setItemClickListener(new OnRecyclerItemClickListener() {
                @Override
                public void click(View item, int position) {


                    Mango.setImages(images);
                    Mango.setPosition(position);

                    Mango.setImageSelectListener(new ImageSelectListener() {
                        @Override
                        public void select(int index) {
                        }
                    });
                    try {
                        Mango.open(LookPhotoActivity.this);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            rv.setAdapter(adapter);
        }

    }



    private void initView() {
        tvTitle=findViewById(R.id.tv_title);
        rv=findViewById(R.id.recyclerView);

        if (type==0){
            tvTitle.setText("文体材料");
        }
        if (type==1){
            tvTitle.setText("能力材料");
        }
        if (type==2){
            tvTitle.setText("品德材料");
        }


    }
}
