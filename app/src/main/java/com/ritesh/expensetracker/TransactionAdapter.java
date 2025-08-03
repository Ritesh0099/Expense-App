package com.ritesh.expensetracker;

import android.app.Activity;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class TransactionAdapter extends ArrayAdapter<Transaction> {
    Activity context;
    ArrayList<Transaction> transactions;

    public TransactionAdapter(Activity context, ArrayList<Transaction> transactions) {
        super(context, R.layout.transaction_item, transactions);
        this.context = context;
        this.transactions = transactions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(R.layout.transaction_item, parent, false);

        TextView desc = convertView.findViewById(R.id.desc);
        TextView amount = convertView.findViewById(R.id.amount);
        TextView dateTime = convertView.findViewById(R.id.dateTime);

        Transaction t = transactions.get(position);

        desc.setText(t.description);
        dateTime.setText(t.dateTime);

        String prefix = t.isExpense ? "-₹" : "+₹";
        amount.setText(prefix + String.format("%.2f", t.amount));
        amount.setTextColor(t.isExpense ? 0xFFE53935 : 0xFF43A047); // red or green

        return convertView;
    }
}
