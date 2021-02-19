package com.example.geststock;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.geststock.model.Article;
import com.example.geststock.model.MyDBHelper;

public class edit_activity extends AppCompatActivity {

    TextView tvnmae;
    EditText earticle;
    int id_article;
    MyDBHelper db;
    Article article;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modification);
        db=new MyDBHelper(this);
        String narticle=getIntent().getExtras().getString("article");
        id_article=getIntent().getExtras().getInt("id");
        earticle=findViewById(R.id.name_article);
        earticle.setText(narticle);


    }

    public void editArticle(View view ){
        article=new Article();
        String art=earticle.getText().toString();
        System.out.println("id");
        System.out.println(id_article);
        article.setId(id_article);
        article.setLibelle(art);

        db.updateArticle(article);
        startActivity(new Intent(edit_activity.this,StockActivity.class));
        finish();
    }

    public void deleteArticle(View view ){

        article=new Article();
        article.setId(id_article);
        db.deleteArticle(article);
        startActivity(new Intent(edit_activity.this,StockActivity.class));
        finish();
    }



}
