package com.example.fragmentbestpractice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 刘子恒 on 2017/9/15.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    private int resourceId;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects){
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News news=getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView==null){             //convertView参数是一个用于缓存之前加载好的布局，以便之后进行重用
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.newTitleText=(TextView) view.findViewById(R.id.news_title);
            view.setTag(viewHolder);

        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.newTitleText.setText(news.getTitle());

        return view;
    }

    //用于对控件的实例进行缓存
    class ViewHolder{
        TextView newTitleText;
    }
}
