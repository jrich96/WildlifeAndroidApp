package com.example.jaker.wildlife2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements View.OnClickListener
{


    private Button video1Btn;
    private Button video2Btn;
    private Button video3Btn;
    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle)
    {
        video1Btn = (Button) view.findViewById(R.id.iguanaVidBtn);
        video2Btn = (Button) view.findViewById(R.id.kangVidBtn);
        video3Btn = (Button) view.findViewById(R.id.tigerBtn);

        video1Btn.setOnClickListener(this);
        video2Btn.setOnClickListener(this);
        video3Btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        Fragment vpf = new VideoPlayerFragment();
        Bundle values = new Bundle();
        switch (view.getId())
        {
            case R.id.iguanaVidBtn:
                values.putString("URL", "https://www.youtube.com/embed/Rv9hn4IGofM");
                break;
            case R.id.kangVidBtn:
                values.putString("URL", "https://www.youtube.com/embed/WCcLMNcWZOc");
                break;
            case R.id.tigerBtn:
                values.putString("URL", "https://www.youtube.com/embed/RPekEt1Kjg8");
                break;


        }
        vpf.setArguments(values);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, vpf).addToBackStack("vf").commit();
        Toast.makeText(getContext(), "Loading Video...", Toast.LENGTH_SHORT).show();


    }
}
