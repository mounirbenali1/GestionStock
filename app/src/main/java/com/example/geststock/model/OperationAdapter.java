package com.example.geststock.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.geststock.R;

import java.util.ArrayList;

public class OperationAdapter extends ArrayAdapter<Operation> {
    private Context oContext;
    private ArrayList<Operation> operations; //Declaring an array list of our History class
    private int mResource;
    MyDBHelper db;
    public OperationAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Operation> objects,MyDBHelper db) {
        //The constructor of our adapter with our context and ressources
        super(context, resource,objects);
        this.oContext = context;
        mResource = resource;
        this.operations = objects;
    }

    @Override
    public int getCount() {

        return operations.size();

    }

    @Nullable
    @Override
    public Operation getItem(int position) {
        return operations.get(position);
    } //Return the item in the giving position

    @Override
    public int getPosition(@Nullable Operation item) {
        return operations.indexOf(item);
    } //To get the position of certain item

    @Override
    public long getItemId(int position) {
        return position;
    } //get position by itemID

    public View getView(int position, View convertView, ViewGroup parents){ //Get the view in a given position
        if ( convertView == null) {
            convertView = LayoutInflater.from(oContext).inflate(mResource,parents,false);
            // Inflate our layer from the custom context and using our ressources
        }

        //Spinner sp = (Spinner) convertView.findViewById(R.id.spinner); //For the labels in our application
        //String article_nm= sp.getSelectedItem().toString();

        TextView id_op = (TextView) convertView.findViewById(R.id.id_operation);
        TextView id_art = (TextView) convertView.findViewById(R.id.art_name);
        TextView date = (TextView) convertView.findViewById(R.id.id_date);
        TextView qte = (TextView) convertView.findViewById(R.id.id_qt);


        id_op.setText(String.valueOf(getItem(position).getNum()));
        id_art.setText(String.valueOf(getItem(position).getArticle_id()));
        date.setText(getItem(position).getDate());
        qte.setText(String.valueOf(getItem(position).getQte()));
        return convertView;
    }
}
