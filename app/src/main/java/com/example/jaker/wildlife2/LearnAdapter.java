package com.example.jaker.wildlife2;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class LearnAdapter extends FragmentPagerAdapter
{
    //private Context context;
    //private ArrayList<LearnObject> learnObjects;
    private ArrayList<Fragment> fragments;

    public LearnAdapter(android.support.v4.app.FragmentManager fm, ArrayList<Fragment> fragments)
    {
        super(fm);
        //this.context = context;
        //this.learnObjects = learnObjects;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /*@Override
    public boolean isViewFromObject(View v, Object obj)
    {
        return v == ((TextView)obj) || v == ((ImageView)obj);
    }*/

    /*@Override
    public Object instantiateItem(ViewGroup container, int i)
    {
        TextView titleTV = new TextView(context);
        titleTV.setText(learnObjects.get(i).getTitle());
        titleTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        ((ViewPager) container).addView(titleTV, 0);
        ImageView imageView = new ImageView(context);
        int imID = context.getResources().getIdentifier(learnObjects.get(i).getImage(), "drawable", context.getPackageName());
        imageView.setImageResource(imID);
        ((ViewPager) container).addView(imageView, 1);
        TextView infoTV = new TextView(context);
        infoTV.setText(learnObjects.get(i).getInfo());
        ((ViewPager) container).addView(infoTV);

    }*/






}
