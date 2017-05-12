package com.example.jaker.wildlife2;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DocumenterFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private Spinner spinner;

    public DocumenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_documenter, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle)
    {
        super.onViewCreated(view, SavedBundle);
        /*new AsyncDocumenter(new DocumentInterface() {
            @Override
            public void processFinish(ArrayList<Document> documents)
            {
                if (documents != null)
                {
                    if (documents.size() != 0)
                    {
                        viewDocuments(documents);
                    }
                }
                else
                    {
                        showErrorMessage();
                    }

            }
        },getContext()).execute();*/


    }

    public void viewDocuments(ArrayList<Document> documents)
    {
        final ListView lv = (ListView) getView().findViewById(R.id.docLV);
        DocumenterAdapter adapter = new DocumenterAdapter(getContext(), R.layout.fragment_documenter, documents);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getContext(), "CLICKED", Toast.LENGTH_SHORT).show();
                Document d = (Document) lv.getItemAtPosition(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DOC", d);
                Fragment fragment = new DocumenterPageFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("new").commit();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.addFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("EDIT", false);
                Fragment fragment = new AddEditFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("TAG").commit();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        inflater.inflate(R.menu.documenter_menu, menu);
        String[] items = new String[]{"All", "Mammal", "Insect", "Amphibian", "Bird", "Reptile", "Fish"};
        MenuItem menuItem = menu.findItem(R.id.menu_spinner);
        spinner = (Spinner) MenuItemCompat.getActionView(menuItem);
        //spinner = (Spinner) menu.findItem(R.id.menu_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, items);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        super.onCreateOptionsMenu(menu, inflater);
    }


    public void showErrorMessage()
    {
        Toast.makeText(getContext(), "DB Empty", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        final String category = spinner.getSelectedItem().toString();
        if (category.equalsIgnoreCase("All"))
        {
            new AsyncDocumenter(new DocumentInterface() {
                @Override
                public void processFinish(ArrayList<Document> documents) {
                    viewDocuments(documents);
                    Toast.makeText(getContext(), "All Items", Toast.LENGTH_SHORT).show();
                }
            },getContext()).execute();
        }

        else
        {
            new AsyncDocumenter(new DocumentInterface() {
                @Override
                public void processFinish(ArrayList<Document> documents) {
                    viewDocuments(documents);
                    Toast.makeText(getContext(), "Sorted by " + category + "s", Toast.LENGTH_SHORT).show();
                }
            },getContext(),category).execute();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
