package com.example.wub.materialdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by wub on 2017/6/19.
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{

    /**
     * Context
     */
    private Context context;
    /**
     * 数据集合
     */
    private List<Fruit> mFruitList;

    /**
     * ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder{
        //控件
        CardView cardView;
        ImageView ivFruit;
        TextView tvFruitName;
        //构造函数
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            ivFruit = (ImageView) itemView.findViewById(R.id.ivFruit);
            tvFruitName = (TextView) itemView.findViewById(R.id.tvFruitName);
        }
    }

    /**
     * 构造函数
     * @param fruitList
     */
    public FruitAdapter(List<Fruit> fruitList){
        //数据集合
        mFruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_fruit,parent,false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Log.d("position=======",position+"");
                Fruit fruit = mFruitList.get(position);
                Intent intent = new Intent(context,FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                //跳转水果详情页
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.tvFruitName.setText(fruit.getName());
        Glide.with(context).load(fruit.getImageId()).into(holder.ivFruit);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
