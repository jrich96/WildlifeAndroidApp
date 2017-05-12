package com.example.jaker.wildlife2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by jaker on 04/05/2017.
 */

public class AsyncQuiz extends AsyncTask<Void, Integer, ArrayList<Question>>
{
    private QuizResponse delegate = null;
    private ArrayList<Question> questions = null;
    private Context context;
    public AsyncQuiz(QuizResponse delegate, Context context)
    {
        this.delegate = delegate;
        this.context = context;
    }
    @Override
    protected void onPreExecute() {

    }

    @Override
    protected ArrayList<Question> doInBackground(Void... params)
    {
        JSONArray jsonArray = getJSONArray();
        if (jsonArray != null)
        {
            readJSONArray(jsonArray);
            return questions;
        }
        else
        {
            Log.e("ERROR", "JSON NULL");
        }


        return questions;
    }

    private JSONArray getJSONArray()
    {
        JSONArray jsonArray = null;
        try {
            String input = "";
            //InputStream inputStream = context.openFileInput("questions.json");
            InputStream fis = context.getResources().openRawResource(R.raw.questions);

            if (fis != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                bufferedReader.close();

                inputStreamReader.close();
                fis.close();
                input = stringBuilder.toString();
                jsonArray = new JSONArray(input);
                return jsonArray;
            }
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());

        }
        return jsonArray;
    }

    private void readJSONArray(JSONArray jsonArray)
    {
        questions = new ArrayList<Question>();
        try
        {
            Question q = new Question();
            //JSONArray jsonArray = json.getJSONArray("Questions");
            for (int i = 0; i < jsonArray.length(); i++)
            {
                q = new Question();
                JSONObject qjson = jsonArray.getJSONObject(i);
                q.setWrongAns1(qjson.getString("WrongAnswer1"));
                q.setWrongAns2(qjson.getString("WrongAnswer2"));
                q.setCorrectAns(qjson.getString("CorrectAnswer"));
                q.setQuestionTxt(qjson.getString("Questiontxt"));
                q.setImage(qjson.getString("Image"));
                questions.add(q);
            }
        }
        catch (Exception e)
        {

        }



    }


    @Override
protected void onPostExecute(ArrayList<Question> questions) {delegate.processFinish(questions);}
}
