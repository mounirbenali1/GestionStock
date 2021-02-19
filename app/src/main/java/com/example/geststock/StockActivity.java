package com.example.geststock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.geststock.model.Article;
import com.example.geststock.model.ArticleAdapter;
import com.example.geststock.model.MyDBHelper;

import java.util.ArrayList;

public class StockActivity extends AppCompatActivity {
    private EditText article_nm;
    private Button btn_add;

    private ListView listView;

    ArrayList<Article> arrayList;
    ArticleAdapter articleAdapter;
    MyDBHelper db_helper;
    Article artobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        article_nm=(EditText)findViewById(R.id.id_article);
        arrayList = new ArrayList<Article>();
        db_helper=new MyDBHelper(this);

        arrayList=(ArrayList) db_helper.getAllArticles();
        listView=(ListView)findViewById(R.id.listview);
        listView.setClickable(true);
        articleAdapter = new ArticleAdapter(this, R.layout.list_view_adapter,arrayList,db_helper); //As you can see we're affecting this adapter to another layout
        listView.setAdapter(articleAdapter);




        btn_add=(Button)findViewById(R.id.btn_ajt);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("dsdsdsds");
            String art_nm=article_nm.getText().toString();
            artobj=new Article(art_nm);
            arrayList.add(artobj);
            articleAdapter.notifyDataSetChanged();
            db_helper.insertArticle(artobj);
           ; // Modifier listView

                Log.d("ref","zjouter");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parentAdapter, View view,
                int position, long id) {

                    Article client = arrayList.get(position) ;
                    Intent intent=new Intent(StockActivity.this,edit_activity.class);
                    intent.putExtra("id",client.getId());
                    intent.putExtra("article",client.getLibelle());
                    startActivity(intent);
                    finish();
                    System.out.print(client.getLibelle());
                    Log.d("ms",client.getLibelle());



                }
            });






    }




}