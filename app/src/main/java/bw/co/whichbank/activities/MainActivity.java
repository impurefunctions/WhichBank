package bw.co.whichbank.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar;

import bw.co.whichbank.R;
import bw.co.whichbank.util.Constants;

import static bw.co.whichbank.util.Constants.currentClient;

public class MainActivity extends AppCompatActivity {


    TextView tvHello;
    SharedPreferences preferences;
    TextInputEditText edtLoanAmount,edtLoanDuration, edtBank;
    int loanAmount,loanDuration;
    Spinner spinnerBank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("WhichBank",MODE_PRIVATE);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab.setClickable(false);
        spinnerBank = (Spinner)findViewById(R.id.spinner_bank);
//        spinnerBank
        tvHello = (TextView) findViewById(R.id.helloClient);
        tvHello.setText("Hello, " + currentClient.getfName());
        edtLoanAmount = (TextInputEditText)findViewById(R.id.edtLoanAmount);
        edtLoanDuration = (TextInputEditText)findViewById(R.id.edtLoanDuration);
        edtBank = (TextInputEditText)findViewById(R.id.edtBank);


        BubbleThumbSeekbar seekBar = (BubbleThumbSeekbar)findViewById(R.id.seekBar_loan);


        seekBar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number value) {
                edtLoanAmount.setText("BWP "+ value+".00");
                loanAmount = value.intValue();
                Constants.loanAmount = loanAmount;
                //fab.setClickable(true);


            }
        });

        BubbleThumbSeekbar seekBarDuration = (BubbleThumbSeekbar)findViewById(R.id.seekBar_loan_duration);
        seekBarDuration.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number value) {
                edtLoanDuration.setText( value+ "  years");
                loanDuration = value.intValue();
                Constants.loanDuration = loanDuration;
                //fab.setClickable(true);


            }
        });
        fab.setClickable(true);
        String strBank = edtBank.getText().toString();
        currentClient.bank = strBank;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultsIntent = new Intent(MainActivity.this,BankResults.class);
                startActivity(resultsIntent);

            }
        });
    }
}
