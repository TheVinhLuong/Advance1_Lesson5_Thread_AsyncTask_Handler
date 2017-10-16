package com.example.android.recyclerviewexample.utils;

import android.databinding.BindingAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.recyclerviewexample.R;

/**
 * Created by le.quang.dao on 20/03/2017.
 */

public final class BindingUtils {

    private BindingUtils() {
    }

    
    @BindingAdapter({ "recyclerAdapter" })
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
            RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
    
    @BindingAdapter({"viewPagerAdapter"})
    public static void setViewPagerAdapter(ViewPager viewPager, PagerAdapter adapter){
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({"loadImageFromUrl"})
    public static void loadImageFromUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher))
                .into(imageView);
    }
}
