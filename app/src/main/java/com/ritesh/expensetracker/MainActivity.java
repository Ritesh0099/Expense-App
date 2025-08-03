package com.ritesh.expensetracker;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText amountEditText, descEditText;
    RadioGroup typeRadioGroup;
    Button addButton;
    TextView balanceTextView;
    ListView transactionListView;

    ArrayList<Transaction> transactions = new ArrayList<>();
    TransactionAdapter adapter;
    double balance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEditText);
        descEditText = findViewById(R.id.descEditText);
        typeRadioGroup = findViewById(R.id.typeRadioGroup);
        addButton = findViewById(R.id.addButton);
        balanceTextView = findViewById(R.id.balanceTextView);
        transactionListView = findViewById(R.id.transactionListView);

        adapter = new TransactionAdapter(this, transactions);
        transactionListView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String amountStr = amountEditText.getText().toString().trim();
            String desc = descEditText.getText().toString().trim();

            if (amountStr.isEmpty() || desc.isEmpty() || typeRadioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);
            boolean isExpense = typeRadioGroup.getCheckedRadioButtonId() == R.id.expenseRadio;

            String currentDateTime = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
                    .format(new Date());

            Transaction transaction = new Transaction(desc, amount, isExpense, currentDateTime);
            transactions.add(0, transaction);

            balance += isExpense ? -amount : amount;
            balanceTextView.setText("Current Balance: ₹" + String.format("%.2f", balance));

            adapter.notifyDataSetChanged();
            amountEditText.setText("");
            descEditText.setText("");
            typeRadioGroup.clearCheck();
        });
    }
}
