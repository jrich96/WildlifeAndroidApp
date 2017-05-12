package com.example.jaker.wildlife2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoPlayerFragment extends Fragment {


    public VideoPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_player, container, false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle)
    {
        //ActionBar ab = (ActionBar) ((AppCompatActivity) getActivity()).getSupportActionBar();
        //ab.setHomeButtonEnabled(true);
        //ab.setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        MenuItem back = (MenuItem) toolbar.getMenu().findItem(R.id.action_back);
        if (back != null)
        {
            back.setVisible(true);
        }
        /*MenuItem edit = (MenuItem)toolbar.getMenu().findItem(R.id.action_edit);
        if (edit != null)
        {
            edit.setVisible(false);
        }


        MenuItem delete = (MenuItem) toolbar.getMenu().findItem(R.id.action_delete);
        if (delete != null)
        {
            delete.setVisible(false);
        }*/

        String url = "";
        if(getArguments() != null)
        {
            url = getArguments().getString("URL");
            WebView webView = (WebView) view.findViewById(R.id.videoWV);
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            //webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            //webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
            webView.setWebChromeClient(new WebChromeClient());
            webView.loadUrl(url);

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        inflater.inflate(R.menu.vid_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.action_back_vid)
        {
            getFragmentManager().popBackStackImmediate();
        }
        return super.onOptionsItemSelected(item);
    }


}
