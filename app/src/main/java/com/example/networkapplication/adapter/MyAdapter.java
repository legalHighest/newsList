package com.example.networkapplication.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networkapplication.R;
import com.example.networkapplication.entity.News;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<News> DataList;


    public List<News> getDataList() {
        return DataList;
    }

    public void setDataList(List<News> dataList) {
        DataList = dataList;
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



    // 构造函数，接收数据源
    public MyAdapter(List<News> dataList) {
        this.DataList = dataList;
    }

    // 创建ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    // 绑定数据到ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.image.setTag(position);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        });
        News item = DataList.get(position);
        holder.bind(item);
    }

    // 返回数据项的数量
    @Override
    public int getItemCount() {
        return DataList.size();
    }


    public void addNews(List<News> newsList) {
        DataList.addAll(newsList);
        notifyItemInserted(DataList.size() - 1);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateNews(List<News> newsList) {
        newsList.addAll(DataList);
        DataList.removeAll(DataList);
        DataList.addAll(newsList);
        notifyDataSetChanged();
    }

    // ViewHolder类
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            image = itemView.findViewById(R.id.news_image);
        }

        // 将数据绑定到视图
        public void bind(News news) {
            title.setText(news.getTitle());
            if (!Objects.equals(news.getThumbnail_pic_s(), "")) {
                Picasso.get().load(news.getThumbnail_pic_s()).into(image);
            }
        }
    }
}