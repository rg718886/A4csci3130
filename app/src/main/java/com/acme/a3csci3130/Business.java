package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Business implements Serializable {

    public  String business_number;
    public  String business_name;
    public  String primary_business;
    public String address;
    public String province;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Business(String business_number, String business_name, String primary_business, String address, String province){
        this.business_number = business_number;
        this.business_name = business_name;
        this.primary_business = primary_business;
        this.address = address;
        this.province = province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("business_number", business_number);
        result.put("business_name", business_name);
        result.put("primary_business", primary_business);
        
        if(province.length()==0)
            result.put("province", province);
        if (address.length() == 0)
            result.put("address", address);

        return result;
    }
}