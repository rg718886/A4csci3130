package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import android.content.Context;



public class CreateBusinessAcitivity extends Activity {

    private Button submitButton;
    private EditText numberField, nameField, primaryFeild, addressFeild, provinceFeild;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_business_activity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());
        submitButton = (Button) findViewById(R.id.submitButton);
        numberField = (EditText) findViewById(R.id.business_number);
        nameField = (EditText) findViewById(R.id.business_name);
        primaryFeild = (EditText) findViewById(R.id.primary_b);
        provinceFeild = (EditText) findViewById(R.id.province);
        addressFeild = (EditText) findViewById(R.id.address);
    }
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String b_number = appState.firebaseReference.push().getKey();
        String b_name = nameField.getText().toString();
        String primary_b = primaryFeild.getText().toString();
        String b_address = addressFeild.getText().toString();
        String b_province = provinceFeild.getText().toString();

        Business business = new Business(b_number, b_name, primary_b, b_address, b_province);
        appState.firebaseReference.child(b_number).setValue(business);
        finish();


    }

    //might not use it --> if set rules in fireRules.json
    private boolean valid_business_info(Business business){

        ArrayList<String> valid_provinces = new ArrayList<String>();
        ArrayList<String> valid_primary_business = new ArrayList<String>();
        boolean temp;

        valid_provinces.add("AB");
        valid_provinces.add("BC");
        valid_provinces.add("MB");
        valid_provinces.add("NB");
        valid_provinces.add("NL");
        valid_provinces.add("NS");
        valid_provinces.add("NT");
        valid_provinces.add("NU");
        valid_provinces.add("ON");
        valid_provinces.add("PE");
        valid_provinces.add("QC");
        valid_provinces.add("SK");
        valid_provinces.add("YT");
        valid_provinces.add("");
        valid_primary_business.add("Fisher");
        valid_primary_business.add("Distributor");
        valid_primary_business.add("Processor");
        valid_primary_business.add("Fish Monger");
        temp = true;

        if (!(business.business_number.length()==9)){
            temp=false;
            return temp;
        }
        if (business.business_name.length()< 2 || business.business_name.length()>48){
            temp=false;
            return temp;
        }
        if (!(business.address.length()<50)){
            temp=false;
            return temp;
        }
        if (!valid_primary_business.contains(business.primary_business)){
            temp=false;
            return temp;
        }

        if (!valid_provinces.contains(business.province)){
            temp=false;
            return temp;
        }
        else {
            return  temp;}

    }
}
