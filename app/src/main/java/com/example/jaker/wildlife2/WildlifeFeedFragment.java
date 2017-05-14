package com.example.jaker.wildlife2;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WildlifeFeedFragment extends Fragment {

    private ArrayList<WildlifeFeed> wlFeed = null;
    private View view;
    private ListView lv;
    private ProgressBar progressBar;

    public WildlifeFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wildlife_feed, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle SavedBundle) {
        super.onViewCreated(view, SavedBundle);
        lv = (ListView) getView().findViewById(R.id.wlfList);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
        String url = getArguments().getString("URL");
        //progress = new ProgressDialog(getContext());
        //progress.setTitle("Loading Feed");
        //progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        //progress.show();
        getFeed(url);

    }


    private void getFeed(String url)
    {
        new AsyncWildlifeFeed(new WildlifeFeedResponse() {
            @Override
            public void processFinish(ArrayList<WildlifeFeed> wlf) {
                //progress.dismiss();
                wlFeed = wlf;
                viewFeed();
            }
        },url, progressBar).execute();
    }

    private void viewFeed()
    {

        WildlifeFeedAdapter wlfAdapter = new WildlifeFeedAdapter(getContext(), R.layout.fragment_wildlife_feed, wlFeed);
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
