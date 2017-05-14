package com.example.jaker.wildlife2;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jaker on 03/05/2017.
 */

public class WildlifeFeedAdapter extends ArrayAdapter
{
    private ArrayList<WildlifeFeed> wlf;
    public WildlifeFeedAdapter(Context context, int resource, ArrayList<WildlifeFeed> wlf)
    {
        super(context, resource, wlf);
        this.wlf = wlf;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.fragment_wildlife_feed, null);
        }

        WildlifeFeed wlFeed = (WildlifeFeed) getItem(position);

        String title = wlFeed.getTitle();
        String desc = wlFeed.getDescription();
        String link = wlFeed.getLink();
        String pubdate = wlFeed.getPubDate();
        Drawable drawable = wlFeed.getImage();
        int width = wlFeed.getWidth();
        int height = wlFeed.getHeight();

        if (wlFeed != null) {
            ImageView imageView = (ImageView) v.findViewById(R.id.wlfIV);
            TextView titleView = (TextView) v.findViewById(R.id.titleTV);
            TextView descView = (TextView) v.findViewById(R.id.descTV);
            TextView linkView = (TextView) v.findViewById(R.id.linkTV);
            TextView pubdateView = (TextView) v.findViewById(R.id.dateTV);

            imageView.setVisibility(View.VISIBLE);
            imageView.setImageDrawable(drawable);
            //imageView.setScaleX(width);
            //imageView.setScaleY(height);
            imageView.getLayoutParams().width = width * 4;
            imageView.getLayoutParams().height = height * 4;

            titleView.setVisibility(View.VISIBLE);
            titleView.setText(title);
            titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            titleView.setTypeface(null, Typeface.BOLD);
            descView.setVisibility(View.VISIBLE);
            descView.setText(desc);
            descView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            linkView.setVisibility(View.VISIBLE);
            linkView.setText("Select to visit: " + link);
            linkView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            pubdateView.setVisibility(View.VISIBLE);
            pubdateView.setText("Date published: " + pubdate);
            pubdateView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        }

        return v;
    }



    @Override
    public int getCount() {
        return super.getCount();
    }
}
