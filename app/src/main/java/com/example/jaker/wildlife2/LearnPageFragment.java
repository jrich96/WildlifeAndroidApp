package com.example.jaker.wildlife2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class LearnPageFragment extends Fragment {

    private String title;
    private String info;
    private String image;


    public LearnPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_learn_page, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle)
    {
        super.onViewCreated(view, SavedBundle);

        if(getArguments() != null)
        {
            title = getArguments().getString("TITLE");
            image = getArguments().getString("IMAGE");
            info = getArguments().getString("INFO");
        }

        TextView titleTV = (TextView) view.findViewById(R.id.learnTitleTV);
        titleTV.setText(title);
        ImageView imageView = (ImageView) view.findViewById(R.id.learnIV);
        int imID = getContext().getResources().getIdentifier(image, "drawable", getContext().getPackageName());
        imageView.setImageResource(imID);
        TextView infoTV = (TextView) view.findViewById(R.id.learnInfoTV);
        infoTV.setText(info);


        //setListAdapter(new ArrayAdapter<LearnObject>(getContext(), android.R.layout.simple_list_item_1, ));
    }


}
