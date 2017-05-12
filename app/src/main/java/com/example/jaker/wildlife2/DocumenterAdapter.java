package com.example.jaker.wildlife2;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jaker on 06/05/2017.
 */

public class DocumenterAdapter extends ArrayAdapter
{
    private ArrayList<Document> documents;
    public DocumenterAdapter(Context context, int resource, ArrayList<Document> documents)
    {
        super(context, resource, documents);
        this.documents = documents;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.fragment_documenter, null);
        }

        Document d = (Document) getItem(position);
        TextView nameTV = (TextView) v.findViewById(R.id.docNameTV);
        TextView catTV = (TextView) v.findViewById(R.id.docCatTV);
        TextView amountTV = (TextView) v.findViewById(R.id.docAmmountTV);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.addFab);
        fab.setVisibility(View.GONE);

        nameTV.setVisibility(View.VISIBLE);
        catTV.setVisibility(View.VISIBLE);
        amountTV.setVisibility(View.VISIBLE);
        nameTV.setTypeface(null, Typeface.BOLD);
        //nameTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        //catTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        //amountTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        nameTV.setText(d.getName());
        catTV.setText("Category: " + d.getCategory());
        amountTV.setText("Amount: " + Integer.toString(d.getAmount()));

        return v;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
