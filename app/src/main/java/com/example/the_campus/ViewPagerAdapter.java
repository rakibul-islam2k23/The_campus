package com.example.the_campus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;

public class ViewPagerAdapter extends PagerAdapter {

    private final Context context;
    private final int[] animations;
    private final String[] titles;
    private final String[] descriptions;

    public ViewPagerAdapter(Context context, int[] animations, String[] titles, String[] descriptions) {
        this.context = context;
        this.animations = animations;
        this.titles = titles;
        this.descriptions = descriptions;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager_layout,container,false);

        LottieAnimationView animationView = (LottieAnimationView) view.findViewById(R.id.lottieLayout);
        TextView title = (TextView) view.findViewById(R.id.heading);
        TextView description = (TextView) view.findViewById(R.id.details);

        animationView.setAnimation(animations[position]);
        title.setText(titles[position]);
        description.setText(descriptions[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
