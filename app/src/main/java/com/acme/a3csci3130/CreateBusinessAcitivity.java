package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import android.content.Context;
import android.widget.Toast;


public class CreateBusinessAcitivity extends Activity {

    private Button submitButton;
    private EditText numberField, nameField, primaryFeild, addressFeild, provinceFeild;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
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
        String business_number = appState.firebaseReference.push().getKey();
        String business_name = nameField.getText().toString();
        String primary_b = primaryFeild.getText().toString();
        String address = addressFeild.getText().toString();
        String province = provinceFeild.getText().toString();

        Business business = new Business(business_number, business_name, primary_b, address, province);

        if(valid_business_info(business)==true){
            appState.firebaseReference.child(business_number).setValue(business);

            finish();}
        //if not valid, show message to user
        else{
            //this code segment is retrieved from https://developer.android.com/guide/topics/ui/notifiers/toasts.html  : the basics part
            Context context = getApplicationContext();
            CharSequence text = "invalid business information, check inputs!";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        }

    }
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
        valid_provinces.add(" ");

        valid_primary_business.add("Fisher");
        valid_primary_business.add("Distributor");
        valid_primary_business.add("Processor");
        valid_primary_business.add("Fish Monger");

        temp = true;

        if ( (business.business_number.length()!=9) || (!(2<business.business_name.length() && business.business_name.length()<48))  ||  (valid_primary_business.contains(business.primary_business)==false) || !(business.address.length()<=50) || (valid_provinces.contains(business.province)==false) ){
            temp=false;
        }
        return temp;


    }
}
