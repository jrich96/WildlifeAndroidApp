package com.example.jaker.wildlife2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Question> retQuestions = null;
    private int qNum = 1;
    private int score = 0;
    private Button wrongAnswerBtn1;
    private Button wrongAnswerBtn2;
    private Button correctAnswerBtn;
    private ImageView image;
    private TextView qTV;
    private String topBarTxtStart;
    private ActionBar ab;
    private String appName;
    private LinearLayout layout;
    private ArrayList<Button> buttons;
    //private Context context;
    public QuizFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //context = getContext();

        return inflater.inflate(R.layout.fragment_quiz, container, false);



    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle)
    {
        super.onViewCreated(view, SavedBundle);
        //wrongAnswerBtn1 = (Button) view.findViewById(R.id.wrongAns1Btn);
        //wrongAnswerBtn2 = (Button) view.findViewById(R.id.wrongAns2Btn);
        //correctAnswerBtn = (Button) view.findViewById(R.id.correctAnsBtn);
        wrongAnswerBtn1 = new Button(getContext());
        wrongAnswerBtn2 = new Button(getContext());
        correctAnswerBtn = new Button(getContext());
        layout = (LinearLayout) view.findViewById(R.id.quizLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.weight = 0.5f;

        //wrongAnswerBtn1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0));
        wrongAnswerBtn1.setLayoutParams(layoutParams);
        wrongAnswerBtn2.setLayoutParams(layoutParams);
        correctAnswerBtn.setLayoutParams(layoutParams);
        wrongAnswerBtn1.setTextSize(15);
        wrongAnswerBtn2.setTextSize(15);
        correctAnswerBtn.setTextSize(15);
        wrongAnswerBtn1.setAllCaps(false);
        wrongAnswerBtn2.setAllCaps(false);
        correctAnswerBtn.setAllCaps(false);



        wrongAnswerBtn1.setOnClickListener(this);
        wrongAnswerBtn2.setOnClickListener(this);
        correctAnswerBtn.setOnClickListener(this);
        image = (ImageView) view.findViewById(R.id.qImage);
        qTV = (TextView) view.findViewById(R.id.questTV) ;
        ab = (ActionBar) ((AppCompatActivity) getActivity()).getSupportActionBar();
        //tb = () view.findViewById(R.id.toolbar);
        appName = getString(R.string.app_name);
        topBarTxtStart = appName + " - Score:";

        buttons = new ArrayList<Button>();
        buttons.add(wrongAnswerBtn1);
        buttons.add(wrongAnswerBtn2);
        buttons.add(correctAnswerBtn);
        Collections.shuffle(buttons);
        for (int i = 0; i < buttons.size(); i++)
        {
            layout.addView(buttons.get(i));
        }



        getQuestions();
    }

    private void getQuestions()
    {

        new AsyncQuiz(new QuizResponse() {
            @Override
            public void processFinish(ArrayList<Question> questions) {
                retQuestions = questions;
                viewQuestion();
            }
        }, getContext()).execute();
    }

    private void viewQuestion()
    {
        if(qNum > retQuestions.size())
        {
            //open another fragment
            ab.setTitle(appName);
            Bundle bundle = new Bundle();
            String stringScore = "Final Score: "+ Integer.toString(score) + "/" + Integer.toString(retQuestions.size());
            bundle.putString("SCORE", stringScore);
            Fragment fragment = new QuizCompleteFragment();
            fragment.setArguments(bundle);
            this.getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            for(int i = 0; i < this.getFragmentManager().getBackStackEntryCount(); ++i) {
                this.getFragmentManager().popBackStack();
            }
            //fm.beginTransaction().replace(R.id.fragment_container, hf).commit();
            return;

        }

        layout.removeView(wrongAnswerBtn1);
        layout.removeView(wrongAnswerBtn2);
        layout.removeView(correctAnswerBtn);
        Collections.shuffle(buttons);
        for (int i = 0; i < buttons.size(); i++)
        {
            layout.addView(buttons.get(i));
        }
        ab.setTitle(topBarTxtStart + score + "/" + Integer.toString(retQuestions.size()) + " Q:" + qNum + "/" + Integer.toString(retQuestions.size()));
        Question q = retQuestions.get(qNum - 1);
        wrongAnswerBtn1.setText(q.getWrongAns1());
        wrongAnswerBtn2.setText(q.getWrongAns2());
        correctAnswerBtn.setText(q.getCorrectAns());
        qTV.setText(q.getQuestionTxt());
        ImageView imageCopy = image;
        int imID = 1;
        imID = getContext().getResources().getIdentifier(q.getImage(), "drawable", getContext().getPackageName());
        image.setImageResource(imID);
        if (image == imageCopy)
        {
            Log.e("THE", "SAME");
        }
    }


    @Override
    public void onClick(View view)
    {
        /*switch (view)
        {
            case wrongAnswerBtn1:
                Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();

                break;
            case R.id.wrongAns2Btn:
                Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();

                break;
            case R.id.correctAnsBtn:
                score++;
                Toast.makeText(getContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();

                break;
        }
        qNum++;
        viewQuestion();*/



        if (view == wrongAnswerBtn1 || view == wrongAnswerBtn2)
        {
            Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
        }

        else if (view == correctAnswerBtn)
        {
            score++;
            Toast.makeText(getContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
        }

        qNum++;
        viewQuestion();

    }




}
