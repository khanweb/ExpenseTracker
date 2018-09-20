package com.example.training2.expensetracker;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ExpenseMonitor extends AppCompatActivity {

    FloatingActionButton addExp;
    int expPosition;
    ListView expenses;
    ArrayList<Expense> dummy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_monitor);

        addExp = findViewById(R.id.addExp);
        expenses = findViewById(R.id.expenses);


        final ExpenseAdapter eAdapter = new ExpenseAdapter(ExpenseMonitor.this,dummy);
        expenses.setAdapter(eAdapter);


        Intent intent = getIntent();
        expPosition = intent.getIntExtra("position",0);
        addExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ExpenseMonitor.this);
                dialog.setContentView(R.layout.add_category);

                final EditText expName,expAmnt;
                Button sub,can;
                expName = dialog.findViewById(R.id.expName);
                expAmnt = dialog.findViewById(R.id.expAmount);
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
                        dummy.add(new Expense(
                                expName.getText().toString(),
                                Double.parseDouble(expAmnt.getText().toString())
                        ));;
                        MainActivity.categories.get(expPosition).setExpenses(dummy);

                        eAdapter.notifyDataSetChanged();
                        dialog.cancel();

                        }
                });



                dialog.show();

            }
        });
    }
}
