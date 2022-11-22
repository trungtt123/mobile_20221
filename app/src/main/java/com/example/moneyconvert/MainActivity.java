package com.example.moneyconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText from;
    private EditText to;
    private double convert = 25000;
    private static final int REQUEST_CODE_EXAMPLE = 0x9345;
    Button switchToOptionActivity;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_EXAMPLE) {
            if(resultCode == Activity.RESULT_OK) {
                final String result = data.getStringExtra(Option.EXTRA_DATA);
                final String fromName = data.getStringExtra(Option.EXTRA_FROM);
                final String toName = data.getStringExtra(Option.EXTRA_TO);
                from = (EditText) findViewById(R.id.from);
                to = (EditText) findViewById(R.id.to);
                TextView txtViewFrom = (TextView) findViewById(R.id.textViewFrom);
                TextView txtViewTo = (TextView) findViewById(R.id.textViewTo);
                txtViewFrom.setText("From " + fromName);
                txtViewTo.setText("To " + toName);
                convert = Double.parseDouble(result);
                try {
                    double fromData = Double.parseDouble(from.getText().toString());
                    double toData = fromData * convert;
                    to.setText(String.valueOf(toData));
                }
                catch (Exception e){
                    to.setText("Invalid Input");
                }
            } else {
                // DetailActivity không thành công, không có data trả về.
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        from = (EditText) findViewById(R.id.from);
        to = (EditText) findViewById(R.id.to);
        from.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    double fromData = (s == null || s.toString().isEmpty()) ? 0 : Double.parseDouble(s.toString());
                    double toData = fromData * convert;
                    to.setText(String.valueOf(toData));
                }
                catch (Exception e){
                    to.setText("Invalid Input");
                }
            }
        });
        switchToOptionActivity = findViewById(R.id.button);
        switchToOptionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });
    }
    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, Option.class);
        startActivityForResult(switchActivityIntent, REQUEST_CODE_EXAMPLE);
    }
}