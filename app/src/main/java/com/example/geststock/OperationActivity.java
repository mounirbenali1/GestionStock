package com.example.geststock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.geststock.model.Article;
import com.example.geststock.model.MyDBHelper;
import com.example.geststock.model.Operation;
import com.example.geststock.model.OperationAdapter;

import java.util.ArrayList;
import java.util.List;

public class OperationActivity extends AppCompatActivity {
    MyDBHelper db_helper;
    private Button btn_add;
    private TextView date;
    private TextView qte;

    private ListView listView;
    ArrayList<Operation> arrayList1;
    ArrayList<Operation> arrayList;
    OperationAdapter OperationAdapter;
    Spinner spinner;
    Operation opobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        addItemsOnSpinner2();
        addListenerOnSpinnerItemSelection();
        arrayList = new ArrayList<Operation>();
        arrayList1 = new ArrayList<Operation>();
        db_helper=new MyDBHelper(this);
        spinner=(Spinner)findViewById(R.id.spinner);
        arrayList=(ArrayList) db_helper.getAllArticles();
        arrayList1=(ArrayList) db_helper.getAllOperation();
        date=(TextView)findViewById(R.id.id_dt);
        qte=(TextView)findViewById(R.id.id_qte);

        listView=(ListView)findViewById(R.id.oplistview);
        listView.setClickable(true);
        OperationAdapter = new OperationAdapter(this, R.layout.list_op_adapter,arrayList1,db_helper); //As you can see we're affecting this adapter to another layout
        listView.setAdapter(OperationAdapter);




        btn_add=(Button)findViewById(R.id.add_op);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("operation");
                String art_nm=spinner.getSelectedItem().toString();
                String dt=date.getText().toString();
                int qt=Integer.parseInt(qte.getText().toString());

                opobj=new Operation(db_helper.getArtID(art_nm),dt,qt);
                arrayList.add(opobj);
                db_helper.insertOperation(opobj);
                OperationAdapter.notifyDataSetChanged();
                ; // Modifier listView

                Log.d("ref","zjouter");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parentAdapter, View view,
                                    int position, long id) {

                Operation op = arrayList1.get(position) ;
                Intent intent=new Intent(OperationActivity.this,edit_op_activity.class);
                intent.putExtra("id",op.getNum());
                intent.putExtra("article_nm",db_helper.getArtname(op.getArticle_id()));
                intent.putExtra("date",op.getDate());
                intent.putExtra("qte",op.getQte());
                startActivity(intent);
                finish();
                System.out.print(op.getDate());
                Log.d("ms",op.getDate());



            }
        });





    }

    public void addListenerOnSpinnerItemSelection() {
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    public void addItemsOnSpinner2() {
        db_helper= new MyDBHelper(this);
        List<Article> list = new ArrayList<Article>();
        List<String> list_article = new ArrayList<String>();
        list=db_helper.getAllArticles();

        for (Article art :list) {
            list_article.add(art.getLibelle());

        }

        Spinner spinner1= (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                                            android.R.layout.simple_spinner_item, list_article);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }


}