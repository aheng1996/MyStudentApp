package com.example.mystudentapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mystudentapp.R;
import com.example.mystudentapp.adapter.BaseRecyclerViewAdapter;
import com.example.mystudentapp.base.BaseActivity;
import com.example.mystudentapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.example.mystudentapp.activity.MutiImageSelectorActivity.MAX_SELECT_NUM;
import static com.example.mystudentapp.activity.MutiImageSelectorActivity.SELECTED_DATA;
import static com.example.mystudentapp.activity.MutiImageSelectorActivity.SELECTED_FINISH;

public class SelectPhotoActivity extends BaseActivity {

    private Context context;
    private RecyclerView recyclerview;
    private PhotoWallAdapter photoWallAdapter;
    private ArrayList<String> selectedData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        Utils.checkApplicationFolder();
        initView();
    }

    private void initView() {
        context = this;
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new GridLayoutManager(context, 3));
        photoWallAdapter = new PhotoWallAdapter(context, selectedData, R.layout.item_photo_wall);
        recyclerview.setAdapter(photoWallAdapter);
        photoWallAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListenter() {
            @Override
            public void OnItemClick(View view, int position) {
                if("add".equals(selectedData.get(position))){
                    toMutiImageSelectorActivity();
                }
            }
        });
    }

    public void toMutiImageSelectorActivity(){
        Intent intent = new Intent(SelectPhotoActivity.this, MutiImageSelectorActivity.class);
        intent.putExtra(MAX_SELECT_NUM,25);
        intent.putStringArrayListExtra(SELECTED_DATA, selectedData);
        startActivityForResult(intent,SELECTED_FINISH);
    }


    class PhotoWallAdapter extends BaseRecyclerViewAdapter<String> {
        public PhotoWallAdapter(Context context, List<String> data, int itemLayoutId) {
            super(context, data, itemLayoutId, 3);
            data.add("add");
            this.data = data;
        }
        @Override
        public void onBindViewHolder(BaseRecyclerViewAdapter.ViewHolder holder, final int position) {
            RelativeLayout addLayout = (RelativeLayout)holder.getViewById(R.id.add_layout);
            ImageView image = holder.setImageUrl(R.id.image, data.get(position));
            if(data.get(position).equals("add")) {
                addLayout.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) addLayout.getLayoutParams();
                params.width = params.height = itemWidth;
            }else{
                addLayout.setVisibility(View.GONE);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) image.getLayoutParams();
                params.width = params.height = itemWidth;
            }
            final int index = position;
            addLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListenter!=null){
                        onItemClickListenter.OnItemClick(v, index);
                    }
                }
            });
        }
    }

}
