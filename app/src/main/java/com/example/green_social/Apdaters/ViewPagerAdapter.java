package com.example.green_social.Apdaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.green_social.R;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    private int image[] = {
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3
    };

    private String titles[] = {
            "Learn",
            "Create",
            "Enjoy"
    };

    private String descs[] = {
            "lorem ipsum dolor sit amet consectetur adipiscing elit. aliquam tincidunt elementum sem non luctus.",
            "lorem ipsum dolor sit amet consectetur adipiscing elit. aliquam tincidunt elementum sem non luctus.",
            "lorem ipsum dolor sit amet consectetur adipiscing elit. aliquam tincidunt elementum sem non luctus."
    };

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
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.view_pager, container, false);

        //init views
        ImageView imageView = v.findViewById(R.id.imgViewPager);
        TextView txtTitleViewPager = v.findViewById(R.id.txtTitleViewPager);
        TextView txtDescViewPager = v.findViewById(R.id.txtDescViewPager);

        imageView.setImageResource(image[position]);
        txtTitleViewPager.setText(titles[position]);
        txtDescViewPager.setText(descs[position]);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
