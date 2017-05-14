package com.example.jaker.wildlife2;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class WildlifeFeedSetupFragment extends Fragment {


    public WildlifeFeedSetupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wildlife_feed_setup, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle)
    {
        super.onViewCreated(view, SavedBundle);

        WildlifeFeedFragment updateFragment = new WildlifeFeedFragment();
        Bundle updateBundle = new Bundle();
        updateBundle.putString("URL", "http://www.bbc.co.uk/nature/wildlife/by/updated.rss"); //"http://www.bbc.co.uk/nature/wildlife/by/updated.rss"
        updateFragment.setArguments(updateBundle);

        WildlifeFeedFragment latestFragment = new WildlifeFeedFragment();
        Bundle latestBundle = new Bundle();
        latestBundle.putString("URL", "http://www.bbc.co.uk/nature/wildlife/by/latest.rss");
        latestFragment.setArguments(latestBundle);

        WildlifeFeedFragment collectionsFragment = new WildlifeFeedFragment();
        Bundle collectionsBundle = new Bundle();
        collectionsBundle.putString("URL", "http://www.bbc.co.uk/nature/collections.rss");
        collectionsFragment.setArguments(collectionsBundle);

        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(updateFragment);
        fragments.add(latestFragment);
        fragments.add(collectionsFragment);

        setupAdapter(fragments);
    }

    public void setupAdapter(ArrayList<Fragment> fragments)
    {
        WildlifeFeedPagerAdapter adapter = new WildlifeFeedPagerAdapter(getChildFragmentManager(), fragments);
        ViewPager vp = (ViewPager) getView().findViewById(R.id.wlfViewPager);
        TabLayout tabLayout = (TabLayout) getView().findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vp, true);
        vp.setAdapter(adapter);
    }

}
