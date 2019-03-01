package com.example.mateusz.batterytester.Model.Domain.Objects;



public class InterClassDataHolder {
    private static InterClassDataHolder ObjectInstance;

    private double _price;

    private InterClassDataHolder(){

    }

    public static InterClassDataHolder getInstance(){
        if(ObjectInstance == null){
            ObjectInstance = new InterClassDataHolder();
        }

        return ObjectInstance;
    }

    public double getPrice(){
        return _price;
    }

    public void setPrice(double price){
        _price = price;
    }

}
