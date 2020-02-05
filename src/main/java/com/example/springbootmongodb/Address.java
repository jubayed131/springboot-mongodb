package com.example.springbootmongodb;

public class Address {
    private String country;
    private String city;

    protected Address(){}
    public Address(String country, String city){
        this.country=country;
        this.city=city;
    }
    public String getCity(){
        return city;
    }

    public String getCountry(){
        return country;
    }
}
