package com.example.training2.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends ArrayAdapter<Expense> {


    private Context mContext;
    private List<Expense> expList = new ArrayList<>();
    DecimalFormat decim = new DecimalFormat("0.00");

    public ExpenseAdapter(Context context,ArrayList<Expense> list) {
        super(context,0,list);
        mContext = context;
        expList = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.expense_list_item,parent,false);

        Expense currentExp = expList.get(position);

        TextView title = listItem.findViewById(R.id.expTitle);
        title.setText(currentExp.getExpenseName());

        TextView budget = listItem.findViewById(R.id.expMoney);
        budget.setText("$"+String.valueOf(decim.format(currentExp.getExpenseAmount())));

        return listItem;
    }
}


