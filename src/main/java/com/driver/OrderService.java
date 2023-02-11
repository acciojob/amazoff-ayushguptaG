package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    public Order getOrder(String id){
        return orderRepository.getOrderById(id);
    }

    public void addPartner(String partnerId){
        orderRepository.addPartner(partnerId);
    }

    public DeliveryPartner getPartner(String id){
        return orderRepository.getPartnerById(id);
    }

    public void createPair(String orderId, String partnerId){
        orderRepository.addPair(orderId, partnerId);
    }

    public int getOrdersCount(String partnerId){
        return orderRepository.getOrderCount(partnerId);
    }

    public List<String> getOrders(String partnerId){
        return orderRepository.getOrders(partnerId);
    }

    public List <String> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public int getUnassignedOrdersCount(){
        return orderRepository.getUnassignedOrderCount();
    }

    public int getUndeliveredCount(String time, String partnerId){
        return orderRepository.getCountOfUndeliveredOrders(time, partnerId);
    }

    public String getLastDeliveryTime(String partnerId){
        return orderRepository.getLastDeliveryTime(partnerId);
    }

    public void deletePartner(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrder(String orderId){
        orderRepository.deleteOrder(orderId);
    }
}