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
    public void addPartner(String id){
        orderRepository.addPartner(id);
    }
    public void addOrderPartnerPair(String orderId, String partnerId){
        orderRepository.addOrderPartnerPair(orderId, partnerId);
    }
    public Order getOrderById(String id){
       return orderRepository.getOrderById(id);
    }
    public DeliveryPartner getPartnerById(String id){
        return orderRepository.getPartnerById(id);
    }
    public Integer getOrderCountByPartnerId(String id){
        return orderRepository.getOrderCountByPartnerId(id);
    }
    public List<String> getOrdersByPartnerId(String id){
        return orderRepository.getOrdersByPartnerId(id);
    }
    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }
    public Integer getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time, partnerId);
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }
    public void  deletePartnerById(String id) throws Exception{
        orderRepository.deletePartnerById(id);
    }
    public void  deleteOrderById(String orderId)throws Exception{
        orderRepository.deleteOrderById(orderId);
    }
}
