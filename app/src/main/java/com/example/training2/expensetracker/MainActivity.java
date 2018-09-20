package com.example.training2.expensetracker;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    FloatingActionButton addCat;
    ListView cList;
    int id = 0;
    static ArrayList<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCat = (FloatingActionButton) findViewById(R.id.addCat);
        cList = (ListView) findViewById(R.id.List);

        cList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Intent intent = new Intent(MainActivity.this,ExpenseMonitor.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });

        final CategoryAdapter cAdapter = new CategoryAdapter(MainActivity.this, categories);

        cList.setAdapter(cAdapter);

        addCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_category);

                final EditText catName,catBud;
                Button sub,can;
                catName = dialog.findViewById(R.id.catName);
                catBud = dialog.findViewById(R.id.catBudget);
                sub = dialog.findViewById(R.id.submit);
                can = dialog.findViewById(R.id.cancel);

                can.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Category cat = new Category(id);
                        id++;
                        cat.setCatName(catName.getText().toString());
                        cat.setBudget(Double.parseDouble(catBud.getText().toString()));
                        categories.add(cat);
                        cAdapter.notifyDataSetChanged();
                        dialog.cancel();

                    }
                });



                dialog.show();

            }
        });

    }

}
