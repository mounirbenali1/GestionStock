package com.example.geststock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.geststock.model.MyDBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDBHelper db = new MyDBHelper(this);
        configureStockButton();
        configureOperationButton();
        /*
        db.insertArticle("Pain");
        db.insertArticle("Lait");
        List<String> lst = db.getAllArticles();
        lst.forEach(l -> Log.i("GEST-STK", l));*/
    }

    private void configureStockButton(){
        Button stock_btn=(findViewById(R.id.btn_stock));
        stock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,StockActivity.class));
            }
        });

    }
    private void configureOperationButton(){
        Button operation_btn=(findViewById(R.id.btn_operation));
        operation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,OperationActivity.class));
            }
        });

    }
}