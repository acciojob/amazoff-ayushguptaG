package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    private HashMap<String,Order> orderMap= new HashMap<>();
    private HashMap<String,DeliveryPartner> deliveryPartnerMap= new HashMap<>();
    private HashMap<String, List<Order>> deliveryPartnerOrderMap= new HashMap<>();
    private List<Order> assignedOrders= new ArrayList<>();


    public void addOrder(Order order){

        String id= order.getId();
        orderMap.put(id, order);
    }
    public void addPartner(String id){

        DeliveryPartner deliveryPartner= new DeliveryPartner(id);
        deliveryPartnerMap.put(id,deliveryPartner);
    }
    public void addOrderPartnerPair(String orderId, String partnerId){

        if(assignedOrders.contains(orderMap.get(orderId))){
            return;
        }
        if(!deliveryPartnerOrderMap.containsKey(partnerId)){
            List<Order> orderList=  new ArrayList<>();
            deliveryPartnerOrderMap.put(partnerId, orderList);
        }
        deliveryPartnerOrderMap.get(partnerId).add(orderMap.get(orderId));

        int orders= deliveryPartnerMap.get(partnerId).getNumberOfOrders();

        deliveryPartnerMap.get(partnerId).setNumberOfOrders(orders+1);
        assignedOrders.add(orderMap.get(orderId));

    }
    public Order getOrderById(String id){

        return orderMap.get(id);
    }
    public DeliveryPartner getPartnerById(String id){
        return deliveryPartnerMap.get(id);
    }
    public Integer getOrderCountByPartnerId(String id){

        int numOfOrders= deliveryPartnerMap.get(id).getNumberOfOrders();
        return Integer.valueOf(numOfOrders);
    }
    public List<String> getOrdersByPartnerId(String id){

        List<String> orderList= new ArrayList<>();
        for(Order order : deliveryPartnerOrderMap.get(id)){
            orderList.add(order.getId());
        }
        return orderList;
    }
    public List<String> getAllOrders(){

        List<String> list= new ArrayList<>();
        for(Order order : orderMap.values()){
            list.add(order.getId());
        }
        return list;
    }
    public Integer getCountOfUnassignedOrders(){

        int countOfUnassignedOrders= orderMap.size()-assignedOrders.size();
        return Integer.valueOf(countOfUnassignedOrders);
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        int ordersLeft= 0;
        int hour= Integer.valueOf(time.substring(0,2));
        int minute= Integer.valueOf(time.substring(3));

        int givenTime= hour*60+minute;
        for(Order order: deliveryPartnerOrderMap.get(partnerId)){

            if(order.getDeliveryTime()>givenTime) {
                ordersLeft++;
            }
        }
        return Integer.valueOf(ordersLeft);
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){

        int lastDeliveryTime= 0;
        for(Order order : deliveryPartnerOrderMap.get(partnerId)){
                if(order.getDeliveryTime() > lastDeliveryTime){
                    lastDeliveryTime= order.getDeliveryTime();
                }
        }

        StringBuilder time = new StringBuilder("");

        int hour= lastDeliveryTime/60;
        int min= lastDeliveryTime%60;

        if(hour>9){
            time.append(hour);
        }
        else
            time.append(0).append(hour);

        time.append(":");
        if(min > 9){
            time.append(min);
        }
        else
            time.append(0).append(min);

        return String.valueOf(time);

    }
    public void  deletePartnerById(String id){

        for(Order order : deliveryPartnerOrderMap.get(id)){

            assignedOrders.remove(order);
        }
        deliveryPartnerOrderMap.remove(id);
    }
    public void  deleteOrderById(String orderId){

        if(assignedOrders.contains(orderMap.get(orderId))){

            outer:
            for(String partnerId : deliveryPartnerOrderMap.keySet()){
                for(Order order : deliveryPartnerOrderMap.get(partnerId))
                {
                    if(order.getId().equals(orderId))
                        deliveryPartnerOrderMap.get(partnerId).remove(order);

                    if(deliveryPartnerOrderMap.get(partnerId).isEmpty())
                        deliveryPartnerOrderMap.remove(partnerId);
                    break outer;
                }
            }
            assignedOrders.remove(orderMap.get(orderId));
        }
        orderMap.remove(orderId);
    }




}
