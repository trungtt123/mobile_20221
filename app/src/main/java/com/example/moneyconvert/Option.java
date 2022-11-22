package com.example.moneyconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Option extends Activity {
    RadioGroup radioGroupFrom;
    RadioGroup radioGroupTo;
    private double from = 1;
    private double to = 0.00004;
    private String fromName = "USD";
    private String toName = "VND";
    Button submit;
    public static final String EXTRA_DATA = "EXTRA_DATA";
    public static final String EXTRA_FROM = "EXTRA_FROM";
    public static final String EXTRA_TO = "EXTRA_TO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        radioGroupFrom = (RadioGroup) findViewById(R.id.radioGroupFrom);
        radioGroupTo = (RadioGroup) findViewById(R.id.radioGroupTo);
        submit = (Button) findViewById(R.id.submit);
        radioGroupFrom
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.FROM_EURO) {
                            from = 1.03;
                            fromName = "EURO";
                        } else if (checkedId == R.id.FROM_JPY) {
                            from = 0.0071;
                            fromName = "JPY";
                        } else if (checkedId == R.id.FROM_USD) {
                            from = 1;
                            fromName = "USD";
                        } else if (checkedId == R.id.FROM_VND) {
                            from = 0.00004;
                            fromName = "VND";
                        }
                    }

                });
        radioGroupTo
                .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.TO_EURO) {
                            to = 1.03;
                            toName = "EURO";
                        } else if (checkedId == R.id.TO_JPY) {
                            to = 0.0071;
                            toName = "JPY";
                        } else if (checkedId == R.id.TO_USD) {
                            to = 1;
                            toName = "USD";
                        } else if (checkedId == R.id.TO_VND) {
                            to = 0.00004;
                            toName = "VND";
                        }
                    }

                });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });
    }
    private void switchActivities() {
        final Intent data = new Intent();
        data.putExtra(EXTRA_DATA, String.valueOf(from/to));
        data.putExtra(EXTRA_FROM, fromName);
        data.putExtra(EXTRA_TO, toName);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}