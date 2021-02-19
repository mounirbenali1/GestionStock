package com.example.geststock.model;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.geststock.R;

import java.util.ArrayList;

public class ArticleAdapter  extends ArrayAdapter<Article> {
    private Context mContext;
    private ArrayList<Article> articles; //Declaring an array list of our History class
    private int mResource;
    public ArticleAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Article> objects,MyDBHelper db) {
        //The constructor of our adapter with our context and ressources
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
        this.articles = objects;
    }

    @Override
    public int getCount() {

        return articles.size();

    }

    @Nullable
    @Override
    public Article getItem(int position) {
        return articles.get(position);
    } //Return the item in the giving position

    @Override
    public int getPosition(@Nullable Article item) {
        return articles.indexOf(item);
    } //To get the position of certain item

    @Override
    public long getItemId(int position) {
        return position;
    } //get position by itemID

    public View getView(int position, View convertView, ViewGroup parents){ //Get the view in a given position
        if ( convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource,parents,false);
            // Inflate our layer from the custom context and using our ressources
        }

        TextView id_article = (TextView) convertView.findViewById(R.id.id_produit); //For the labels in our application
        TextView article_name = (TextView) convertView.findViewById(R.id.nom_produit);
        //Button  btn_modifier= (Button) convertView.findViewById(R.id.edit);
        //Button  btn_supprimer= (Button) convertView.findViewById(R.id.delete);


        id_article.setText(Integer.toString(getItem(position).getId()));
        article_name.setText(getItem(position).getLibelle());



        return convertView;
    }



}
