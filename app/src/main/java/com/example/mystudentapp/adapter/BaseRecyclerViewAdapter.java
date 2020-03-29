package com.example.mystudentapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mystudentapp.utils.Utils;


import java.util.List;

/**
 * Created by qiaoshi on 2017/4/25.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {
    protected Context context;
    protected int itemWidth;
    protected List<T> data;
    protected int itemLayoutId;
    protected OnItemClickListenter onItemClickListenter;

    public BaseRecyclerViewAdapter(Context context, List<T> data, int itemLayoutId, int column) {
        this.context = context;
        this.itemWidth = column > 0 ? Utils.screenWidth() / column : Utils.screenWidth();
        this.data = data;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(itemLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListenter {
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListenter onItemClickListener){
        this.onItemClickListenter = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
        public void setText(int viewId, String text) {
            TextView textView = itemView.findViewById(viewId);
            textView.setText(text);
        }
        public ImageView setImageUrl(int viewId, String url) {
            ImageView imageView = itemView.findViewById(viewId);
            Glide.with(context)
                    .load(url)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }

                    })
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            return false;
//                        }
//                    })
                    .into(imageView);
            return imageView;
        }
        public ImageView setImageResource(int viewId, int res) {
            ImageView imageView = itemView.findViewById(viewId);
            imageView.setImageResource(res);
            return imageView;
        }
        public void setImageDrawable(int viewId, Drawable drawable) {
            ImageView imageView = itemView.findViewById(viewId);
            imageView.setImageDrawable(drawable);
        }
        public View getViewById(int viewId) {
            return itemView.findViewById(viewId);
        }
    }
}
