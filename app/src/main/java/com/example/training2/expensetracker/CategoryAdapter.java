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

public class CategoryAdapter extends ArrayAdapter<Category> {


    private Context mContext;
    private List<Category> catList = new ArrayList<>();
    DecimalFormat decim = new DecimalFormat("0.00");

    public CategoryAdapter(Context context,ArrayList<Category> list) {
        super(context,0,list);
        mContext = context;
        catList = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.category_list_item,parent,false);

        Category currentCat = catList.get(position);

        TextView title = listItem.findViewById(R.id.catTitle);
        title.setText(currentCat.getCatName());

        TextView budget = listItem.findViewById(R.id.catMoney);
        budget.setText("$"+String.valueOf(decim.format(currentCat.getBudget())));




        return listItem;
    }
}


