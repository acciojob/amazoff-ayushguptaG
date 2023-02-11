package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id= id;
        this.deliveryTime=convertTime(deliveryTime);

    }
    public int convertTime(String deliveryTime){
        int hh = Integer.valueOf(deliveryTime.substring(0, 2));
        int mm = Integer.valueOf(deliveryTime.substring(3, 5));
        int convertedTime = hh*60 + mm;
        return convertedTime;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
