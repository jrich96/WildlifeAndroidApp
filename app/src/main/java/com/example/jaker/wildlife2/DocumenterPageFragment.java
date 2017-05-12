package com.example.jaker.wildlife2;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DocumenterPageFragment extends Fragment {

    private Document document;

    public DocumenterPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);

        return inflater.inflate(R.layout.fragment_documenter_page, container, false);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle SavedBundle)
    {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        MenuItem back = (MenuItem) toolbar.getMenu().findItem(R.id.action_back);
        if (back != null)
        {
            back.setVisible(true);
        }
        MenuItem edit = (MenuItem) toolbar.getMenu().findItem(R.id.action_edit);
        if (edit != null)
        {
            edit.setVisible(true);
        }
        MenuItem delete = (MenuItem) toolbar.getMenu().findItem(R.id.action_delete);
        if (delete != null)
        {
            delete.setVisible(true);
        }

        document = (Document) getArguments().getSerializable("DOC");
        TextView nameTV = (TextView) getView().findViewById(R.id.docPageNameTV);
        TextView activityTV = (TextView) getView().findViewById(R.id.docPageActivityTV);
        TextView amountTV = (TextView) getView().findViewById(R.id.docPageAmmountTV);
        TextView catTV = (TextView) getView().findViewById(R.id.docPageCatTV);
        TextView locTV = (TextView) getView().findViewById(R.id.docPageLocTV);
        nameTV.setText(document.getName());
        activityTV.setText("Activity: " + document.getActivity());
        amountTV.setText("Amount: " + Integer.toString(document.getAmount()));
        catTV.setText("Category: " + document.getCategory());
        locTV.setText("Location: " + document.getLocation());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = new AddEditFragment();
        Bundle bundle;
        switch (item.getItemId())
        {
            case R.id.action_back:
                goBack();
                break;
            case R.id.action_edit:
                bundle = new Bundle();
                bundle.putBoolean("EDIT", true);
                bundle.putSerializable("EDITDOC", document);
                fragment.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("TAG").commit();

                break;

            case R.id.action_delete:
                showDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog()
    {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        new AsyncDocumenter(new DocumentInterface() {
                            @Override
                            public void processFinish(ArrayList<Document> documents) {
                                Toast.makeText(getContext(), "Record Deleted", Toast.LENGTH_SHORT).show();
                                goBack();
                            }
                        }, getContext(), document.getId()).execute();

                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }

            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Are you sure you want to delete this record?").setPositiveButton("Yes", onClickListener)
                .setNegativeButton("No", onClickListener).show();

    }

    private void goBack()
    {

        Fragment fragment = new DocumenterFragment();

        //getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        getFragmentManager().popBackStackImmediate();

    }
}
