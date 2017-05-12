package com.example.jaker.wildlife2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by jaker on 05/05/2017.
 */

public class AsyncLearn extends AsyncTask<Void, Integer, ArrayList<LearnObject>>
{
    private LearnResponse delegate = null;
    private ArrayList<LearnObject> learnObjects = null;
    private Context context;

    public AsyncLearn(LearnResponse delegate, Context context)
    {
        this.delegate = delegate;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected ArrayList<LearnObject> doInBackground(Void... params)
    {
        JSONArray jsonArray = getJSONArray();
        if (jsonArray != null)
        {
            readJSONArray(jsonArray);
            return learnObjects;
        }
        else
        {
            Log.e("ERROR", "JSON NULL");
        }

        return learnObjects;
    }

    @Override
    protected void onPostExecute(ArrayList<LearnObject> learnObjects) {delegate.processFinish(learnObjects);}


    private JSONArray getJSONArray()
    {
        JSONArray jsonArray = null;
        try {
            String input = "";
            //InputStream inputStream = context.openFileInput("questions.json");
            InputStream fis = context.getResources().openRawResource(R.raw.learn_feed);

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
        learnObjects = new ArrayList<LearnObject>();
        try
        {
            LearnObject learnObject = new LearnObject();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                learnObject = new LearnObject();
                JSONObject ljson = jsonArray.getJSONObject(i);
                learnObject.setTitle(ljson.getString("Title"));
                learnObject.setImage(ljson.getString("Image"));
                learnObject.setInfo(ljson.getString("Info"));
                learnObjects.add(learnObject);
            }
        }
        catch (Exception e)
        {

        }

    }
}
