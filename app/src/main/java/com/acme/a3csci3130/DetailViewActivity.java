package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class DetailViewActivity extends Activity {

    private EditText numberField, nameField, primarybFeild, addressFeild, provinceFeild;
    Business receivedBusinessInfo;

    private MyApplicationData applicationData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");

        numberField = (EditText) findViewById(R.id.business_number);
        nameField = (EditText) findViewById(R.id.business_name);
        primarybFeild = (EditText) findViewById(R.id.primary_b);
        addressFeild = (EditText) findViewById(R.id.address);
        provinceFeild = (EditText) findViewById(R.id.province);

        if(receivedBusinessInfo != null){
            numberField.setText(receivedBusinessInfo.business_number);
            nameField.setText(receivedBusinessInfo.business_name);
            primarybFeild.setText(receivedBusinessInfo.primary_business);
            addressFeild.setText(receivedBusinessInfo.address);
            provinceFeild.setText(receivedBusinessInfo.province);


        }
    }

    public void updateContact(View v){
        //TODO: Update contact funcionality
        applicationData = (MyApplicationData)getApplication();
        String business_number = numberField.getText().toString();
        String business_name = nameField.getText().toString();
        String primary_business = primarybFeild.getText().toString();
        String address = addressFeild.getText().toString();
        String province = provinceFeild.getText().toString();
        Business business = new Business(receivedBusinessInfo.business_number, business_name, primary_business, address, province);

        //if (valid_business_info(business)==true){
          //  applicationData.firebaseReference.child(receivedBusinessInfo.business_number).setValue(business);
          //  finish();
        //}
        applicationData.firebaseReference.child(receivedBusinessInfo.business_number).setValue(business);
        finish();


    }

    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        applicationData = (MyApplicationData)getApplication();
        if(!receivedBusinessInfo.equals(null)){
            applicationData.firebaseReference.child(receivedBusinessInfo.business_number).removeValue();
            finish();
        }
        else{
            finish();}
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
        valid_provinces.add(" ");

        valid_primary_business.add("Fisher");
        valid_primary_business.add("Distributor");
        valid_primary_business.add("Processor");
        valid_primary_business.add("Fish Monger");

        temp = true;

        if (!(business.business_number.length()==9)){
            temp=false;
            return temp;
        }
        if (business.business_name.length()< 2  || business.business_name.length()>48){
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
        return temp;

    }
}
