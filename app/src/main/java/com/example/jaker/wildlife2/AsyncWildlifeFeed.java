package com.example.jaker.wildlife2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import static java.security.AccessController.getContext;


public class AsyncWildlifeFeed extends AsyncTask<Void, Integer, ArrayList<WildlifeFeed>>
{
    private WildlifeFeedResponse delegate = null;
    private ArrayList<WildlifeFeed> wlFeed = null;
    private Context context = null;
    private WildlifeFeed wlf;
    private String urlString;  //http://www.bbc.co.uk/nature/wildlife/by/updated.rss        //http://www.bbc.co.uk/nature/collections.rss
    private ProgressBar progressBar;
    public AsyncWildlifeFeed(WildlifeFeedResponse delegate, String urlString, ProgressBar progressBar)
    {
        this.delegate = delegate;
        this.urlString = urlString;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
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
            boolean inHref = false;
            WildlifeFeed wlf = new WildlifeFeed();
            int eventType = xpp.getEventType();
            String desc = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (xpp.getName() != null) {
                    if (eventType == XmlPullParser.START_TAG) {

                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                if (xpp.next() == XmlPullParser.TEXT && eventType == XmlPullParser.START_TAG) {
                                    wlf.setTitle(xpp.getText());
                                }
                            }
                        } else if (xpp.getName().equalsIgnoreCase("description")) {
                            if (insideItem) {
                                if (xpp.next() == XmlPullParser.TEXT) {
                                    desc += xpp.getText();
                                }
                            }
                        } else if (xpp.getName().equalsIgnoreCase("a") || xpp.getName().equalsIgnoreCase("b")) {
                            if (insideItem) {
                                if (xpp.next() == XmlPullParser.TEXT) {
                                    desc += xpp.getText();
                                    inHref = true;
                                }
                            }
                        } else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                            if (insideItem) {
                                if (xpp.next() == XmlPullParser.TEXT) {
                                    wlf.setPubDate(xpp.getText());
                                }
                            }
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                if (xpp.next() == XmlPullParser.TEXT) {
                                    wlf.setLink(xpp.getText());
                                }
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("media:thumbnail")) {
                            if (insideItem) {
                                wlf.setWidth(Integer.parseInt(xpp.getAttributeValue(null, "width")));
                                wlf.setHeight(Integer.parseInt(xpp.getAttributeValue(null, "height")));

                                    wlf.setImage(getImage(xpp.getAttributeValue(null, "url")));
                            }
                        }



                    }

                    else if (inHref)
                    {
                        if (xpp.next() == XmlPullParser.TEXT)
                        {
                            desc += xpp.getText();
                            inHref = false;
                        }

                    }

                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("br"))
                    {
                        inHref = true;
                    }

                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                        wlf.setDescription(desc);
                        desc = "";
                        wlFeed.add(wlf);
                        wlf = new WildlifeFeed();
                    }

                }

                eventType = xpp.next();
            }

        }
        catch (Exception e)
        {
            Log.e("RSS ERROR", e.getMessage());
            //Toast.makeText(context ,"ERROR" + e.getMessage(), Toast.LENGTH_LONG).show();

        }


        return wlFeed;
    }


    public Drawable getImage(String address)
    {
        try
        {
            URL url = new URL(address);
            InputStream content = (InputStream)url.getContent();
            return Drawable.createFromStream(content, "src");
        }

        catch (Exception e)
        {

        }
        return null;
    }



    @Override
    protected void onPostExecute(ArrayList<WildlifeFeed> wlf) {
        progressBar.setVisibility(View.GONE);
        delegate.processFinish(wlf);}

}
