package bw.co.whichbank.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar;

import bw.co.whichbank.R;
import bw.co.whichbank.models.ClientClass;

import static bw.co.whichbank.util.Constants.currentClient;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TextInputEditText edtFName,edtSName,edtOccupation,edtEmployer,edtLocation, edtMonthlyIncome;// = (TextInputEditText) rootView.findViewById(R.id.monthly_income) ;
    String fName,lName,occupation,employer,natureOfEmployment,contractOrPerm,location,monthlyIncome;
    RadioGroup radioNatureOfEmployment,radioContractOrPerm;
    RadioButton radioContract,radioGov;
    ClientClass clientClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences("WhichBank",MODE_PRIVATE);
        edtFName = (TextInputEditText)findViewById(R.id.edtPersonfName) ;
        edtSName = (TextInputEditText)findViewById(R.id.edtPersonLName) ;
        edtOccupation = (TextInputEditText)findViewById(R.id.edtOccupation) ;
        edtEmployer = (TextInputEditText)findViewById(R.id.edtEmployer) ;
        edtLocation = (TextInputEditText)findViewById(R.id.edtLocation) ;
        edtMonthlyIncome = (TextInputEditText)findViewById(R.id.edtMonthlyIncome) ;
        radioNatureOfEmployment = (RadioGroup)findViewById(R.id.group_empNature);
        radioContractOrPerm = (RadioGroup)findViewById(R.id.group_empContract);

        final int[] monthlyIncomInt = new int[1];


        BubbleThumbSeekbar seekBar = (BubbleThumbSeekbar)findViewById(R.id.seekBar_income);
        seekBar.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number value) {
                edtMonthlyIncome.setText("BWP "+ value+".00");
                monthlyIncomInt[0] = value.intValue();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean cancel =false;
                View focusView = null;
                /*
  Gather all the data

  */
                // get selected radio button from radioGroup
                int selectedConId = radioContractOrPerm.getCheckedRadioButtonId();
                int selectedGovId = radioNatureOfEmployment.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioContract = (RadioButton) findViewById(selectedConId);
                radioGov = (RadioButton) findViewById(selectedGovId);

                fName = edtFName.getText().toString();
                if (TextUtils.isEmpty(fName)) {
                    edtFName.setError("You Name can't be blank");
                    focusView = edtFName;
                    cancel = true;
                }
                lName = edtSName.getText().toString();
                if (TextUtils.isEmpty(lName)) {
                    edtSName.setError("You surname can't be blank");
                    focusView = edtSName;
                    cancel = true;
                }
                occupation = edtOccupation.getText().toString();
                if (TextUtils.isEmpty(occupation)) {
                    edtOccupation.setError("Your Occupation can't be blank");
                    focusView = edtOccupation;
                    cancel = true;
                }
                employer = edtEmployer.getText().toString();
                if (TextUtils.isEmpty(employer)) {
                    edtEmployer.setError("Your employer can't be blank");
                    focusView = edtEmployer;
                    cancel = true;
                }

                natureOfEmployment = radioGov.getText().toString();
                contractOrPerm = radioContract.getText().toString();
                location = edtLocation.getText().toString();
                monthlyIncome = edtMonthlyIncome.getText().toString();


                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("fName",fName);
                    editor.putString("lName",lName);
                    editor.putString("occupation",occupation);
                    editor.putString("employer",fName);
                    editor.putString("nature",natureOfEmployment);
                    editor.putString("contract",contractOrPerm);
                    editor.putString("location",location);
                    editor.putString("monthly",monthlyIncome);
                    editor.apply();
                    clientClass = new ClientClass(fName,lName,occupation,employer,natureOfEmployment,contractOrPerm,location,monthlyIncomInt[0]);
                    currentClient = clientClass;
                    startActivity(new Intent(HomeActivity.this,MainActivity.class));
                    Snackbar.make(view, "Your data has been captured", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
    }

}
