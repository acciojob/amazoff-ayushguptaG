package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap <String, Order> orderHashMap = new HashMap<>();
    HashMap <String, DeliveryPartner> partnerHashMap = new HashMap<>();
    HashMap <String, ArrayList <String>> pairMap = new HashMap<>();
    HashMap<String, String> orderToPartnerPair = new HashMap<>();


    public void addOrder(Order order){
        orderHashMap.put(order.getId(), order);
    }

    public Order getOrderById(String id){
        return orderHashMap.get(id);
    }

    public void addPartner(String partnerId){
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        partnerHashMap.put(partner.getId(), partner);
    }

    public DeliveryPartner getPartnerById(String id){
        return partnerHashMap.get(id);
    }

    public void addPair(String orderId, String partnerId){
        ArrayList <String> list;
        if(!pairMap.containsKey(partnerId)){
            list = new ArrayList<>();
        }
        else list = pairMap.get(partnerId);
        list.add(orderId);
        DeliveryPartner partner = partnerHashMap.get(partnerId);
        System.out.println(partner.getId());
        System.out.println(partner.getNumberOfOrders());
        partner.setNumberOfOrders(list.size());
        orderToPartnerPair.put(orderId, partnerId);
        pairMap.put(partnerId, list);
    }

    public int getOrderCount(String partnerId){
//        List <String> list = pairMap.get(partnerId);
//        DeliveryPartner deliveryPartner = partnerHashMap.get(partnerId);
//
//        return list.size();
        return partnerHashMap.get(partnerId).getNumberOfOrders();
    }

    public List<String> getOrders(String partnerId){
        return pairMap.get(partnerId);
    }

    public List<String> getAllOrders(){
        List<String> list = new ArrayList<>();
        for(String key: orderHashMap.keySet()){
            list.add(key);
        }
        return list;
    }

    public int getUnassignedOrderCount(){
        int count = 0;
        for(String orderId: orderHashMap.keySet()){
            if(!orderToPartnerPair.containsKey(orderId)){
                count++;
            }
        }
        return count;
    }

    public int getCountOfUndeliveredOrders(String time, String partnerId){
        int count = 0;
        List <String> orders = pairMap.get(partnerId);
        for(String orderId: orders){
            Order order = orderHashMap.get(orderId);
            if(order.getDeliveryTime() > order.convertTime(time)){
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTime(String partnerId){
        int max = 0;
        List <String> orders = pairMap.get(partnerId);
        for(String orderId: orders){
            Order order = orderHashMap.get(orderId);
            if(order.getDeliveryTime() > max){
                max = order.getDeliveryTime();
            }
        }
        int mm = max%60;
        int hh = max/60;
        String time = "";
        if(hh < 10) time += "0" + hh;
        else time += hh;
        time += ":" + mm;
        return time;
    }

    public void deletePartnerById(String partnerId){
        partnerHashMap.remove(partnerId);
        for(String orderID: orderToPartnerPair.keySet()){
            if(orderToPartnerPair.get(orderID).equalsIgnoreCase(partnerId)){
                orderToPartnerPair.remove(orderID);
            }
        }
    }

    public void deleteOrder(String orderId){
        orderHashMap.remove(orderId);
        String partnerId = orderToPartnerPair.get(orderId);
        pairMap.get(partnerId).remove(orderId);
    }

}