package com.example.jaker.wildlife2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizCompleteFragment extends Fragment {


    public QuizCompleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_complete, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle) {
        super.onViewCreated(view, SavedBundle);
        String score = getArguments().getString("SCORE");
        TextView scoreTV = (TextView) view.findViewById(R.id.finalScoreTV);
        scoreTV.setText(score);
    }

}
