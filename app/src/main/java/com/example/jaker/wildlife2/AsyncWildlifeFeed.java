package com.example.jaker.wildlife2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static java.security.AccessController.getContext;


public class AsyncWildlifeFeed extends AsyncTask<Void, Integer, ArrayList<WildlifeFeed>>
{
    private WildlifeFeedResponse delegate = null;
    private ArrayList<WildlifeFeed> wlFeed = null;
    private Context context = null;
    private WildlifeFeed wlf;
    private final String urlString = "http://www.bbc.co.uk/nature/wildlife/by/updated.rss";  //http://www.bbc.co.uk/nature/wildlife/by/updated.rss        //http://www.bbc.co.uk/nature/collections.rss
    public AsyncWildlifeFeed( WildlifeFeedResponse delegate)
    {
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected ArrayList<WildlifeFeed> doInBackground(Void... params)
    {
        try
        {
            wlFeed = new ArrayList<WildlifeFeed>();
            wlf = new WildlifeFeed();
            URL url = new URL(urlString);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(url.openConnection().getInputStream(), "UTF_8");

            boolean insideItem = false;
            WildlifeFeed wlf = new WildlifeFeed();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                    }
                    else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem) {
                            wlf.setTitle(xpp.nextText());
                        }
                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (insideItem) {
                                wlf.setDescription(xpp.nextText());
                            //eventType = xpp.nextTag();
                        }
                    } else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                        if (insideItem) {
                            wlf.setPubDate(xpp.nextText());
                        }
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (insideItem) {
                            wlf.setLink(xpp.nextText());
                        }
                    }

                }
                else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                    insideItem = false;
                    wlFeed.add(wlf);
                    wlf = new WildlifeFeed();
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e)
        {
            Log.e("ERROR", e.getMessage());
            //Toast.makeText(context ,"ERROR" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
        return wlFeed;
    }



    @Override
    protected void onPostExecute(ArrayList<WildlifeFeed> wlf) {delegate.processFinish(wlf);}

}
