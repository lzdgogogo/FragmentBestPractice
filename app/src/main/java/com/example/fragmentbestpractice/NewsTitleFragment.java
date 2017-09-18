package com.example.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘子恒 on 2017/9/15.
 */

public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener
{

    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter adapter;
    private boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList=getNews();
        adapter=new NewsAdapter(activity,R.layout.news_item,newsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_title_frag,container,false);

        newsTitleListView=(ListView)view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
        }else{
            isTwoPane=false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        News news=newsList.get(position);
        if(isTwoPane){
            NewsContentFragment newsContentFragment=(NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refersh(news.getTitle(),news.getContent());
        }else{
            NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
        }
    }

    private List<News> getNews(){
        List<News> newsList=new ArrayList<>();
        News news1=new News();
        news1.setTitle("震惊！国民女神于颖疑似嫁入豪门！");
        news1.setContent("  没啥可说的，国民女神于颖做什么都是对的，就算是放个屁!");
        newsList.add(news1);

        News news2=new News();
        news2.setTitle("天呐！五亿豪奖得主现身北京！身份曝光");
        news2.setContent("  该男子名叫刘子恒，身家现已成为豪门。");
        newsList.add(news2);

        News news3=new News();
        news3.setTitle("千万人见证！有效减小肚子的方法！");
        news3.setContent("  就是拉屎啦！拉的越多小的越多！");
        newsList.add(news3);

        return newsList;
    }
}
