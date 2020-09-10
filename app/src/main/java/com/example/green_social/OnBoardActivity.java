package com.example.green_social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.green_social.Apdaters.ViewPagerAdapter;

public class OnBoardActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button btn_Left, btn_Right;
    private ViewPagerAdapter pagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        init();

    }

    private void init() {
        viewPager = findViewById(R.id.view_pager);
        btn_Left = findViewById(R.id.btnLeft);
        btn_Right = findViewById(R.id.btnRight);
        dotsLayout = findViewById(R.id.dots_Layout);
        pagerAdapter = new ViewPagerAdapter(this);
        addDots(0);
        viewPager.addOnPageChangeListener(listener);
        viewPager.setAdapter(pagerAdapter);

        btn_Right.setOnClickListener(v->{
            //button text = next => go to next page
            if(btn_Right.getText().toString().equals("Next")){
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            } else{
                //if = finish => start AuthActivity
                startActivity(new Intent(OnBoardActivity.this, AuthActivity.class));
                finish();
            }
        });

        btn_Left.setOnClickListener(v->{
            viewPager.setCurrentItem(viewPager.getCurrentItem()+2);
        });
    }


    //create dots from html code
    private void addDots(int position){
        dotsLayout.removeAllViews();
        dots = new TextView[3];
        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorLightGrey));
            dotsLayout.addView(dots[i]);
        }

        //change dots color
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.colorGrey));
        }
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            //change text to Finish at page 3
            // hide Skip if not at page 1

            if(position == 0){
                btn_Left.setVisibility(View.VISIBLE);
                btn_Left.setEnabled(true);
                btn_Right.setText("Next");
            } else if(position == 1){
                btn_Left.setVisibility(View.GONE);
                btn_Left.setEnabled(false);
                btn_Right.setText("Next");
            } else{
                btn_Left.setVisibility(View.GONE);
                btn_Left.setEnabled(false);
                btn_Right.setText("Finish");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
