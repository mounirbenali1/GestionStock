package com.example.geststock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.geststock.model.Article;
import com.example.geststock.model.MyDBHelper;
import com.example.geststock.model.Operation;

public class edit_op_activity extends AppCompatActivity {

    EditText qte;
    EditText date;
    EditText nmart;

    String id_op;
    String article;
    MyDBHelper db;
    Operation operation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_op_modification);
        db=new MyDBHelper(this);

        id_op=getIntent().getExtras().getString("id");
        article=getIntent().getExtras().getString("article_nm");
        String dt=getIntent().getExtras().getString("date");
        int qt=getIntent().getExtras().getInt("qte");

        qte=findViewById(R.id.id_qt);
        date=findViewById(R.id.date_operation);
        nmart=findViewById(R.id.id_art);

        qte.setText(String.valueOf(qt));
        date.setText(dt);
        nmart.setText(article);

    }

    public void editOperation(View view ){
        operation=new Operation();


        operation.setQte(Integer.parseInt(qte.getText().toString()));
        operation.setDate(date.getText().toString());
        operation.setArticle_id(db.getArtID(nmart.getText().toString()));
        db.updateOperation(operation);
        startActivity(new Intent(edit_op_activity.this,OperationActivity.class));
        finish();
    }

    public void deleteOperation(View view ){

        operation=new Operation();
        operation.setNum(Integer.parseInt(id_op));
        db.deleteOperation(operation);
        startActivity(new Intent(edit_op_activity.this,OperationActivity.class));
        finish();
    }



}
