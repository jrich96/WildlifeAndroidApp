package com.example.jaker.wildlife2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddEditFragment extends Fragment {


    private int mode;
    private int id;
    private Document document;
    private EditText nameET;
    private EditText activityET;
    private EditText locationET;
    private EditText amountET;

    public AddEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_edit, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle) {
        super.onViewCreated(view, SavedBundle);

        if (getArguments().getBoolean("EDIT"))
        {
            mode = 1;
            document = (Document) getArguments().getSerializable("EDITDOC");
        }

        else
        {
            mode = 2;
        }

        setupForm();

    }

    private void setupForm()
    {
        nameET = (EditText) getView().findViewById(R.id.addEditNameET);
        activityET = (EditText) getView().findViewById(R.id.addEditActET);
        locationET = (EditText) getView().findViewById(R.id.addEditLocET);
        amountET = (EditText) getView().findViewById(R.id.addEditAmountET);
        final Spinner spinner = (Spinner) getView().findViewById(R.id.addEditSpinner);
        String[] items = new String[]{"Mammal", "Insect", "Amphibian", "Bird", "Reptile", "Fish"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, items);
        spinner.setAdapter(adapter);

        if (mode == 1)
        {
            nameET.setText(document.getName());
            activityET.setText(document.getActivity());
            locationET.setText(document.getLocation());
            amountET.setText(Integer.toString(document.getAmount()));
            int pos = -1;
            pos = adapter.getPosition(document.getCategory());
            if (pos > -1)
            {
                spinner.setSelection(pos);
            }
        }


        Button submitBtn = (Button) getView().findViewById(R.id.addEditSubmitBtn);
        Button cancelBtn = (Button) getView().findViewById(R.id.addEditCancelBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(testETs())
                {
                    //handle exception
                    String name = nameET.getText().toString();
                    String activity = activityET.getText().toString();
                    String location = locationET.getText().toString();
                    int amount = Integer.parseInt(amountET.getText().toString());
                    String category = spinner.getSelectedItem().toString();
                    Document newDoc = new Document();
                    if (mode == 1)
                    {
                        newDoc = new Document(name, category, activity, location, amount, document.getId());
                    }

                    else if (mode == 2)
                    {
                        newDoc = new Document(name, category, activity, location, amount);
                    }

                    queryDB(newDoc);
                }

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bundle bundle = new Bundle();
                //bundle.putSerializable("DOC", document);
                //Fragment fragment = new DocumenterPageFragment();
                //fragment.setArguments(bundle);
                getFragmentManager().popBackStackImmediate();
                //getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("df").commit();
            }
        });



    }

    private void queryDB(Document newDoc)
    {
        if (mode == 1)
        {
            new AsyncDocumenter(new DocumentInterface() {
                @Override
                public void processFinish(ArrayList<Document> documents) {
                    Toast.makeText(getContext(), "Document Edited", Toast.LENGTH_SHORT).show();
                    loadNext();
                }
            }, getContext(), newDoc, newDoc.getId()).execute();
        }

        else if (mode == 2)
        {
            new AsyncDocumenter(new DocumentInterface() {
                @Override
                public void processFinish(ArrayList<Document> documents) {
                    Toast.makeText(getContext(), "Document Added", Toast.LENGTH_SHORT).show();
                    loadNext();
                }
            }, getContext(), newDoc).execute();
        }

    }

    private boolean testETs()
    {
        EditText[] texts = new EditText[] {nameET, activityET, locationET, amountET};
        String testText = "";
        for (int i = 0; i < texts.length; i++)
        {
            testText = texts[i].getText().toString();
            if (testText.equalsIgnoreCase(""))
            {
                Toast.makeText(getContext(), "Enter Values", Toast.LENGTH_SHORT).show();
                return false;
            }
            testText = "";
        }
        return true;
    }

    public void loadNext()
    {
        Fragment fragment = new DocumenterFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }


}
