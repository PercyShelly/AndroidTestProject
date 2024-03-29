package com.example.qtfnvwang.myapplication;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GuideActivity extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences guide = getSharedPreferences("guide",MODE_PRIVATE);
        SharedPreferences.Editor editor = guide.edit();
        editor.putString("third","key");
        editor.commit();
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position==0)
                    return new GuideFragment1();
                if(position==1)
                    return new GuideFragment2();
                if(position==2)
                    return new GuideFragment3();
                return null;
            }

            @Override
            public int getCount() {
             return 3;
            }
        });

    }
}
