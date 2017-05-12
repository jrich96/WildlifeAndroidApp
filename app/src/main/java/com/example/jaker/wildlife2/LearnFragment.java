package com.example.jaker.wildlife2;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {


    public LearnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learn, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle)
    {
        super.onViewCreated(view, SavedBundle);
        new AsyncLearn(new LearnResponse() {
            @Override
            public void processFinish(ArrayList<LearnObject> learnObjects)
            {
                //ArrayList<LearnObject> learnObjectsRet = learnObjects;
                ArrayList<Fragment> fragments = new ArrayList<Fragment>();
                for (int i = 0; i < learnObjects.size(); i++)
                {
                    LearnPageFragment lpf = new LearnPageFragment();

                    Bundle values = new Bundle();
                    values.putString("TITLE", learnObjects.get(i).getTitle());
                    values.putString("IMAGE", learnObjects.get(i).getImage());
                    values.putString("INFO", learnObjects.get(i).getInfo());
                    lpf.setArguments(values);
                    fragments.add(lpf);
                    //COME BACK
                }

                setupAdapter(fragments);

                //set up with adapter
            }
        }, getContext()).execute();
    }

    public void setupAdapter(ArrayList<Fragment> fragments)
    {
        //FragmentManager fm = getFragmentManager();
        LearnAdapter adapter = new LearnAdapter(getChildFragmentManager(), fragments);
        ViewPager vp = (ViewPager) getView().findViewById(R.id.learnViewPager);
        TabLayout tabLayout = (TabLayout) getView().findViewById(R.id.tabDotsLearn);
        tabLayout.setupWithViewPager(vp, true);
        vp.setAdapter(adapter);
    }






}
