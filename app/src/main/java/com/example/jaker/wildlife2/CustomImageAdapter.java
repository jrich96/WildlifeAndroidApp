package com.example.jaker.wildlife2;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by jaker on 04/05/2017.
 */

public class CustomImageAdapter extends PagerAdapter
{
    private Context context;
    private int[] sliderImages = new int[]{
            R.drawable.dragonfly, R.drawable.sloth2, R.drawable.leopard,
            R.drawable.panda, R.drawable.penguin, R.drawable.viper,
            R.drawable.sloth1,

    };

    public CustomImageAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderImages.length;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(context);
        //mImageView.setScaleType(ImageView.ScaleType.);
        mImageView.setImageResource(sliderImages[i]);

        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }

}
