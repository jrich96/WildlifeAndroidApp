package com.example.jaker.wildlife2;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WildlifeFeedFragment extends Fragment {

    private ArrayList<WildlifeFeed> wlFeed = null;
    private View view;
    public WildlifeFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getFeed();
        //this.view = inflater.inflate(R.layout.list_views, container, false);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wildlife_feed, container, false);
    }

    private void getFeed()
    {
        new AsyncWildlifeFeed(new WildlifeFeedResponse() {
            @Override
            public void processFinish(ArrayList<WildlifeFeed> wlf) {
                wlFeed = wlf;
                viewFeed();
            }
        }).execute();
    }

    private void viewFeed()
    {

        WildlifeFeedAdapter wlfAdapter = new WildlifeFeedAdapter(getContext(), R.layout.fragment_wildlife_feed, wlFeed);
        final ListView lv = (ListView) getView().findViewById(R.id.wlfList);
        lv.setAdapter(wlfAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WildlifeFeed wlf = (WildlifeFeed) lv.getItemAtPosition(i);
                String link = wlf.getLink();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(browserIntent);
            }
        });
    }
}
